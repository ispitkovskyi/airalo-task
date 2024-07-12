package com.airalo.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Android extends BaseEntity{

    @JsonProperty("apn_type")
    private String apnType;
    @JsonProperty("apn_value")
    private String apnValue;

    @JsonProperty("apn_type")
    public String getApnType() {
        return apnType;
    }

    @JsonProperty("apn_type")
    public void setApnType(String apnType) {
        this.apnType = apnType;
    }

    @JsonProperty("apn_value")
    public String getApnValue() {
        return apnValue;
    }

    @JsonProperty("apn_value")
    public void setApnValue(String apnValue) {
        this.apnValue = apnValue;
    }

}