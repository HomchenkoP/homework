package ru.geekbrains.javaCore.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

// Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
//
// Написать метод, который преобразует массив в ArrayList;
//
// Задача:
// a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
// b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
// c. Для хранения фруктов внутри коробки можно использовать ArrayList;
// d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
// вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
// e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare()
// в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
// f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
// Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
// Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
// g. Не забываем про метод добавления фрукта в коробку.

public class Homework11 {

    public static <T> void method1(T[] arr, int position1, int position2) {
        if (position1 < 0 || position1 > arr.length) {
            System.out.println("Некорректное значение праметра 'position1'");
            return;
        }
        if (position2 < 0 || position2 > arr.length) {
            System.out.println("Некорректное значение праметра 'position2'");
            return;
        }
        System.out.println("Меняем местами элементы '" + position1 + "' и '" + position2 + "'");
        T tempVal = arr[position1];
        arr[position1] = arr[position2];
        arr[position2] = tempVal;
    }

    public static <T> ArrayList<T> method2(T[] arr) {
        ArrayList<T> list = new ArrayList<>();
        for(T t : arr)
            // чтобы убедиться, что код работает, добавляем элементы в список в обратном порядке
            list.add(0, t);
        return list;
    }

    public static void main(String[] args) {

        String[] arr1 = {"один", "два", "три", "четыре", "пять"};
        System.out.println("Массив был: " + Arrays.toString(arr1));
        method1(arr1, 2,4);
        System.out.println("Массив стал: " + Arrays.toString(arr1));

        ArrayList<String> list1 = method2(arr1);
        System.out.println("Список из массива: " + list1.toString());
        System.out.println();

        Integer[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Массив был: " + Arrays.toString(arr2));
        method1(arr2, 1,3);
        System.out.println("Массив стал: " + Arrays.toString(arr2));

        ArrayList<Integer> list2 = method2(arr2);
        System.out.println("Список из массива: " + list2.toString());
        System.out.println();

        Box<Apple> box1 = new Box<>(new Apple());
        Box<Orange> box2 = new Box<>(new Orange(), new Orange());
        Box<Apple> box3 = new Box<>(new Apple(), new Apple(), new Apple());

        System.out.println("Коробка1 весит " + box1.getWeight());
        System.out.println("Коробка2 весит " + box2.getWeight());
        System.out.println("Коробка3 весит " + box3.getWeight());
        System.out.println();

        if (box1.compare(box2))
            System.out.println("Коробка1 весит как Коробка2");
        else
            System.out.println("Коробка1 и Коробка2 имеют разный вес");
        if (box2.compare(box3))
            System.out.println("Коробка2 весит как Коробка3");
        else
            System.out.println("Коробка2 и Коробка3 имеют разный вес");
        if (box3.compare(box1))
            System.out.println("Коробка3 весит как Коробка1");
        else
            System.out.println("Коробка3 и Коробка1 имеют разный вес");
        System.out.println();

        System.out.println("Пересыпали фрукты из одной коробки в другую");
        //box1.transferAll(box2); // ошибка компиляции
        box1.transferAll(box3);

        System.out.println("Коробка1 весит " + box1.getWeight());
        System.out.println("Коробка2 весит " + box2.getWeight());
        System.out.println("Коробка3 весит " + box3.getWeight());
        System.out.println();
    }
}
