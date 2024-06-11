package com.example.stuntguard;

public class ContainerAnak {
    private int id;
    private String name;

    private String lastUpdated;

    private double beratBadan;

    private double tinggiBadan;

    private double lingkarKepala;

    private String imageUrl;

    public ContainerAnak(int id, String name, String lastUpdated, String imageUrl) {
        this.id = id;
        this.name = name;
        this.lastUpdated = lastUpdated;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
