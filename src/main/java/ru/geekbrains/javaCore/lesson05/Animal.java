package ru.geekbrains.javaCore.lesson05;

// Создать классы Собака и Кот с наследованием от класса Животное.
// Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
// Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
// У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
// Добавить подсчет созданных котов, собак и животных.

public abstract class Animal {
    static private int count = 0;

    public Animal() {
        super();
        count++;
    }

    public static int getCount() {
        return count;
    }

    public abstract void run(int howLong);

    public abstract void swim(int howLong);

}
