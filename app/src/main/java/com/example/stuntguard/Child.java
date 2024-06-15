package com.example.stuntguard;

public class Child {
    private String key;
    private String name;
    private String birthDate;
    private String gender;
    private float beratBadan;
    private float tinggiBadan;
    private float lingkarKepala;
    private String lastUpdated;
    private int age;

    public String getChildImageUrl() {
        return childImageUrl;
    }

    public void setChildImageUrl(String childImageUrl) {
        this.childImageUrl = childImageUrl;
    }

    private String childImageUrl;


    public Child(String name, String birthDate, String gender, float beratBadan, float tinggiBadan, float lingkarKepala, int age) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
        this.lingkarKepala = lingkarKepala;
        this.age = age;
        this.childImageUrl = childImageUrl;
    }


    public Child() {

    }

    // Getter and Setter methods
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(float beratBadan) {
        this.beratBadan = beratBadan;
    }

    public float getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(float tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public float getLingkarKepala() {
        return lingkarKepala;
    }

    public void setLingkarKepala(float lingkarKepala) {
        this.lingkarKepala = lingkarKepala;
    }

    public int getAge() {
        return age;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
