package com.sibijar.features.information;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sibijar.admin.Admin;
import com.sibijar.features.category.Category;
import com.sibijar.features.category.CategoryManager;
import com.sibijar.service.database.DatabaseManager;

public class InformationManager {
    private DatabaseManager dbManager;
    private CategoryManager categoryManager;
    private Admin admin;

    public InformationManager(Admin admin, DatabaseManager dbManager, CategoryManager categoryManager) {
        this.admin = admin;
        this.dbManager = dbManager;
        this.categoryManager = categoryManager;
    }

    public boolean addInformation() {
        boolean isInserted = false;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Masukkan nama informasi:");
            String nm_info = scanner.nextLine();

            System.out.println("Masukkan konten informasi:");
            String isi = scanner.nextLine();

            System.out.println("Masukkan URL gambar:");
            String gambar = scanner.nextLine();

            System.out.println("Masukkan tanggal posting (YYYY-MM-DD):");
            String tgl_posting = scanner.nextLine();

            System.out.println("Kategori yang tersedia:");
            viewCategory();

            System.out.println("Masukkan ID kategori:");
            int id_kategori = scanner.nextInt();

            if (isAdmin()) {
                if (nm_info != null && !nm_info.trim().isEmpty()) {
                    String sql = "INSERT INTO informasi (nm_info, isi, gambar, tgl_posting, id_admin, id_kategori) VALUES (?, ?, ?, ?, ?, ?)";
                    try {
                        dbManager.executeUpdate(sql, nm_info, isi, gambar, tgl_posting, admin.getId_admin(),
                                id_kategori);
                        isInserted = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Nama informasi tidak boleh kosong.");
                }
            } else {
                System.out.println("Hanya admin yang bisa menambahkan Informasi.");
            }
        }
        return isInserted;
    }

    public Object getInformation(Integer id_info) {
        if (id_info != null) {
            Information info = null;
            String sql = "SELECT * FROM informasi WHERE id_info = ?";
            try {
                ResultSet rs = dbManager.executeQuery(sql, id_info);
                if (rs.next()) {
                    info = new Information(
                            rs.getInt("id_info"),
                            rs.getString("nm_info"),
                            rs.getString("isi"),
                            rs.getString("gambar"),
                            rs.getString("tgl_posting"),
                            admin.getId_admin(),
                            rs.getInt("id_kategori"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return info;
        } else {
            List<Information> informations = new ArrayList<>();
            String sql = "SELECT * FROM informasi";
            try {
                ResultSet rs = dbManager.executeQuery(sql);
                while (rs.next()) {
                    informations.add(new Information(
                            rs.getInt("id_info"),
                            rs.getString("nm_info"),
                            rs.getString("isi"),
                            rs.getString("gambar"),
                            rs.getString("tgl_posting"),
                            admin.getId_admin(),
                            rs.getInt("id_kategori")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return informations;
        }
    }

    public boolean deleteInformation(int id_info) {
        if (isAdmin()) {
            String sql = "DELETE FROM informasi WHERE id_info = ?";
            try {
                dbManager.executeUpdate(sql, id_info);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Hanya admin yang bisa menghapus Informasi.");
            return false;
        }
    }

    public boolean updateInformation() {
        if (isAdmin()) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Pilih id yang ingin di ubah:");
                int id_info = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Masukkan nama informasi baru (kosongkan jika tidak ingin mengubah):");
                String nm_info = scanner.nextLine();

                System.out.println("Masukkan konten informasi baru (kosongkan jika tidak ingin mengubah):");
                String isi = scanner.nextLine();

                System.out.println("Masukkan URL gambar baru (kosongkan jika tidak ingin mengubah):");
                String gambar = scanner.nextLine();

                System.out.println("Masukkan tanggal posting baru (YYYY-MM-DD) (kosongkan jika tidak ingin mengubah):");
                String tgl_posting = scanner.nextLine();

                System.out.println("Kategori yang tersedia:");
                viewCategory();

                System.out.println("Masukkan ID kategori baru (kosongkan jika tidak ingin mengubah):");
                int id_kategori = 0;

                String id_kategori_str = scanner.nextLine();

                if (!id_kategori_str.isEmpty() && !categoryExists(Integer.parseInt(id_kategori_str))) {
                    System.out.println("No category found with the provided id_kategori.");
                    return false;
                }

                // Fetch the current information
                Information currentInfo = (Information) getInformation(id_info);

                // If the user didn't provide a new value, use the current value
                if (currentInfo != null) {
                    nm_info = nm_info.isEmpty() ? currentInfo.getNm_info() : nm_info;
                    isi = isi.isEmpty() ? currentInfo.getIsi() : isi;
                    gambar = gambar.isEmpty() ? currentInfo.getGambar() : gambar;
                    tgl_posting = tgl_posting.isEmpty() ? currentInfo.getTgl_posting() : tgl_posting;
                    id_kategori = id_kategori_str.isEmpty() ? currentInfo.getId_kategori()
                            : Integer.parseInt(id_kategori_str);
                } else {
                    System.out.println("No information found with the provided id_info.");
                }

                String sql = "UPDATE informasi SET nm_info = ?, isi = ?, gambar = ?, tgl_posting = ?, id_kategori = ? WHERE id_info = ?";
                try {
                    dbManager.executeUpdate(sql, nm_info, isi, gambar, tgl_posting, id_kategori, id_info);
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            System.out.println("Hanya admin yang bisa mengubah Informasi.");
            return false;
        }
    }

    public boolean isAdmin() {
        // Check if the user is an admin
        boolean isAdmin = admin.getUsername().equals("admin") && admin.getPassword().equals("admin");
        return isAdmin;
    }

    private void viewCategory() {
        List<Category> categoryResult = categoryManager.getCategories();
        System.out.println("\n-------------------------------------------------");
        System.out.printf("| %-10s | %-30s |\n", "ID Kategori", "Nama Kategori");
        System.out.println("-------------------------------------------------");
        for (Category category : categoryResult) {
            System.out.printf("| %-10s | %-30s |\n", category.getId_kategori(), category.getNm_kategori());
        }
        System.out.println("-------------------------------------------------");
    }

    private boolean categoryExists(int id_kategori) {
        String sql = "SELECT 1 FROM kategori WHERE id_kategori = ?";
        try {
            ResultSet rs = dbManager.executeQuery(sql, id_kategori);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}