package com.example.claid;


import android.graphics.Bitmap;

public class Game3d {
    private String name;
    private Bitmap imageSource;

    public Game3d(Bitmap imageSource, String name) {
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
