package com.airalo.tests.rest;

import com.airalo.api.OrdersService;
import com.airalo.api.SimsService;

public abstract class BaseRestTest {

    SimsService simsService;
    OrdersService ordersService;

    public BaseRestTest() {
        simsService = new SimsService();
        ordersService = new OrdersService();
    }

}
