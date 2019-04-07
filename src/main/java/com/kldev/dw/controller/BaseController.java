package com.kldev.dw.controller;

import com.kldev.dw.controller.model.CodeResponse;

import javax.ws.rs.core.Response;

public abstract class BaseController {

    public Response success()
    {
        CodeResponse codeResponse = new CodeResponse(200, "success");

        return Response.ok().entity(codeResponse).build();
    }

    public Response success(Object object)
    {
        return Response.ok().entity(object).build();
    }
}
