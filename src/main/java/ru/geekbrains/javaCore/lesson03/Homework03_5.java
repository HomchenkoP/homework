package ru.geekbrains.javaCore.lesson03;

import java.util.Arrays;

// 5. *По желанию! Написать метод, который принимает на вход 2 целых числа, например, x и y.
//    Внутри метода создаётся целочисленный двумерный массив со сторонами х и у. Потом этот массив заполняется последовательно
//    инкрементируемыми числами по спирали (или змейкой). То есть, в позиции [0,0] будет 1, в [0,1] 2 и так далее.
//    Потом по достижении правой границы направление заполнения меняется на вертикальное и так далее.
//    Так, что последний заполненный элемент будет посередине массива.

public class Homework03_5 {

    public static void main(String[] args) {
        int[][] rect;
        rect = zmeyka(6,5);
        printArr2D(rect);
    }

    public static int[][] zmeyka(int x_size, int y_size) {
        int[][] rect = new int[y_size][x_size];
        int direction = 0;
        int x_min, x_max, y_min, y_max;
        x_min = 0;
        x_max = x_size-1;
        y_min = 1;
        y_max = y_size-1;

        for (int i = 1, x = 0, y = 0; i <= x_size * y_size; i++) {
            rect[y][x] = i;
            if (direction == 0)
                if (x < x_max) x++;
                else {
                    direction++;
                    x_max--;
                }
            if (direction == 1)
                if (y < y_max) y++;
                else {
                    direction++;
                    y_max--;
                }
            if (direction == 2)
                if (x > x_min) x--;
                else {
                    direction++;
                    x_min++;
                }
            if (direction == 3)
                if (y > y_min) y--;
                else {
                    direction = 0;
                    y_min++;
                    x++;
                }
        }
        return rect;
    }

    public static void printArr2D(int[][] arr2D) {
        for (int i = 0; i < arr2D.length; i++) {
            System.out.println(Arrays.toString(arr2D[i]));
        }
    }
}
