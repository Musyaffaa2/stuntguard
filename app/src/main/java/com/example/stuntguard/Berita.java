package com.example.stuntguard;

public class Berita {
    private String imageUrl;
    private String title;
    private String content;

    public Berita() {
        // Default constructor required for calls to DataSnapshot.getValue(Berita.class)
    }

    public Berita(String imageUrl, String title, String content) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}