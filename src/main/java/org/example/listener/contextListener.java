package org.example.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.repository.DbWork;
import org.flywaydb.core.Flyway;

@WebListener
public class contextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
//        Flyway flyway = Flyway.configure()
//                .dataSource("jdbc:postgresql://localhost:5432/pagination", "postgres", "123").load();
//
//        flyway.migrate();

        DbWork.getInstance();
    }
    public void contextDestroyed(ServletContextEvent sce) {
        DbWork.getInstance().destroy();
    }
}


