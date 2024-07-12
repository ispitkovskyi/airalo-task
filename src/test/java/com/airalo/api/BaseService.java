package com.airalo.api;

import com.airalo.config.HttpClient;
import com.airalo.rest.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class BaseService <T extends BaseEntity> {

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

    protected void setHeaders(HttpRequestBase req, HashMap<String, String> headers){
        headers.entrySet().stream().forEach(entry -> setHeader(req, entry.getKey(), entry.getValue()));
    }

    protected void setHeader(HttpRequestBase req, String headerName, String headerValue){
        req.setHeader(new BasicHeader(headerName, headerValue));
    }

    protected void setEntity(HttpEntityEnclosingRequest req, HashMap<String, String> entityParts) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        entityParts.entrySet().stream().forEach(part -> builder.addTextBody(part.getKey(), part.getValue()));
        req.setEntity(builder.build());
    }

    protected HttpResponse sendRequest(HttpRequestBase request) {
        HttpResponse response;
        try {
            response = client.sendRequest(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    protected T toDTO(String json, Class<? extends BaseEntity> dtoClassType) throws IOException {
        return (T) jacksonMapper.readValue(json, dtoClassType);
//        return (T) jacksonMapper.readValue(json, Entity.class);
    }

    protected JsonNode toNODE(String json) throws IOException {
        return jacksonMapper.readTree(json).get("data");
    }

    public List<T> toDTOList(String json, Class<? extends Entity> dtoClassType) throws IOException {
        List dtoList = jacksonMapper.readValue(json, jacksonMapper.getTypeFactory().constructCollectionType(List.class, dtoClassType));
//        List dtoList = jacksonMapper.readValue(json, jacksonMapper.getTypeFactory().constructCollectionType(List.class, Entity.class));
        return dtoList;
    }

    protected String toJSON(T dto) throws JsonProcessingException {
        return jacksonMapper.writeValueAsString(dto);
    }

    protected String toJSON(List<T> dtoList) throws JsonProcessingException {
        return jacksonMapper.writeValueAsString(dtoList);
    }

    public String getToken() {
        String url = client.getApiUrl() + "token";
//        URI url = client.getApiUrl() + "token";

/*        try {
            url = urlBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }*/

        HashMap<String, String> parts = new HashMap<>(){{
            put("client_id", client.getClientId());
            put("client_secret", client.getClientSecret());
            put("grant_type", "client_credentials");
        }};

        HttpPost request = new HttpPost(url);
        setEntity(request, parts);
        setHeader(request, "Accept", "application/json");

        HttpResponse resp = sendRequest(request);
        Assert.assertEquals("Invalid status code on obtaining access token: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

//        Entity tokenEntity = getResponseEntity(resp);
//        String token = ((HashMap<String, String>)tokenEntity.getAdditionalProperties().get("data")).get("access_token");

        JsonNode tokenNode = getResponseNode(resp);
        String token = tokenNode.get("access_token").asText();
        return token;
    }

/*    protected Entity getResponseEntity(HttpResponse response){
        return getResponseEntity(response, Entity.class);
    }*/

    protected T getResponseEntity(HttpResponse response, Class<? extends BaseEntity> dtoClassType){
        String responseBody = getResponseBody(response);
        try {
            return toDTO(responseBody, dtoClassType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected JsonNode getResponseNode(HttpResponse response){
        String responseBody = getResponseBody(response);
        try {
            return toNODE(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<JsonNode> getResponseNodesList(HttpResponse response){
        JsonNode root = getResponseNode(response);
        ArrayList<JsonNode> nodeArray = new ArrayList<>();
        if (root.isArray()) {
            Iterator<JsonNode> itr = root.iterator();
            while (itr.hasNext()) {
                JsonNode node=itr.next();
                nodeArray.add(node);
            }
        }
        return nodeArray;
    }

/*    protected List<T> getResponseEntitiesList(HttpResponse response){
        String responseBody = getResponseBody(response);
        try {
            return (List<Entity>) toDTOList(responseBody, ItemsList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    protected String getResponseBody(HttpResponse responce){
        HttpEntity entity = responce.getEntity();
        String responseBody;
        try {
            responseBody = entity == null ? "" : EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    protected List<JsonNode> getAllItems() {
        try {
            URI url = urlBuilder.build();
            HttpGet request = new HttpGet(url);
            setHeader(request, "Accept", "application/json");
            setHeader(request, "Authorization", "Bearer " + client.getAccessToken());

            HttpResponse resp = sendRequest(request);
            Assert.assertEquals("Invalid status code on getting Sims: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

//            return getResponseEntitiesList(resp);
//            ItemsList ent = (ItemsList) getResponseEntity(resp, ItemsList.class);
//            return ent.getItems();
            return getResponseNodesList(resp);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<SimData> getAllItems2() {
        try {
            URI url = urlBuilder.build();
            HttpGet request = new HttpGet(url);
            setHeader(request, "Accept", "application/json");
            setHeader(request, "Authorization", "Bearer " + client.getAccessToken());

            HttpResponse resp = sendRequest(request);
            Assert.assertEquals("Invalid status code on getting Sims: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

//            return getResponseEntitiesList(resp);
//            ItemsList ent = (ItemsList) getResponseEntity(resp, ItemsList.class);
//            return ent.getItems();
//            return getResponseNodesList(resp);
            SimPackageList allSimPacks = (SimPackageList) getResponseEntity(resp, SimPackageList.class);
            return allSimPacks.getData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected JsonNode postEntity(HashMap<String, String> parts) {
        try {
            URI url = urlBuilder.build();

            HttpPost request = new HttpPost(url);
            setHeader(request, "Accept", "application/json");
            setHeader(request, "Authorization", "Bearer " + client.getAccessToken());
            setEntity(request, parts);

            HttpResponse resp = sendRequest(request);
            Assert.assertEquals("Invalid status code on submitting post request: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

//            return getResponseEntity(resp);
            return getResponseNode(resp);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected SimPackage postEntity2(HashMap<String, String> parts) {
        try {
            URI url = urlBuilder.build();

            HttpPost request = new HttpPost(url);
            setHeader(request, "Accept", "application/json");
            setHeader(request, "Authorization", "Bearer " + client.getAccessToken());
            setEntity(request, parts);

            HttpResponse resp = sendRequest(request);
            Assert.assertEquals("Invalid status code on submitting post request: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

            return (SimPackage) getResponseEntity(resp, SimPackage.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
