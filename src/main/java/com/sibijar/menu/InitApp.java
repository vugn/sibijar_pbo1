package com.sibijar.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.sibijar.admin.Admin;
import com.sibijar.features.category.CategoryManager;
import com.sibijar.features.information.InformationManager;
import com.sibijar.service.auth.AuthManager;
import com.sibijar.service.database.DatabaseManager;
import com.sibijar.service.utils.RainbowPrinter;

public class InitApp {
    private Scanner scanner;
    private DatabaseManager databaseManager;
    private AuthManager authManager;
    private Admin admin;
    private CategoryManager categoryManager;
    private InformationManager informationManager;
    private boolean isAuthenticated = false;

    public InitApp() throws SQLException, IOException {
        scanner = new Scanner(System.in);
        databaseManager = new DatabaseManager();
        authManager = new AuthManager(databaseManager);

        // Print Rainbow SIBIJAR
        RainbowPrinter printer = new RainbowPrinter();
        printer.printRainbow("SI BIJAR");

        System.out.print("Masukkan username Admin: ");
        String username = scanner.nextLine();
        System.out.flush();

        System.out.print("Masukkan password Admin: ");
        String password = scanner.next();
        scanner.nextLine();
        System.out.print("\033[H\033[2J"); // Clear console
        System.out.flush();

        admin = new Admin(1, username, password, "");

        // Authenticate the Admin
        try {
            Admin authenticatedAdmin = authManager.authenticate(admin.getUsername(), admin.getPassword());
            if (authenticatedAdmin != null) {
                admin = authenticatedAdmin;
                isAuthenticated = true;
                System.out.println("Admin: " + admin.getNama_lengkap_admin());
            } else {
                throw new Exception("Admin gagal di autentikasi.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Admin gagal di autentikasi.");
        }

        // Create a new CategoryManager object
        categoryManager = new CategoryManager(admin, databaseManager);
        informationManager = new InformationManager(admin, databaseManager, categoryManager);
    }

    public void run() {
        if (!isAuthenticated) {
            System.out.println("Aplikasi Gagal dijalankan karena Authentikasi gagal.");
            return;
        }

        Menu menu = new Menu(categoryManager, informationManager);
        menu.display();
    }
}