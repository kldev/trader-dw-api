package com.kldev.dw.controller;

import com.kldev.dw.controller.model.CodeResponse;
import com.kldev.dw.controller.security.UserAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public abstract class BaseController {


    @Context
    protected HttpServletRequest servletRequest;

    protected String role = "admin";
    protected String authSecret;

    public Response success()
    {
        CodeResponse codeResponse = new CodeResponse(200, "success");

        return Response.ok().entity(codeResponse).build();
    }

    public Response success(Object object)
    {
        return Response.ok().entity(object).build();
    }

    protected UserAuth authorizeUser() {

        String auth = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(authSecret.getBytes())
                    .parseClaimsJws(auth.replace("Bearer ", "")).getBody();


            UserAuth prince = new UserAuth(
                    claims.get("username", String.class),
                    claims.get("id", String.class),
                    claims.get("role", String.class));

            if (prince.getRole().equals(role))
            {
                return prince;
            }

            return prince;

        } catch (Exception ex) {
            Exception cause = new IllegalArgumentException(ex.getMessage());
            throw new WebApplicationException(cause, Response.Status.UNAUTHORIZED);
        }


    }


}
