package com.kldev.dw.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTraderRequest {


    @JsonProperty
    private String id;

    @JsonProperty
    private String login;

    @JsonProperty
    private String password;

    @JsonProperty
    private long phone;

    @JsonProperty
    private String name;

    @JsonProperty
    private String email;

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
