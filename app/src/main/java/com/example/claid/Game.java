package com.example.claid;


import android.graphics.Bitmap;

public class Game {
    private String name;
    private Bitmap imageSource;

    public Game (Bitmap imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImageSource() {
        return imageSource;
    }
}
