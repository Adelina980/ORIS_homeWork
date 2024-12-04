package org.example;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@WebListener
public class contextListener implements ServletContextListener {
    private DBConnection dbConnection;

    final static Logger logger = LogManager.getLogger(contextListener.class);
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Create the Flyway instance and point it to the database

            logger.info("start migration config");

            Flyway flyway = Flyway.configure()//.baselineOnMigrate(true)
                    .dataSource("jdbc:postgresql://localhost:5432/HashPasswordProject", "postgres", "123").load();
            logger.info("start migration");

            // Start the migration
            flyway.migrate();
            logger.info("migration done");

            dbConnection = DBConnection.getInstance();
            System.out.println("Соединение с БД установлено.");
            Map<UUID, Long> userSessions = new HashMap<>();

            sce.getServletContext().setAttribute("USER_SESSIONS", userSessions);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании соединения с базой данных:", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // Закрываем подключение
        try {
            DBConnection.getInstance().destroy();
            System.out.println("Соединение с БД закрыто.");
        } catch (SQLException e) {
            System.out.println("Соединение с БД не было установлено.");
            throw new RuntimeException(e);
        }
        Map userSessions = (Map) sce.getServletContext().getAttribute("USER_SESSIONS");
        userSessions.clear();
    }
}

