package com.depi.store;

/**
 * Abstract base class for every product the store sells.
 * Holds the attributes and behavior common to all items, and leaves
 * getCategory() to be implemented by each concrete leaf class
 * (polymorphism: calling getCategory() on an Item reference invokes
 * the correct override at runtime).
 */
public abstract class Item {

    private String name;
    private double price;
    private int availableCopies;

    protected Item(String name, double price, int availableCopies) {
        this.name = name;
        this.price = price;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * Decrements available stock by 1 if a copy is available.
     * Throws OutOfStockException if there is no stock left.
     */
    public void sell() {
        if (availableCopies <= 0) {
            throw new OutOfStockException(name + " is out of stock.");
        }
        availableCopies--;
    }

    /** Increments available stock by 1 (a returned unit). */
    public void returnItem() {
        availableCopies++;
    }

    /** Each concrete product reports its own category label, e.g. "Clothing - Shirt". */
    public abstract String getCategory();

    @Override
    public String toString() {
        return String.format("%-18s [%-18s] $%-8.2f stock:%d", name, getCategory(), price, availableCopies);
    }
}
