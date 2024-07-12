package com.airalo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimPackage extends BaseEntity {

    @JsonProperty("data")
    private Simable data;

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("data")
    public Simable getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Simable data) {
        this.data = data;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
