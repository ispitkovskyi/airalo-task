package com.airalo.model;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {

    @JsonIgnore
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public BaseEntity(){

    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonAnyGetter
    public void setAdditionalProperties(HashMap<String, Object> props) {
        if(props!=null)
            props.entrySet().stream().forEach(propEntry -> this.additionalProperties.put(propEntry.getKey(), propEntry.getValue()));
    }
}
