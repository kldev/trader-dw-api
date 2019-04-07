/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.kldev.dw;

import com.kldev.dw.config.ServerConfig;
import com.kldev.dw.controller.BookController;
import com.kldev.dw.controller.TraderController;
import com.kldev.dw.service.AppService;
import com.kldev.dw.storage.Book;
import com.kldev.dw.storage.Trader;
import com.kldev.dw.util.DAOStorage;
import com.kldev.dw.util.ProxyStorage;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<ServerConfig> {


    private final HibernateBundle<ServerConfig> hibernate = new HibernateBundle<ServerConfig>(Book.class,
            Trader.class) {

        @Override
        protected String name() {
            return "dw";
        }

        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfig configuration) {
            return configuration.getDatabase();
        }
    };


    @Override
    public void initialize(Bootstrap<ServerConfig> bootstrap) {

        bootstrap.addBundle(hibernate);


        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }
    public static void main(String[] args) throws Exception {
        new  Main().run(args);
    }


    @Override
    public void run(ServerConfig config, Environment env) throws Exception {


        final DAOStorage daoStorage = new DAOStorage(hibernate.getSessionFactory());
        final ProxyStorage proxyStorage = new ProxyStorage(hibernate.getSessionFactory(), daoStorage);


        final AppService appService = new AppService(proxyStorage.getTraderProxy(), proxyStorage.getBookProxy());

        final BookController bookController = new BookController(appService);
        final TraderController traderController = new TraderController(appService);

        env.jersey().register(bookController);
        env.jersey().register(traderController);

    }
}
