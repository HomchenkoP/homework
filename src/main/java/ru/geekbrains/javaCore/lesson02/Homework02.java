package ru.geekbrains.javaCore.lesson02;

import java.util.Arrays;

public class Homework02 {

    public static void main(String[] args) {
        method1();
        System.out.println();
        method2();
        System.out.println();
        method3();
        System.out.println();
        method4();
        System.out.println();
        method5();
        System.out.println();

        //int[] arr = {2, 2, 2, 1, 2, 2, 10, 1};
        //int[] arr = {1, 1, 1, 2, 1};
        int[] arr = {2, 2, 0, 0, 4};

        System.out.println("В массиве " + Arrays.toString(arr));
        if (method6(arr)) System.out.println("есть место, в котором сумма левой и правой части массива равны");
        else System.out.println("нет места, в котором сумма левой и правой части массива равны");
        System.out.println();

        //int arr7[] = {1, 2, 3}, n = 1;
        int arr7[] = {3, 5, 6, 1}, n = -2;
        method7(arr7, n);
    }

    public static void method1() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Было");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            //заменить 0 на 1, 1 на 0
            switch (arr[i]) {
                case 0:
                    arr[i] = 1;
                    break;
                case 1:
                    arr[i] = 0;
                    break;
            }
        }
        System.out.println("Стало");
        System.out.println(Arrays.toString(arr));
    }

    public static void method2() {
        int[] arr = new int[8];
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            //заполнить значениями 0 3 6 9 12 15 18 21
            arr[i] = arr[i-1] + 3;
        }
        System.out.println("Стало");
        System.out.println(Arrays.toString(arr));
    }

    public static void method3() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("Было");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            //числа меньшие 6 умножить на 2
            if (arr[i] < 6) arr[i] *= 2;
        }
        System.out.println("Стало");
        System.out.println(Arrays.toString(arr));
    }

    public static void printArr2D(int[][] arr2D) {
        for (int i = 0; i < arr2D.length; i++) {
            System.out.println(Arrays.toString(arr2D[i]));
        }
    }

    public static void method4() {
        int arr2D[][] = new int[5][];
        for (int i = 0; i < arr2D.length; i++) {
            arr2D[i] = new int[arr2D.length];
            //заполнить диагональные элементы единицами
            arr2D[i][i] = 1;
            arr2D[i][arr2D.length-1-i] = 1;
        }
        System.out.println("Стало");
        printArr2D(arr2D);
    }

    public static void method5() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int minVal = arr[0];
        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            //найти минимальный и максимальный элементы
            if (arr[i] < minVal) minVal = arr[i];
            if (arr[i] > maxVal) maxVal = arr[i];
        }
        System.out.println("В массиве " + Arrays.toString(arr));
        System.out.println("минимальный элемент: " + minVal);
        System.out.println("максимальный элемент: " + maxVal);
    }

    public static boolean method6(int[] arr) {
        long leftSum, rightSum;
        int[] leftArr, rightArr;
        boolean retVal = false;

        for (int i = 1; i < arr.length; i++) {
            leftSum = 0L;
            rightSum = 0L;
            leftArr = new int[i];
            rightArr= new int[arr.length - i];

            for (int j = 0; j < arr.length; j++) {
                if (j < i) {
                    //сумма элементов левой части массива
                    leftSum += arr[j];
                    leftArr[j] = arr[j];
                } else {
                    //сумма элементов правой части массива
                    rightSum += arr[j];
                    rightArr[j-i] = arr[j];
                }
            }
            if (leftSum == rightSum) {
                System.out.println(Arrays.toString(leftArr) + Arrays.toString(rightArr));
                retVal = true;
            }
        }
        return retVal;
    }

    public static void method7(int[] arr, int n) {
        System.out.println("Было");
        System.out.println(Arrays.toString(arr));
        int dummy;
        //сместить все элементы массива на n позиций
        for (int i = 0; i < Math.abs(n); i++) {
            if (Math.signum(n) == 1) {
                //сместить на одну позицию вправо
                dummy = arr[arr.length-1]; //запоминаем значение последнего элемента
                for (int l = arr.length-1; l > 0 ; l--) arr[l] = arr[l-1];
                arr[0] = dummy;
            } else {
                //сместить на одну позицию влево
                dummy = arr[0]; // запоминаем значение первого элемента
                for (int l = 0; l < arr.length-1; l++) arr[l] = arr[l+1];
                arr[arr.length-1] = dummy;
            }
        }
        System.out.println("Стало после сдвига на " + n + " позиций");
        System.out.println(Arrays.toString(arr));
    }
}
