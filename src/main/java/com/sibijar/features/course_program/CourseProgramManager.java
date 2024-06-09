package com.sibijar.features.course_program;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sibijar.admin.Admin;
import com.sibijar.service.database.DatabaseManager;

public class CourseProgramManager {
    private DatabaseManager dbManager;
    private Admin admin;

    public CourseProgramManager(Admin admin, DatabaseManager dbManager) {
        this.admin = admin;
        this.dbManager = dbManager;
    }

    public boolean addCourseProgram() {
        boolean isInserted = false;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Masukkan jenis program bimbel:");
            String jenis_program_bimbel = scanner.nextLine();

            System.out.println("Masukkan biaya:");
            int biaya = scanner.nextInt();

            if (isAdmin()) {
                if (jenis_program_bimbel != null && !jenis_program_bimbel.trim().isEmpty()) {
                    String sql = "INSERT INTO program_bimbel (jenis_program_bimbel, biaya, id_admin) VALUES (?, ?, ?)";
                    try {
                        dbManager.executeUpdate(sql, jenis_program_bimbel, biaya, admin.getId_admin());
                        isInserted = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Jenis program bimbel tidak boleh kosong.");
                }
            } else {
                System.out.println("Hanya admin yang bisa menambahkan Program Bimbel.");
            }
        }
        return isInserted;
    }

    public Object getCourseProgram(Integer id_program_bimbel) {
        if (id_program_bimbel != null) {
            String sql = "SELECT * FROM program_bimbel WHERE id_program_bimbel = ?";
            try {
                ResultSet rs = dbManager.executeQuery(sql, id_program_bimbel);
                if (rs.next()) {
                    CourseProgram courseProgram = new CourseProgram(rs.getInt("id_program_bimbel"),
                            rs.getString("jenis_program_bimbel"), rs.getInt("biaya"), rs.getInt("id_admin"));
                    return courseProgram;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            List<CourseProgram> coursePrograms = new ArrayList<>();
            String sql = "SELECT * FROM program_bimbel";
            try {
                ResultSet rs = dbManager.executeQuery(sql);
                while (rs.next()) {
                    coursePrograms.add(new CourseProgram(rs.getInt("id_program_bimbel"),
                            rs.getString("jenis_program_bimbel"), rs.getInt("biaya"), rs.getInt("id_admin")));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return coursePrograms;
        }
        return id_program_bimbel;
    }

    public boolean deleteCourseProgram(int id_program_bimbel) {
        boolean isDeleted = false;
        if (isAdmin()) {
            String sql = "DELETE FROM program_bimbel WHERE id_program_bimbel = ?";
            try {
                dbManager.executeUpdate(sql, id_program_bimbel);
                isDeleted = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Hanya admin yang bisa menghapus Program Bimbel.");
        }
        return isDeleted;
    }

    public boolean updateCourseProgram() {
        if (isAdmin()) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Pilih id program yang ingin di ubah:");
                int id_program_bimbel = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Masukkan jenis program bimbel baru (kosongkan jika tidak ingin mengubah):");
                String new_jenis_program_bimbel = scanner.nextLine();

                System.out.println("Masukkan biaya baru (kosongkan jika tidak ingin mengubah):");
                int new_biaya = 0;
                String new_biaya_str = scanner.nextLine();

                CourseProgram currentCourseProgram = (CourseProgram) getCourseProgram(id_program_bimbel);

                if (currentCourseProgram != null) {
                    new_jenis_program_bimbel = new_jenis_program_bimbel.isEmpty()
                            ? currentCourseProgram.getJenis_program_bimbel()
                            : new_jenis_program_bimbel;
                    new_biaya = new_biaya_str.isEmpty() ? currentCourseProgram.getBiaya()
                            : Integer.parseInt(new_biaya_str);
                } else {
                    System.out.println("No course program found with the provided id_program_bimbel.");
                    return false;
                }

                String sql = "UPDATE program_bimbel SET jenis_program_bimbel = ?, biaya = ? WHERE id_program_bimbel = ?";
                try {
                    dbManager.executeUpdate(sql, new_jenis_program_bimbel, new_biaya, id_program_bimbel);
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            System.out.println("Hanya admin yang bisa memperbarui Program Bimbel.");
            return false;
        }
    }

    public boolean isAdmin() {
        // Check if the user is an admin
        boolean isAdmin = admin.getUsername().equals("admin") && admin.getPassword().equals("admin");
        return isAdmin;
    }
}