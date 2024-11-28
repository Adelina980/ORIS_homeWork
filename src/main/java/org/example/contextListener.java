package org.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import static org.example.DBConnection.releaseConnection;

@WebListener
public class contextListener implements ServletContextListener {
    private DBConnection dbConnection;
    public void contextInitialized(ServletContextEvent sce) {
        Map<UUID, Long> authentificationData = new HashMap<>();
        sce.getServletContext().setAttribute("AUTH_DATA", authentificationData);
        try {
            dbConnection = DBConnection.getInstance();
            System.out.println("Соединение с БД установлено.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании соединения с базой данных:", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // Закрываем подключение
        if(dbConnection != null){
            releaseConnection((Connection) dbConnection);
            System.out.println("Соединение с БД закрыто.");
        } else{
            System.out.println("Соединение с БД не было установлено.");
        }
    }
}

