package com.sibijar;

import com.sibijar.menu.InitApp;

public class App {
    public static void main(String[] args) {
        try {
            InitApp initApp = new InitApp();
            initApp.run();
        } catch (Exception e) {
            System.out.println("Application closing due to error: " + e.getMessage());
            System.exit(1);
        }
    }
}