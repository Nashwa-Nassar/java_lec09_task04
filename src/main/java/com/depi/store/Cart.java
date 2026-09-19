package com.depi.store;

import java.util.ArrayList;
import java.util.List;

/**
 * A shopping cart holding a list of Items a customer intends to buy.
 * Aggregation: a Cart has-a List<Item>, but doesn't own the Item
 * objects' lifecycle - they still exist in the store inventory whether
 * or not they're currently in someone's cart.
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    /** Sum of the price of every item currently sitting in the cart. */
    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Calls sell() on every item in the cart. Items that sell successfully
     * are charged and removed from the cart; any item that turns out to be
     * out of stock is reported and left in the cart instead of failing the
     * whole checkout.
     */
    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty - nothing to check out.");
            return;
        }

        List<Item> sold = new ArrayList<>();
        double total = 0;

        System.out.println("----- Receipt -----");
        for (Item item : items) {
            try {
                item.sell();
                sold.add(item);
                total += item.getPrice();
                System.out.println(String.format("  %-18s $%.2f", item.getName(), item.getPrice()));
            } catch (OutOfStockException e) {
                System.out.println("  Skipped " + item.getName() + ": " + e.getMessage());
            }
        }
        System.out.println(String.format("Total charged: $%.2f", total));
        System.out.println("--------------------");

        items.removeAll(sold);

        if (!items.isEmpty()) {
            System.out.println(items.size() + " item(s) could not be sold and remain in your cart.");
        }
    }
}
