package com.depi.store;

public class Laptop extends Device {

    private int ramGB;

    public Laptop(String name, double price, int availableCopies, String brand, int warrantyMonths, int ramGB) {
        super(name, price, availableCopies, brand, warrantyMonths);
        this.ramGB = ramGB;
    }

    public Laptop(String name, double price, int availableCopies, int ramGB) {
        this(name, price, availableCopies, "Generic", 12, ramGB);
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    @Override
    public String getCategory() {
        return "Device - Laptop";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | brand:%s warranty:%dmo ram:%dGB",
                getBrand(), getWarrantyMonths(), ramGB);
    }
}
