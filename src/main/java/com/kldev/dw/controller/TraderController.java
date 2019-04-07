package com.kldev.dw.controller;

import com.codahale.metrics.annotation.Timed;
import com.kldev.dw.controller.model.CreateTraderRequest;
import com.kldev.dw.service.AppService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trader")
public final class TraderController extends BaseController {

    private AppService appService;

    public TraderController(AppService appService) {
        this.appService = appService;
    }



    @Timed
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateTraderRequest trader)
    {
        appService.createTrader(trader);

        return success();
    }
}
