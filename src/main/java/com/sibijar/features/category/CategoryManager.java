package com.sibijar.features.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sibijar.admin.Admin;
import com.sibijar.service.database.DatabaseManager;

public class CategoryManager {
    private DatabaseManager dbManager;
    private Admin admin;

    public CategoryManager(Admin admin, DatabaseManager dbManager) {
        this.admin = admin;
        this.dbManager = dbManager;
    }

    public void addCategory(int id_kategori, String nm_kategori) {
        if (isAdmin()) {
            if (nm_kategori != null && !nm_kategori.trim().isEmpty()) {
                String sql = "INSERT INTO kategori (id_kategori, nm_kategori, id_admin) VALUES (?, ?, ?)";
                try {
                    dbManager.executeUpdate(sql, id_kategori, nm_kategori, admin.getId_admin());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Nama kategori tidak boleh kosong.");
            }
        } else {
            System.out.println("Hanya admin yang bisa menambahkan Kategori.");
        }
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        if (isAdmin()) {
            String sql = "SELECT * FROM kategori";
            try {
                ResultSet rs = dbManager.executeQuery(sql);
                while (rs.next()) {
                    categories.add(new Category(admin.getId_admin(), admin.getUsername(), admin.getPassword(),
                            admin.getNama_lengkap_admin(), rs.getInt("id_kategori"), rs.getString("nm_kategori")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Hanya admin yang bisa melihat Kategori.");
        }
        return categories;
    }

    public void deleteCategory(int id_kategori) {
        if (isAdmin()) {
            String sql = "DELETE FROM kategori WHERE id_kategori = ?";
            try {
                dbManager.executeUpdate(sql, id_kategori);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Hanya admin yang bisa menghapus Kategori.");
        }
    }

    public void updateCategory(int id_kategori, String nm_kategori) {
        if (isAdmin()) {
            if (nm_kategori != null && !nm_kategori.trim().isEmpty()) {
                String sql = "UPDATE kategori SET nm_kategori = ? WHERE id_kategori = ?";
                try {
                    dbManager.executeUpdate(sql, nm_kategori, id_kategori);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Nama kategori tidak boleh kosong.");
            }
        } else {
            System.out.println("Hanya admin yang bisa mengubah Kategori.");
        }
    }

    public boolean isAdmin() {
        // Check if the user is an admin
        boolean isAdmin = admin.getUsername().equals("admin") && admin.getPassword().equals("admin");
        return isAdmin;
    }
}