package ru.geekbrains.javaCore.lesson05;

// Создать классы Собака и Кот с наследованием от класса Животное.
// Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
// Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
// У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
// Добавить подсчет созданных котов, собак и животных.

public class Cat extends Animal {
    private static int count = 0;
    private String name;

    public Cat (String name) {
        super();
        count++;
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run(int howLong) {
        if (howLong > 0 && howLong <= 200)
            System.out.println("Кошка "+name+" пробежала "+howLong+"м");
        else if (howLong > 200)
            System.out.println("Кошка "+name+" пробежала только 500м и упала");
    }

    @Override
    public void swim(int howLong) {
        if (howLong > 0)
            System.out.println("Кошка "+name+" не умеет плавать, сразу тонет");
    }
}
