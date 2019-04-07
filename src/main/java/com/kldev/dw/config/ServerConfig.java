package com.kldev.dw.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.constraints.NotNull;

public class ServerConfig extends Configuration {


    @JsonProperty("database")
    @NotNull
    private DataSourceFactory database;

    public DataSourceFactory getDatabase() {
        return database;
    }

}
