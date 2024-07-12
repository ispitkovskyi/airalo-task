package com.airalo.model;
import com.fasterxml.jackson.annotation.*;

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
