package com.airalo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemsList extends Entity {

    @JsonProperty("data")
    public List<Entity> items = null;

    public List<Entity> getItems(){
        return this.items;
    }
}
