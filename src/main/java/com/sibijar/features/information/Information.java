package com.sibijar.features.information;

public class Information {
    private int id_info;
    private String nm_info;
    private String isi;
    private String gambar;
    private String tgl_posting;
    private int id_admin;
    private int id_kategori;

    public Information(int id_info, String nm_info, String isi, String gambar, String tgl_posting, int id_admin,
            int id_kategori) {
        this.id_info = id_info;
        this.nm_info = nm_info;
        this.isi = isi;
        this.gambar = gambar;
        this.tgl_posting = tgl_posting;
        this.id_admin = id_admin;
        this.id_kategori = id_kategori;
    }

    public Information(int id_info, String nm_info, String isi, String gambar, String tgl_posting, int id_admin,
            int id_kategori, String unused1, String unused2, String unused3, String unused4, int unused5) {
        this(id_info, nm_info, isi, gambar, tgl_posting, id_admin, id_kategori);
    }

    // Getters and setters for each field
    // ...

    public int getId_info() {
        return id_info;
    }

    public void setId_info(int id_info) {
        this.id_info = id_info;
    }

    public String getNm_info() {
        return nm_info;
    }

    public void setNm_info(String nm_info) {
        this.nm_info = nm_info;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTgl_posting() {
        return tgl_posting;
    }

    public void setTgl_posting(String tgl_posting) {
        this.tgl_posting = tgl_posting;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }
}