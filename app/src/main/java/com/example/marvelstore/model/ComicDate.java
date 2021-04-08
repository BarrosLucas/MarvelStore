package com.example.marvelstore.model;


public class ComicDate {
    private String type;
    private String date;

    public ComicDate(String type, String date) {
        this.type = type;
        this.date = date;
    }

    public ComicDate(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
