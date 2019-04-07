package com.kldev.dw.storage.proxy;

import com.kldev.dw.storage.Trader;
import com.kldev.dw.storage.dao.TraderDAO;
import io.dropwizard.hibernate.UnitOfWork;

public class TraderProxy {

    private TraderDAO traderDAO;

    public TraderProxy(TraderDAO traderDAO) {
        this.traderDAO = traderDAO;
    }

    @UnitOfWork
    public void create(Trader trader)
    {
        traderDAO.create(trader);
    }

}


