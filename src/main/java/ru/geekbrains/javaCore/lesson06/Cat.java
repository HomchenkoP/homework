package ru.geekbrains.javaCore.lesson06;

public class Cat {
    private String name;
    private int appetite;
    // Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false;
    }

    public void eat(Plate p) {
        // Если коту удалось покушать (хватило еды), сытость = true.
        fullness = p.decreaseFood(appetite);
        if (fullness)
            System.out.println("Кот "+name+" съел "+appetite+".");
        else
            System.out.println("Слишком мало еды для кота "+name+".");
    }

    public void info() {
        System.out.println("Кот "+name+((fullness)?" сыт":" голоден."));
    }
}
