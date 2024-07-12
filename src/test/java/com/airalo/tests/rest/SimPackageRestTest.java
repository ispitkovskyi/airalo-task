package com.airalo.tests.rest;

import com.airalo.model.SimData;
import com.airalo.model.SimPackage;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SimPackageRestTest extends BaseRestTest {

    @Test
    public void testMerhabaPackage() {
        String packageId = "merhaba-7days-1gb";
        SimPackage simPackage = ordersService.submitOrder(packageId, 6);
        Assert.assertTrue("Number of sims in the package does not match expected value: 6", simPackage.getData().getSims().size() == 6);
        List<SimData> sims = simsService.getAllSims();
        List matching = sims.stream().filter(sim -> sim.getSimable().getPackageId().equals(packageId)).collect(Collectors.toList());
        Assert.assertTrue(matching.size() > 6);
    }
}
