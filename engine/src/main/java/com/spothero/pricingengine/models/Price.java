package com.spothero.pricingengine.models;

public class Price {
    private String price;

    public Price() {
    }

    public Price(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
