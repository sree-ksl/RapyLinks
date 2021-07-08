package com.ksl.payrapyd;

public class ProductItem {
    String name;
    String price;

    public ProductItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
