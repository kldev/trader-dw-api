package com.kldev.dw.controller;

import com.kldev.dw.controller.model.AuthRequest;
import com.kldev.dw.controller.model.CodeResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Path("/login")
public class LoginController {

    private final String authSecret;

    public LoginController(String authSecret) {
        this.authSecret = authSecret;
    }

    @SuppressWarnings("Duplicates")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(AuthRequest auth)
    {
        String token = "";

        if (auth.getUsername().equals("admin") && auth.getPassword().equals("password"))
        {
           token = createToken("admin", auth.getUsername(), UUID.randomUUID().toString());

            return Response.ok().header("Authorization", "Bearer " + token)
                    .header("X-APP-ROLE", "admin")
                    .entity(new CodeResponse(200, "Success"))
                    .build();
        }

        if (auth.getUsername().equals("trader") && auth.getPassword().equals("password"))
        {

            token = createToken("trader", auth.getUsername(), UUID.randomUUID().toString());

            return Response.ok().header("Authorization", "Bearer " + token)
                    .header("X-APP-ROLE", "trader")
                    .entity(new CodeResponse(200, "Success"))
                    .build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).entity(new CodeResponse(403, "Access denied")).build();

    }

    private String createToken(String role, String username, String id)
    {
        Map<String, Object> roles = new HashMap<>();
        roles.put("role", role);
        roles.put("username", username);
        roles.put("id", id);


        String token = Jwts.builder().setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(24, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS512, authSecret.getBytes())
                .setClaims(roles)
                .compact();

        return token;

    }
}
