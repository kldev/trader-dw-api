package com.kldev.dw.controller.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBookRequest {

    @JsonProperty
    private String id;

    @JsonProperty
    private String traderId;

    @JsonProperty
    private String author;

    @JsonProperty
    private String title;

    @JsonProperty
    private double price;

    public String getId() {
        return id;
    }

    public String getTraderId() {
        return traderId;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
