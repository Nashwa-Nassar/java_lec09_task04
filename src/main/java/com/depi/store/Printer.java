package com.depi.store;

public class Printer extends Device {

    private boolean isColor;

    public Printer(String name, double price, int availableCopies, String brand, int warrantyMonths, boolean isColor) {
        super(name, price, availableCopies, brand, warrantyMonths);
        this.isColor = isColor;
    }

    public Printer(String name, double price, int availableCopies, boolean isColor) {
        this(name, price, availableCopies, "Generic", 12, isColor);
    }

    public boolean isColor() {
        return isColor;
    }

    public void setColor(boolean color) {
        isColor = color;
    }

    @Override
    public String getCategory() {
        return "Device - Printer";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | brand:%s warranty:%dmo color:%s",
                getBrand(), getWarrantyMonths(), isColor ? "Yes" : "No");
    }
}
