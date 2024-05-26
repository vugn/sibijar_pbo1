package com.sibijar.admin;

public class Admin {
    private int id_admin;
    private String username, password, nama_lengkap_admin;

    // Constructor
    public Admin(int id_admin, String username, String password, String nama_lengkap_admin) {
        this.id_admin = id_admin;
        this.username = username;
        this.password = password;
        this.nama_lengkap_admin = nama_lengkap_admin;
    }

    // Getters
    public int getId_admin() {
        return id_admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama_lengkap_admin() {
        return nama_lengkap_admin;
    }

    // Setters
    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama_lengkap_admin(String nama_lengkap_admin) {
        this.nama_lengkap_admin = nama_lengkap_admin;
    }
}