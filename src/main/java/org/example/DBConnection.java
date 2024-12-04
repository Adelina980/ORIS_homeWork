package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class DBConnection {
    private static DBConnection _instance;
    private Stack<Connection> connections;
    private Set<Connection> usedConnections;
    private static final int MAX_CONNECTIONS = 5;


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
        connections = new Stack<>();
        usedConnections = new HashSet<>();

        try {
            Class.forName("org.postgresql.Driver");
            for(int i=0; i < MAX_CONNECTIONS; i++){
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HashPasswordProject", "postgres", "123");
                connections.push(connection);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException("Ошибка при создании соединения с базой данных: " + e.getMessage(), e);
        }
    }


    public synchronized Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = connections.pop();
            usedConnections.add(connection);
        } catch (EmptyStackException e) {
            connection =
                    DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/HashPasswordProject", "postgres", "123");
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        connections.push(connection);
    }

    public void destroy() {
        for (Connection connection : usedConnections ) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
