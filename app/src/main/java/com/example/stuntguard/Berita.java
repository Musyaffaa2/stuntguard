package com.example.stuntguard;

public class Berita {
    private int imageResource;
    private String title;
    private String content;

    public Berita(int imageResource, String title, String content) {
        this.imageResource = imageResource;
        this.title = title;
        this.content = content;
    }

    public int getImageResource() {
        return imageResource;
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
}
