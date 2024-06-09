package com.sibijar.features.category;

import com.sibijar.admin.Admin;

public class Category extends Admin {
    private int id_kategori;
    private String nm_kategori;

    // Constructor
    public Category(int id_admin, String username, String password, String nama_lengkap_admin, int id_kategori,
            String nm_kategori) {
        super(id_admin, username, password, nama_lengkap_admin);
        this.id_kategori = id_kategori;
        this.nm_kategori = nm_kategori;
    }

    // Getters
    public int getId_kategori() {
        return id_kategori;
    }

    public String getNm_kategori() {
        return nm_kategori;
    }

    // Setters
    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public void setNm_kategori(String nm_kategori) {
        this.nm_kategori = nm_kategori;
    }
}
