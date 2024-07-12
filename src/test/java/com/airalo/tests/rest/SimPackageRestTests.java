package com.airalo.tests.rest;

import com.airalo.rest.Entity;
import com.airalo.rest.SimData;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SimPackageRestTests extends BaseRestTest {

    @Test
    public void testMerhabaPackage() throws Exception {
        String packageId = "merhaba-7days-1gb";
        ordersService.submitOrder2(packageId, 6);
//        List<JsonNode> sims = simsService.getAllSims();
//        List matching = sims.stream().filter(sim -> sim.get("simable").get("package_id").asText().equals(packageId)).collect(Collectors.toList());
        List<SimData> sims = simsService.getAllSims2();
        List matching = sims.stream().filter(sim -> sim.getSimable().getPackageId().equals(packageId)).collect(Collectors.toList());
        Assert.assertTrue(matching.size() > 6);
    }
}
