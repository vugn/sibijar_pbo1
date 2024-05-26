package com.sibijar;

import java.util.Scanner;

import com.sibijar.admin.Admin;
import com.sibijar.category.CategoryManager;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a new Admin object
        System.out.println("Masukkan username Admin:");
        String username = scanner.nextLine();
        System.out.println("Masukkan password Admin:");
        String password = scanner.nextLine();
        Admin admin = new Admin(1, username, password, "Nama Lengkap Admin");

        // Create a new CategoryManager object
        CategoryManager categoryManager = new CategoryManager(admin);

        if (categoryManager.isAdmin()) {
            System.out.println("Masukan nama Kategori (ketik 'keluar' untuk berhenti):");
            while (true) {
                String categoryName = scanner.nextLine();
                if (categoryName.equalsIgnoreCase("keluar")) {
                    break;
                }
                categoryManager.addCategory(categoryManager.getCategories().size() + 1, categoryName);
            }

            // Show categories
            categoryManager.showCategories();
        } else {
            System.out.println("Akses ditolak, hanya Admin yang bisa menambahkan Kategori");
        }

        scanner.close();
    }
}