package ru.geekbrains.javaCore.lesson07;

import java.util.Random;

// Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и прыгать
// (методы просто выводят информацию о действии в консоль).
// У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.

public class Cat implements Participantable {

    private String name;
    private int runAbility;
    private int jumpAbility;
    public static Random rand = new Random();

    public Cat (String name) {
        this.name = name;
        this.runAbility = rand.nextInt(100);
        this.jumpAbility = rand.nextInt(3);
    }

    public void info() {
        System.out.println("Кот " + name + " может пробежать " + this.runAbility + " и перепрыгнуть " + this.jumpAbility + ".");
    }

    @Override
    public boolean run(Obstacleable o) {
        if (this.runAbility >= ((Track)o).getDifficulty()) {
            System.out.println(name + " пробежал " + ((Track)o).getDifficulty() + ".");
            return true;
        }
        System.out.println(name + " не смог " + ((Track)o).getDifficulty() + ", выдохся.");
        return false;
    }

    @Override
    public boolean jump(Obstacleable o) {
        if (this.jumpAbility >= ((Wall)o).getDifficulty()) {
            System.out.println(name + " перепрыгнул " + ((Wall)o).getDifficulty() + ".");
            return true;
        }
        System.out.println(name + " не смог " + ((Wall)o).getDifficulty() + ", упал и подняться не смог.");
        return false;
    }
}

