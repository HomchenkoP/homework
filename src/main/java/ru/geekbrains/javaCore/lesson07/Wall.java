package ru.geekbrains.javaCore.lesson07;

import java.util.Random;

// Препятствие стена

public class Wall implements Obstacleable {

    private int height;
    public static Random rand = new Random();

    public Wall() {
        this.height = rand.nextInt(3) + 1;
    }

    @Override
    public int getDifficulty() {
        return this.height;
    }
}
