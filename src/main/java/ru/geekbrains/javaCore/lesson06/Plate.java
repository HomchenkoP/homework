package ru.geekbrains.javaCore.lesson06;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int portion) {
        // Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
        if (portion > food)
            // Считаем, что если коту мало еды в тарелке, то он её просто не трогает
            return false;
        else
            food -= portion;
        return true;
    }

    // Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
    public void fillPlate(int food)
    {
        this.food += food;
        System.out.println("В тарелку добавили "+food+".");
    }

    public void info() {
        System.out.println("В тарелке "+food+".");
    }
}
