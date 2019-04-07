package com.kldev.dw.service;

import com.kldev.dw.controller.model.CreateBookRequest;
import com.kldev.dw.controller.model.CreateTraderRequest;
import com.kldev.dw.storage.Book;
import com.kldev.dw.storage.Trader;
import com.kldev.dw.storage.proxy.BookProxy;
import com.kldev.dw.storage.proxy.TraderProxy;
import com.kldev.dw.util.SecurePasswordUtil;
import org.omg.CORBA.TRANSACTION_MODE;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public final class AppService {

    private final TraderProxy traderProxy;
    private final BookProxy bookProxy;

    public AppService(TraderProxy traderProxy, BookProxy bookProxy) {
        this.traderProxy = traderProxy;
        this.bookProxy = bookProxy;
    }


    public void createBook(CreateBookRequest request) {

        Book book = new Book();
        book.setCreatedAt(Date.from(Instant.now()));
        book.setSoldCount(0);

        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        book.setTraderId(request.getTraderId());
        book.setId(request.getId());
        book.setPrice(request.getPrice());


        bookProxy.create(book);
    }

    public ArrayList<Book> bookList() {

        return bookProxy.list();
    }

    public ArrayList<Book> bookListByTraderId(String traderId) {

        return bookProxy.listByTrader(traderId);
    }

    public void createTrader(CreateTraderRequest request)
    {
        Trader trader = new Trader();

        trader.setId(request.getId());
        trader.setEmail(request.getEmail());
        trader.setLogin(request.getLogin());
        trader.setName(request.getName());
        trader.setPhone(request.getPhone());
        trader.setPassword(SecurePasswordUtil.hash(request.getPassword()));

        traderProxy.create(trader);
    }
}
