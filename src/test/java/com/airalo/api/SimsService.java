package com.airalo.api;

import com.airalo.rest.Entity;
import com.airalo.rest.SimData;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class SimsService extends BaseService{

    private final String SIMS_PATH = "sims";
    public SimsService() {
        try {
            urlBuilder = new URIBuilder(client.getApiUrl() + SIMS_PATH);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<JsonNode> getAllSims() {
        urlBuilder.clearParameters()
                .addParameter("include", "order,order.status,order.user")
                .addParameter("limit", "100");
        return getAllItems();
    }

    public List<SimData> getAllSims2() {
        urlBuilder.clearParameters()
                .addParameter("include", "order,order.status,order.user")
                .addParameter("limit", "100");
        return getAllItems2();
    }
}
