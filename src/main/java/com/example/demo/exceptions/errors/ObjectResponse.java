package com.example.demo.exceptions.errors;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectResponse implements Serializable {

    private static final long serialVersionUID = 5636917942089571061L;

    private String type;
    private String link;
    private String icon;
    private Object data;

    public ObjectResponse(Object data) {
        this.data = data;
    }

    public ObjectResponse() {

    }
}
