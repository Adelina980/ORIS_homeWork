package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static DBConnection _instance;
    private static Connection connection;


    public synchronized static DBConnection getInstance() throws SQLException {
        if (_instance == null) {
            synchronized (DBConnection.class) {
                if (_instance == null) {
                    _instance = new DBConnection();
                }
            }
        }
        return _instance;
    }

    private DBConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CookieProject", "postgres", "123");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new SQLException("Ошибка при создании соединения с базой данных: " + e.getMessage(), e);
        }
    }


    public static Connection getConnection() {
        return connection;
    }

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при закрытии соединения с БД: " + e.getMessage());
        }
    }
}
