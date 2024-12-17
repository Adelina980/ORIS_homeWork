package org.example.repository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DbWork {

//    final static Logger logger = LogManager.getLogger(DbWork.class);

    private static DbWork instance;

    private static HikariDataSource dataSource;

    private DbWork() {

        try {
            Class.forName("org.postgresql.Driver");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/profession");
            config.setUsername("postgres");
            config.setPassword("123");
            config.setConnectionTimeout(50000);
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);


        } catch (ClassNotFoundException | FlywayException e) {
//            logger.error("", e);
            e.printStackTrace();
        }
    }

    public static DbWork getInstance() {
        if (instance == null) {
            synchronized (DbWork.class) {
                if (instance == null) {
                    instance = new DbWork();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public void destroy() {
        dataSource.close();
    }
}
