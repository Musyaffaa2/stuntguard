package com.example.stuntguard;

public class Menu {
    private String imageUrl;
    private String title;
    private String recipe;

    public Menu() { }

    public Menu(String imageUrl, String title, String recipe) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.recipe = recipe;
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

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
