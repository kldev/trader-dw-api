package com.kldev.dw.storage.proxy;

import com.kldev.dw.storage.Book;
import com.kldev.dw.storage.dao.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

/**
 * Keep Hibernate Session in proxy object not with controller request
 */
public class BookProxy {

    private BookDAO bookDAO;

    public BookProxy(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @UnitOfWork
    public void create(Book book)
    {

        if (book == null) throw new IllegalArgumentException("book");
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) throw new IllegalArgumentException("author");
        if (book.getTitle() == null || book.getTitle().isEmpty()) throw new IllegalArgumentException("title");

        book.setCreatedAt(Date.from(Instant.now()));
        bookDAO.create(book);
    }

    @UnitOfWork
    public ArrayList<Book> list() {

        return bookDAO.list();
    }

    @UnitOfWork
    public ArrayList<Book> listByTrader(String traderId) {

        return bookDAO.listByTrader(traderId);
    }
}
