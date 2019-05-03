package com.random.wahaj.soundboard;

/**
 * Created by Wahaj on 1/14/2018.
 */

public class Meme {
    String name;
    String category;
    String assetName;

    public Meme(String name, String assetName, String category) {
        this.name = name;
        this.assetName = assetName;
        this.category = category;
    }

    public Meme(String name, String assetName) {
        this.name = name;
        this.assetName = assetName;
    }
}
