package com.airalo.api;

import com.airalo.model.SimData;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.List;

public class SimsService extends BaseService {

    private final String SIMS_PATH = "sims";

    public SimsService() {
        try {
            urlBuilder = new URIBuilder(client.getApiUrl() + SIMS_PATH);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SimData> getAllSims() {
        urlBuilder.clearParameters()
                .addParameter("include", "order,order.status,order.user")
                .addParameter("limit", "100");
        return getAllItems();
    }
}
