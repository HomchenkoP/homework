package ru.geekbrains.javaCore.lesson11;

// Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
// вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);

public class Orange extends Fruit {

    private final float weight = 1.5f;

    @Override
    public float getWeight() {
        return weight;
    }
}