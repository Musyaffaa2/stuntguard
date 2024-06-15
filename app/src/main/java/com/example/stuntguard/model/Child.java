package com.example.stuntguard.model;

public class Child {

    private String key;
    private String name;
    private String birthDate;
    private float height;
    private float weight;
    private float headCircumference;
    private String JenisKelamin;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    // Default constructor diperlukan untuk Firebase
    public Child() {}

    public Child(String name, String birthDate, float height, float weight, float headCircumference,String jenisKelamin) {
        this.name = name;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.headCircumference = headCircumference;
        this.JenisKelamin = jenisKelamin;
    }

    // Getter dan Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public float getHeight() { return height; }
    public void setHeight(float height) { this.height = height; }

    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }

    public float getHeadCircumference() { return headCircumference; }
    public void setHeadCircumference(float headCircumference) { this.headCircumference = headCircumference; }
}
