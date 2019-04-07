package com.kldev.dw.controller;

import com.codahale.metrics.annotation.Timed;
import com.kldev.dw.controller.model.CreateBookRequest;
import com.kldev.dw.service.AppService;
import com.kldev.dw.storage.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/book")
public final class BookController extends BaseController {

    private AppService appService;

    public BookController(AppService appService) {
        this.appService = appService;
    }


    @Timed
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateBookRequest book)
    {
        appService.createBook(book);

        return success();
    }

    @Timed
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list()
    {

        ArrayList<Book> bookList = appService.bookList();

        return success(bookList);
    }

    @Timed
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{traderId}")
    public Response list(@PathParam("traderId") String traderId)
    {

        ArrayList<Book> bookList = appService.bookListByTraderId(traderId);

        return success(bookList);

    }
}
