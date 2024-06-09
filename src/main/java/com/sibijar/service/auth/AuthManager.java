package com.sibijar.service.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sibijar.admin.Admin;
import com.sibijar.service.database.DatabaseManager;

public class AuthManager {
    private DatabaseManager databaseManager;

    public AuthManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Admin authenticate(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, username, password);
            if (resultSet.next()) {
                return new Admin(Integer.parseInt(resultSet.getString("id_admin")), resultSet.getString("username"),
                        resultSet.getString("Password"), resultSet.getString("nama_lengkap_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}