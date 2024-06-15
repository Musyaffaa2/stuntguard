package com.example.stuntguard;

public class User {
    private String name;
    private String height;
    private String weight;
    private String medicalHistory;
    private String profileImageUrl;

    // Constructor
    public User(String name, String height, String weight, String medicalHistory, String profileImageUrl) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.medicalHistory = medicalHistory;
        this.profileImageUrl = profileImageUrl;
    }

    public User(){

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    // Setters (if needed)
    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
