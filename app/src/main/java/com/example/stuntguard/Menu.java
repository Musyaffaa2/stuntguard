package com.example.stuntguard;

public class Menu {
    private String imageUrl;
    private String title;
    private String resep;

    public Menu() { }

    public Menu(String imageUrl, String title, String resep) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.resep = resep;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResep() {
        return resep;
    }

    public void setResep(String resep) {
        this.resep = resep;
    }
}
