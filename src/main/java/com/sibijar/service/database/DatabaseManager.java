package com.sibijar.service.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {    private static final String URL = "jdbc:mysql://localhost:3306/sibijar";

    private static final String USER = "root";
    private static final String PASS = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void executeUpdate(String sql, Object... parameters) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            statement.executeUpdate();
        }
    }

    public ResultSet executeQuery(String sql, Object... parameters) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
        return statement.executeQuery();
    }
}