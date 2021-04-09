package com.example.marvelstore.model;

public class SeriesSummary {

    private String resourceURI;
    private String name;

    public SeriesSummary(String resourceURI, String name) {
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public SeriesSummary() {

    }



    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
