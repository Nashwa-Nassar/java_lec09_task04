package com.depi.store;

public class Hat extends Clothing {

    private String style;

    public Hat(String name, double price, int availableCopies, String size, String color, String style) {
        super(name, price, availableCopies, size, color);
        this.style = style;
    }

    public Hat(String name, double price, int availableCopies, String style) {
        this(name, price, availableCopies, "One Size", "Black", style);
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String getCategory() {
        return "Clothing - Hat";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | size:%s color:%s style:%s", getSize(), getColor(), style);
    }
}
