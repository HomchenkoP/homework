package ru.geekbrains.javaCore.lesson05;

// Создать классы Собака и Кот с наследованием от класса Животное.
// Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
// Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
// У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
// Добавить подсчет созданных котов, собак и животных.

public class Homework05 {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Герда");
        Dog dog2 = new Dog("Джесси");
        Dog dog3 = new Dog("Марта");

        Cat cat1 = new Cat("Мурка");
        Cat cat2 = new Cat("Клёпа");
        Cat cat3 = new Cat("Шанель");
        Cat cat4 = new Cat("Ириска");

        System.out.println("Всего животных - "+Animal.getCount()+" из них собак - "+Dog.getCount()+", а кошек - "+Cat.getCount());

        cat1.run(150);
        dog1.swim(9);
        dog3.run(11);
        cat2.run(201);
        dog3.swim(13);
        cat4.swim(1);
    }
}
