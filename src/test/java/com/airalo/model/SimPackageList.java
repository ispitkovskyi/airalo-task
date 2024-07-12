package com.airalo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimPackageList extends BaseEntity {

    @JsonProperty("data")
    private List<SimData> data;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("data")
    public List<SimData> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<SimData> data) {
        this.data = data;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
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
