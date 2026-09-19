package com.depi.store;

public class Socks extends Clothing {

    private int pairsInPack;

    public Socks(String name, double price, int availableCopies, String size, String color, int pairsInPack) {
        super(name, price, availableCopies, size, color);
        this.pairsInPack = pairsInPack;
    }

    public Socks(String name, double price, int availableCopies, int pairsInPack) {
        // socks are typically one-size-fits-all; default size/color kept generic
        this(name, price, availableCopies, "One Size", "Assorted", pairsInPack);
    }

    public int getPairsInPack() {
        return pairsInPack;
    }

    public void setPairsInPack(int pairsInPack) {
        this.pairsInPack = pairsInPack;
    }

    @Override
    public String getCategory() {
        return "Clothing - Socks";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | size:%s color:%s pairsInPack:%d", getSize(), getColor(), pairsInPack);
    }
}
