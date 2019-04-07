package com.kldev.dw.storage.dao;

import com.kldev.dw.storage.Trader;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class TraderDAO extends AbstractDAO<Trader> {

    public TraderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void create(Trader trader) {
        currentSession().save(trader);
    }
}
