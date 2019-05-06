package com.kldev.dw.controller;

import com.codahale.metrics.annotation.Timed;
import com.kldev.dw.controller.model.CreateTraderRequest;
import com.kldev.dw.controller.security.UserAuth;
import com.kldev.dw.service.AppService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/trader")
public final class TraderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TraderController.class);

    private AppService appService;


    public TraderController(AppService appService, String authSecret) {
        this.appService = appService;
        this.authSecret = authSecret;
        this.role = "trader";
    }


    @Timed
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateTraderRequest trader)
    {
        UserAuth auth = authorizeUser();

        logger.info("Create trader by: " + auth.getName());

        appService.createTrader(trader);

        return success();
    }


}
