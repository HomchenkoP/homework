package ru.geekbrains.javaCore.lesson09;

public class Cat {

    private int id;
    private String name;
    private int age;
    private String breed;
    private String colour;

    public Cat(String name, int age, String breed, String colour) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.colour = colour;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
