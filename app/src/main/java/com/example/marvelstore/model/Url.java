package com.example.marvelstore.model;

public class Url {
    private String type;
    private String url;

    public Url(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public Url() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
