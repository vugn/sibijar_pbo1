package com.sibijar.category;

import java.util.ArrayList;
import java.util.List;

import com.sibijar.admin.Admin;

public class CategoryManager {
    private List<Category> categories;
    private Admin admin;

    public CategoryManager(Admin admin) {
        this.admin = admin;
        this.categories = new ArrayList<>();
    }

    public void addCategory(int id_kategori, String nm_kategori) {
        if (isAdmin()) {
            Category category = new Category(admin.getId_admin(), admin.getUsername(), admin.getPassword(),
                    admin.getNama_lengkap_admin(), id_kategori, nm_kategori);
            categories.add(category);
        } else {
            System.out.println("Hanya admin yang bisa menambahkan Kategori.");
        }
    }

    public void showCategories() {
        if (isAdmin()) {
            for (Category category : categories) {
                System.out
                        .println("ID: " + category.getId_kategori() + ", Nama Kategori: " + category.getNm_kategori());
            }
        } else {
            System.out.println("Hanya admin yang bisa melihat Kategori.");
        }
    }

    public boolean isAdmin() {
        // Check if the user is an admin
        boolean isAdmin = admin.getUsername().equals("admin") && admin.getPassword().equals("admin");
        return isAdmin;
    }

    public List<Category> getCategories() {
        return categories;
    }
}