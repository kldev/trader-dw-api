package com.kldev.dw.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeResponse {

    public CodeResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonProperty
    private int code;

    @JsonProperty
    private String message;
}

