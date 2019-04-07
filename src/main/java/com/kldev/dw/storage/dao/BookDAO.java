package com.kldev.dw.storage.dao;

import com.kldev.dw.storage.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void create(Book book) {

        currentSession().save(book);
    }

    public ArrayList<Book> list() {


        Query query = this.currentSession().createQuery("FROM Book");
        query.setMaxResults(100);

        return (ArrayList<Book>)query.getResultList();
    }

    public ArrayList<Book> listByTrader(String traderId) {

        Query query = this.currentSession().createQuery("FROM Book WHERE traderId = :traderId");
        query.setMaxResults(100);
        query.setParameter("traderId", traderId);

        return (ArrayList<Book>)query.getResultList();

    }
}
