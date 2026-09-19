package com.depi.store;

/**
 * Abstract middle class for every clothing product.
 * Extends Item and adds the attributes shared by all clothing items.
 */
public abstract class Clothing extends Item {

    private String size;
    private String color;

    protected Clothing(String name, double price, int availableCopies, String size, String color) {
        super(name, price, availableCopies);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
