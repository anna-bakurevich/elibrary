package com.jd2.elibrary.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    private final ComboPooledDataSource pool;

    public DataSource() {
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        pool.setJdbcUrl(resource.getString("url"));
        pool.setUser(resource.getString("user"));
        pool.setPassword(resource.getString("password"));

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(180);
    }

    private static DataSource instance;

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
