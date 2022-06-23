package com.stocking;

public class StockItem {
    private String name;
    private int numInStock;
    private double price;
    private String description;

    public StockItem(String name, int numInStock, double price, String description) {
        this.name = name;
        this.numInStock = numInStock;
        this.price = price;
        this.description = description;
    }

    public int getNum() {
        return numInStock;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return ("\n"
                + "\t"  + this.name
                + "\t"  + this.numInStock
                + "\t"  + this.price
                + "\t"  + this.description

        );
    }
}
