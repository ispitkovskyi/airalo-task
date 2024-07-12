package com.airalo.api;

import com.airalo.config.HttpClient;
import com.airalo.model.BaseEntity;
import com.airalo.model.SimData;
import com.airalo.model.SimPackage;
import com.airalo.model.SimPackageList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class BaseService<T extends BaseEntity> {

    protected HttpClient client;
    protected URIBuilder urlBuilder;
    protected ObjectMapper jacksonMapper;

    protected BaseService() {
        client = HttpClient.getInstance();
        jacksonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jacksonMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        if (StringUtils.isEmpty(client.getAccessToken())) {
            String token = getToken();
            client.setToken(token);
        }
    }

    public String getToken() {
        String url = client.getApiUrl() + "token";

        HashMap<String, String> parts = new HashMap<>() {{
            put("client_id", client.getClientId());
            put("client_secret", client.getClientSecret());
            put("grant_type", "client_credentials");
        }};

        HttpPost request = new HttpPost(url);
        setEntity(request, parts);
        setHeader(request, "Accept", "application/json");

        HttpResponse resp = sendRequest(request);
        Assert.assertEquals("Invalid status code on obtaining access token: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

        JsonNode tokenNode = getResponseNode(resp);
        String token = tokenNode.get("access_token").asText();
        return token;
    }

    private void setHeaders(HttpRequestBase req, HashMap<String, String> headers) {
        headers.entrySet().stream().forEach(entry -> setHeader(req, entry.getKey(), entry.getValue()));
    }

    private void setHeader(HttpRequestBase req, String headerName, String headerValue) {
        req.setHeader(new BasicHeader(headerName, headerValue));
    }

    private void setEntity(HttpEntityEnclosingRequest req, HashMap<String, String> entityParts) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        entityParts.entrySet().stream().forEach(part -> builder.addTextBody(part.getKey(), part.getValue()));
        req.setEntity(builder.build());
    }

    private void setDefaultHeaders(HttpRequestBase request) {
        setHeader(request, "Accept", "application/json");
        setHeader(request, "Authorization", "Bearer " + client.getAccessToken());
    }

    private HttpResponse sendRequest(HttpRequestBase request) {
        setDefaultHeaders(request);
        HttpResponse response;
        try {
            response = client.sendRequest(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    private T toDTO(String json, Class<? extends BaseEntity> dtoClassType) throws IOException {
        return (T) jacksonMapper.readValue(json, dtoClassType);
    }

    private JsonNode toNODE(String json) throws IOException {
        return jacksonMapper.readTree(json).get("data");
    }

    private JsonNode getResponseNode(HttpResponse response) {
        String responseBody = getResponseBody(response);
        try {
            return toNODE(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private T getResponseEntity(HttpResponse response, Class<? extends BaseEntity> dtoClassType) {
        String responseBody = getResponseBody(response);
        try {
            return toDTO(responseBody, dtoClassType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponseBody(HttpResponse responce) {
        HttpEntity entity = responce.getEntity();
        String responseBody;
        try {
            responseBody = entity == null ? "" : EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    protected List<SimData> getAllItems() {
        try {
            URI url = urlBuilder.build();
            HttpGet request = new HttpGet(url);

            HttpResponse resp = sendRequest(request);
            Assert.assertEquals("Invalid status code on getting Sims: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

            SimPackageList allSimPacks = (SimPackageList) getResponseEntity(resp, SimPackageList.class);
            return allSimPacks.getData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected SimPackage postEntity(HashMap<String, String> parts) {
        try {
            URI url = urlBuilder.build();

            HttpPost request = new HttpPost(url);
            setEntity(request, parts);

            HttpResponse resp = sendRequest(request);
            Assert.assertEquals("Invalid status code on submitting post request: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

            return (SimPackage) getResponseEntity(resp, SimPackage.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
