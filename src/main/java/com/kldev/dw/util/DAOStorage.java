package com.kldev.dw.util;

import com.kldev.dw.storage.dao.BookDAO;
import com.kldev.dw.storage.dao.TraderDAO;
import org.hibernate.SessionFactory;

public final class DAOStorage {

    private final TraderDAO traderDAO;
    private final BookDAO bookDAO;

    public DAOStorage(SessionFactory sessionFactory)
    {
        traderDAO = new TraderDAO(sessionFactory);
        bookDAO = new BookDAO(sessionFactory);
    }

    public TraderDAO getTraderDAO() {
        return traderDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }
}
