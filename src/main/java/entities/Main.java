package entities;

import entities.*;
import service.Warehouse;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Warehouse warehouse = new Warehouse();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> modifyProduct();
                case 3 -> getAllProducts();
                case 4 -> getProductById();
                case 5 -> getProductsByCategory();
                case 6 -> getProductsCreatedAfter();
                case 7 -> getProductsModifiedAfterCreation();
                case 0 -> {
                    System.out.println("Avslutar programmet...");
                    running = false;
                }
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \n--- Warehouse Menu ---
                1. Lägg till ny produkt
                2. Modifiera en produkt
                3. Visa alla produkter
                4. Hämta produkt genom id
                5. Hämta produkter efter kategori
                6. Hämta produkter skapade efter ett datum
                7. Hämta produkter som modifierats efter att de skapats
                0. Avsluta
                """);
        System.out.print("Välj ett alternativ: ");
    }

    private static void addProduct() {
        System.out.print("Ange produkt-id: ");
        String id = scanner.nextLine();
        System.out.print("Ange produktnamn: ");
        String name = scanner.nextLine();
        System.out.print("Ange kategori (ELECTRONICS, FURNITURE, CLOTHING, FOOD, BOOKS): ");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Ange betyg (0-10): ");
        int rating = Integer.parseInt(scanner.nextLine());
        LocalDate now = LocalDate.now();
        Product product = new Product(id, name, category, rating, now, now);
        warehouse.addProduct(product);
        System.out.println("Produkt tillagd.");
    }

    private static void modifyProduct() {
        System.out.print("Ange produkt-id för den produkt som ska modifieras: ");
        String id = scanner.nextLine();
        System.out.print("Ange nytt namn: ");
        String newName = scanner.nextLine();
        System.out.print("Ange ny kategori (ELECTRONICS, FURNITURE, CLOTHING, FOOD, BOOKS): ");
        Category newCategory = Category.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Ange nytt betyg (0-10): ");
        int newRating = Integer.parseInt(scanner.nextLine());
        warehouse.modifyProduct(id, newName, newCategory, newRating);
        System.out.println("Produkt modifierad.");
    }

    private static void getAllProducts() {
        List<Product> products = warehouse.getAllProducts();
        products.forEach(product -> System.out.println(product));
    }

    private static void getProductById() {
        System.out.print("Ange produkt-id: ");
        String id = scanner.nextLine();
        try {
            Product product = warehouse.getProductById(id);
            System.out.println(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getProductsByCategory() {
        System.out.print("Ange kategori (ELECTRONICS, FURNITURE, CLOTHING, FOOD, BOOKS): ");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());
        List<Product> products = warehouse.getProductsByCategory(category);
        products.forEach(product -> System.out.println(product));
    }

    private static void getProductsCreatedAfter() {
        System.out.print("Ange ett datum (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        List<Product> products = warehouse.getProductsCreatedAfter(date);
        products.forEach(product -> System.out.println(product));
    }

    private static void getProductsModifiedAfterCreation() {
        List<Product> products = warehouse.getProductsModifiedAfterCreation();
        products.forEach(product -> System.out.println(product));
    }
}
