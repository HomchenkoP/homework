package ru.geekbrains.javaCore.lesson07;

import java.util.Random;

// Препятствие беговая дорожка

public class Track implements Obstacleable {

    private int length;
    public static Random rand = new Random();

    public Track() {
        this.length = rand.nextInt(100) + 1;
    }

    @Override
    public int getDifficulty() {
        return this.length;
    }
}

