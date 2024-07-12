package com.airalo.rest;
import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Apn extends BaseEntity{

    @JsonProperty("ios")
    private Ios ios;
    @JsonProperty("android")
    private Android android;

    @JsonProperty("ios")
    public Ios getIos() {
        return ios;
    }

    @JsonProperty("ios")
    public void setIos(Ios ios) {
        this.ios = ios;
    }

    @JsonProperty("android")
    public Android getAndroid() {
        return android;
    }

    @JsonProperty("android")
    public void setAndroid(Android android) {
        this.android = android;
    }

}
