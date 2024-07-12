/*
package com.airalo.api;

import com.airalo.rest.Entity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Authentication extends BaseService {

    private final String token = "token";

    public Authentication() {
        try {
            urlBuilder = new URIBuilder(client.getApiUrl() + token);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

*/
/*    public String getToken() throws Exception {
        URI url = urlBuilder.build();
//                .addParameter("client_id", client.getClientId())
//                .addParameter("client_secret", client.getClientSecret())
//                .addParameter("grant_type", "client_credentials")
//                .build();

//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addTextBody("client_id", client.getClientId());
//        builder.addTextBody("client_secret", client.getClientSecret());
//        builder.addTextBody("grant_type", "client_credentials");


        HashMap<String, String> parts = new HashMap<>(){{
           put("client_id", client.getClientId());
           put("client_secret", client.getClientSecret());
           put("grant_type", "client_credentials");
        }};

        HttpPost request = new HttpPost(url);
        setEntity(request, parts);
//        request.setEntity(builder.build());
        setHeader(request, "Accept", "application/json");
        HttpResponse resp = sendRequest(request);
        Assert.assertEquals("Invalid status code: " + resp.getStatusLine().getStatusCode(), 200, resp.getStatusLine().getStatusCode());

        HttpEntity entity = resp.getEntity();
        String responseBody = entity == null ? "" : EntityUtils.toString(entity, StandardCharsets.UTF_8);

        Entity tokenEntity = toDTO(responseBody, Entity.class);

        String token = ((HashMap<String, String>)tokenEntity.getAdditionalProperties().get("data")).get("access_token");

        return token;
    }
   *//*

}
*/
