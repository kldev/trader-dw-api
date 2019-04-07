package com.kldev.dw.util;

import com.kldev.dw.storage.dao.BookDAO;
import com.kldev.dw.storage.dao.TraderDAO;
import com.kldev.dw.storage.proxy.BookProxy;
import com.kldev.dw.storage.proxy.TraderProxy;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import org.hibernate.SessionFactory;

public final class ProxyStorage {

    private final BookProxy bookProxy;
    private final TraderProxy traderProxy;


    public ProxyStorage(SessionFactory factory, DAOStorage daoStorage)
    {
        final UnitOfWorkAwareProxyFactory apiProxyFactory =
                new UnitOfWorkAwareProxyFactory("dw", factory);

        bookProxy = apiProxyFactory.create(BookProxy.class, BookDAO.class, daoStorage.getBookDAO());
        traderProxy = apiProxyFactory.create(TraderProxy.class, TraderDAO.class, daoStorage.getTraderDAO());
    }


    public BookProxy getBookProxy() {
        return bookProxy;
    }

    public TraderProxy getTraderProxy() {
        return traderProxy;
    }
}
