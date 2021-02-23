package ru.geekbrains.javaCore.lesson12;

import java.util.Arrays;

// Необходимо написать два метода, которые делают следующее:
// 1) Создают одномерный длинный массив, например:
//    static final int SIZE = 10 000 000;
//    static final int HALF = size / 2;
//    float[] arr = new float[size].
// 2) Заполняют этот массив единицами.
// 3) Засекают время выполнения: long a = System.currentTimeMillis().
// 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
//
//    arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//
//  5) Проверяется время окончания метода System.currentTimeMillis().
//  6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
//
//  Отличие первого метода от второго:
//  Первый просто бежит по массиву и вычисляет значения.
//  Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

public class Homework12 {

    private static final int SIZE = 10_000_000;
    private static final int HALF = SIZE / 2;
    private static float[] arr = new float[SIZE];

    public static void processing(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    public static void method1(float[] arr) {
        System.out.println("Начинаем обработку массива.");
        long startTime = System.currentTimeMillis();

        processing(arr);

        long finishTime = System.currentTimeMillis();
        System.out.println("В один поток массив обработан за " + (finishTime - startTime) + " мс.");
    }

    public static void method2(float[] arr) throws InterruptedException {
        System.out.println("Начинаем обработку массива.");
        long startTime = System.currentTimeMillis();

        // разбиваем один массив на два
        float[] arrPart1 = new float[HALF];
        float[] arrPart2 = new float[HALF];
        System.arraycopy(arr, 0, arrPart1, 0, HALF);
        System.arraycopy(arr, HALF, arrPart2, 0, HALF);

        // обрабатываем в параллельных потоках
        Thread t1 = new Thread(new Thread() {
            public void run() {
                processing(arrPart1);
            }
        });
        Thread t2 = new Thread(() -> {
            processing(arrPart2);
        });

        // запускаем потоки
        t1.start();
        t2.start();

        // ожидаем завершения потоков
        t1.join();
        t2.join();

        // склеиваем из двух массивов один
        System.arraycopy(arrPart1, 0, arr, 0, HALF);
        System.arraycopy(arrPart2, 0, arr, HALF, HALF);

        long finishTime = System.currentTimeMillis();
        System.out.println("В два потока массив обработан за " + (finishTime - startTime) + " мс.");
    }

    public static void main(String[] args) {
        Arrays.fill(arr, 1.0f);
        // обрабатываем массив в текущем потоке
        method1(arr);

        Arrays.fill(arr, 1.0f);
        // обрабатываем массив в двух параллельных потоках
        try {
            method2(arr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
