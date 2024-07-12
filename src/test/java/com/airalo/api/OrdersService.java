package com.airalo.api;

import com.airalo.rest.Entity;
import com.airalo.rest.SimPackage;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class OrdersService extends BaseService{

    private final String ORDERS_PATH = "orders";
    public OrdersService() {
        try {
            urlBuilder = new URIBuilder(client.getApiUrl() + ORDERS_PATH);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode submitOrder(String packageId, int quantity){
        urlBuilder.clearParameters();

        HashMap<String, String> parts = new HashMap<>(){{
            put("type", "sim");
            put("package_id", packageId);
            put("quantity", String.valueOf(quantity));
        }};

        return postEntity(parts);
    }

    public SimPackage submitOrder2(String packageId, int quantity){
        urlBuilder.clearParameters();

        HashMap<String, String> parts = new HashMap<>(){{
            put("type", "sim");
            put("package_id", packageId);
            put("quantity", String.valueOf(quantity));
        }};

        return postEntity2(parts);
    }
}
