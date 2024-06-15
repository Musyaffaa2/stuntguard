package com.example.stuntguard;

public class ContainerAnak {
    private int id;
    private String name;

    private String lastUpdated;

    private double beratBadan;

    private double tinggiBadan;

    private double lingkarKepala;

    private String imageUrl;
    private int usia;

    public ContainerAnak(int id, String name, String lastUpdated, String imageUrl, double beratBadan, double tinggiBadan, double lingkarKepala, int usia) {
        this.id = id;
        this.name = name;
        this.lastUpdated = lastUpdated;
        this.imageUrl = imageUrl;
        this.usia=usia;
        this.beratBadan=beratBadan;
        this.lingkarKepala=lingkarKepala;
        this.tinggiBadan=tinggiBadan;
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

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(double beratBadan) {
        this.beratBadan = beratBadan;
    }

    public double getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(double tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public double getLingkarKepala() {
        return lingkarKepala;
    }

    public void setLingkarKepala(double lingkarKepala) {
        this.lingkarKepala = lingkarKepala;
    }
}
