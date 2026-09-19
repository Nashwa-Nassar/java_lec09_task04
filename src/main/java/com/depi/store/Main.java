package com.depi.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DEPI - Software Testing Track - Session 09 - Task A
 * E-commerce inventory and shopping cart management system.
 *
 * Interactive console (CLI) entry point. Presents a menu loop backed by
 * java.util.Scanner and drives the Item/Clothing/Device hierarchy and
 * the Cart through browsing, adding to cart, viewing the cart, checkout,
 * and returning stock.
 */
public class Main {

    private static final List<Item> inventory = new ArrayList<>();
    private static final Cart cart = new Cart();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedInventory();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewInventory();
                    break;
                case "2":
                    addItemToCart();
                    break;
                case "3":
                    viewCart();
                    break;
                case "4":
                    cart.checkout();
                    break;
                case "5":
                    returnItemToStock();
                    break;
                case "6":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice - please enter a number from 1 to 6.");
            }
            System.out.println();
        }
        scanner.close();
    }

    /** Pre-populates the store with a small catalog covering every concrete class. */
    private static void seedInventory() {
        inventory.add(new Shirt("Classic Tee", 15.99, 10, "M", "White", "Cotton"));
        inventory.add(new Socks("Sport Socks 3-Pack", 8.50, 20, 3));
        inventory.add(new Hat("Summer Cap", 12.00, 15, "Snapback"));
        inventory.add(new Printer("OfficeJet 200", 150.00, 5, true));
        inventory.add(new Laptop("UltraBook 14", 899.99, 3, 16));
        inventory.add(new Projector("BeamMax X1", 320.00, 4, 3000));
    }

    private static void printMenu() {
        System.out.println("===== Store Menu =====");
        System.out.println("1. View Store Inventory");
        System.out.println("2. Add Item to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Return Item");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    /** Displays every item's stock, category (polymorphic getCategory()) and details. */
    private static void viewInventory() {
        System.out.println("--- Store Inventory ---");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println("[" + i + "] " + inventory.get(i));
        }
    }

    private static void addItemToCart() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        viewInventory();
        System.out.print("Enter the index of the item to add to cart: ");
        Integer index = readValidIndex(inventory.size());
        if (index == null) return;

        Item selected = inventory.get(index);
        cart.addItem(selected);
        System.out.println(selected.getName() + " added to cart.");
    }

    private static void viewCart() {
        System.out.println("--- Your Cart ---");
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (Item item : cart.getItems()) {
            System.out.println("  " + item + " (category: " + item.getCategory() + ")");
        }
        System.out.println(String.format("Total: $%.2f", cart.calculateTotal()));
    }

    private static void returnItemToStock() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        viewInventory();
        System.out.print("Enter the index of the item to return to stock: ");
        Integer index = readValidIndex(inventory.size());
        if (index == null) return;

        Item selected = inventory.get(index);
        selected.returnItem();
        System.out.println(selected.getName() + " returned. New stock: " + selected.getAvailableCopies());
    }

    /** Reads a line, parses it as an int, and validates it's within [0, size). */
    private static Integer readValidIndex(int size) {
        String line = scanner.nextLine().trim();
        try {
            int index = Integer.parseInt(line);
            if (index < 0 || index >= size) {
                System.out.println("Invalid index - out of range.");
                return null;
            }
            return index;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return null;
        }
    }
}
