package com.example.marvelstore.model;

public class ComicPrice {

    private String type;
    private float price;

    public ComicPrice(String type, float price) {
        this.type = type;
        this.price = price;
    }

    public ComicPrice() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
