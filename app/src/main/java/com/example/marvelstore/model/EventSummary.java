package com.example.marvelstore.model;

public class EventSummary {

    private String resourceURI;
    private String name;

    public EventSummary(String resourceURI, String name) {
        this.resourceURI = resourceURI;
        this.name = name;
    }

    public EventSummary() {

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
