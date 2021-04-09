package com.example.marvelstore.model;

public class ComicToCart {
    private String title;
    private float priceUnity;
    private String urlImg;
    private int amount;
    private int code;

    public ComicToCart(String title, float priceUnity, String urlImg, int amount, int code) {
        this.code = code;
        this.title = title;
        this.priceUnity = priceUnity;
        this.urlImg = urlImg;
        this.amount = amount;
    }

    public ComicToCart(){}

    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPriceUnity() {
        return priceUnity;
    }

    public void setPriceUnity(float priceUnity) {
        this.priceUnity = priceUnity;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
