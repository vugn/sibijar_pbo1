package com.sibijar.menu;

import java.util.List;
import java.util.Scanner;

import com.sibijar.features.category.Category;
import com.sibijar.features.category.CategoryManager;
import com.sibijar.features.information.Information;
import com.sibijar.features.information.InformationManager;

public class Menu {
    private CategoryManager categoryManager;
    private InformationManager informationManager;

    private Scanner scanner;

    public Menu(CategoryManager categoryManager, InformationManager informationManager) {
        this.categoryManager = categoryManager;
        this.informationManager = informationManager;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Kategori");
            System.out.println("2. Informasi");
            System.out.println("3. Program Bimbel");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    displayCategoryMenu();
                    break;
                case "2":
                    displayInformationMenu();
                    break;
                case "3":
                    displayProgramMenu();
                    break;
                case "4":
                    System.out.println("Keluar dari aplikasi.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silahkan coba lagi.");
            }
        }
    }

    private void displayCategoryMenu() {
        while (true) {
            System.out.println("\nSubmenu Kategori:");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Hapus Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addCategory();
                    break;
                case "2":
                    viewCategory();
                    break;
                case "3":
                    deleteCategory();
                    break;
                case "4":
                    updateCategory();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silahkan coba lagi.");
            }
        }
    }

    private void displayInformationMenu() {
        while (true) {
            System.out.println("\nSubmenu Informasi:");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Hapus Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addInformation();
                    break;
                case "2":
                    viewInformation();
                    break;
                case "3":
                    deleteInformation();
                    break;
                case "4":
                    updateInformation();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silahkan coba lagi.");
            }
        }
    }

    private void addInformation() {
        if (informationManager.addInformation()) {
            System.out.println("Informasi berhasil ditambahkan.");
            viewInformation();
        } else {
            System.out.println("Informasi gagal ditambahkan.");
        }
    }

    @SuppressWarnings("unchecked")
    private void viewInformation() {
        List<Information> informationResult = (List<Information>) informationManager.getInformation(null);
        System.out.println(
                "\n------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-30s | %-30s | %-30s | %-10s | %-10s |\n",
                "ID Informasi", "Nama Informasi", "Gambar", "Isi", "Tanggal Posting", "ID Kategori", "ID Admin");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Information information : informationResult) {
            System.out.printf("| %-10s | %-30s | %-30s | %-30s | %-30s | %-10s | %-10s |\n",
                    information.getId_info(),
                    information.getNm_info(),
                    information.getGambar(),
                    information.getIsi(),
                    information.getTgl_posting(),
                    information.getId_kategori(),
                    information.getId_admin());
        }
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void deleteInformation() {
        System.out.print("Masukkan ID Informasi yang akan dihapus: ");
        int informationId = scanner.nextInt();
        scanner.nextLine();
        if (informationManager.deleteInformation(informationId)) {
            System.out.println("Informasi berhasil dihapus.");
            viewInformation();
        } else {
            System.out.println("Informasi gagal dihapus.");
        }
    }

    private void updateInformation() {
        if (informationManager.updateInformation()) {
            System.out.println("Informasi berhasil diubah.");
            viewInformation();
        } else {
            System.out.println("Informasi gagal diubah.");
        }
    }

    private void displayProgramMenu() {
        System.out.println("Program Bimbel belum tersedia.");
    }

    private void addCategory() {
        System.out.println("Masukan nama Kategori (ketik 'keluar' untuk berhenti):");
        while (true) {
            String categoryName = scanner.nextLine();
            if (categoryName.equalsIgnoreCase("keluar")) {
                break;
            }
            categoryManager.addCategory(categoryManager.getCategories().size() + 1, categoryName);
        }
        viewCategory();
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

    private void deleteCategory() {
        System.out.print("Masukkan ID Kategori yang akan dihapus: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        categoryManager.deleteCategory(categoryId);
        viewCategory();
    }

    private void updateCategory() {
        System.out.print("Masukkan ID Kategori yang akan diubah: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Masukkan nama Kategori baru: ");
        String categoryName = scanner.nextLine();
        categoryManager.updateCategory(categoryId, categoryName);
        viewCategory();
    }
}