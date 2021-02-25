package ru.geekbrains.javaCore.lesson14;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

// Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен вернуть
// новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
// массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
// Написать набор тестов для этого метода (по 3-4 варианта входных данных).
//   Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
//
// Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы, то метод вернет
// false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
//   [ 1 1 1 4 4 1 4 4 ] -> true
//   [ 1 1 1 1 1 1 ] -> false
//   [ 4 4 4 4 ] -> false
//   [ 1 4 4 1 1 4 3 ] -> false

public class Homework14 {

    // Уровни логирования
    // Trace < Debug < Info < Warn < Error < Fatal
    // Действующий в данный момент уровень логирования указывается в файле ~/IdeaProjects/homework/src/main/resources/log4j2.xml
    // в теге Configuration.Loggers.Logger@level
    private static final Logger LOGGER = LogManager.getLogger(Homework14.class);
    // в теге Configuration.Loggers.Root@level
    //private static final Logger LOGGER = LogManager.getLogger("Root");

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        method1a(arr1);
        try {
            int[] arr2 = {1, 1, 1, 3, 5, 1};
            method1a(arr2);
        } catch (RuntimeException e) {}
    }

    public static int[] method1a(int[] arr) throws RuntimeException {
        LOGGER.debug("Получили массив {}", Arrays.toString(arr));
        if (arr.length == 0) {
            LOGGER.warn("Пустой массив");
            return arr;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int pos = arr.length-1; pos >= 0; pos--) {
            if (arr[pos] == 4) {
                LOGGER.info("В позиции {} oбнаружено число '4'", pos);
                break;
            }
            else if (pos == 0) {
                RuntimeException e = new RuntimeException("Массив не содержит '4'");
                LOGGER.throwing(Level.FATAL, e);
                throw e;
            }
            list.addFirst(Integer.valueOf(arr[pos]));
        }
        int[] ret = list.stream().mapToInt(Integer::intValue).toArray();
        LOGGER.debug("Возвращаем массив {}", Arrays.toString(ret));
        return ret;
    }

    public int[] method1(int[] arr) throws RuntimeException {
        if (arr.length == 0)
            return arr;
        int pos = arr.length-1;
        int cnt = 0;
        while (pos >= 0) {
            if (arr[pos] == 4)
                break;
            else if (pos == 0)
                throw new RuntimeException("Массив не содержит '4'");
            pos--;
            cnt++;
        }
        int[] ret = new int[cnt];
        System.arraycopy(arr, pos+1, ret, 0, cnt);
        return ret;
    }

    public boolean method2(int[] arr) {
        // Класс HashSet служит для создания коллекции, содержащей только уникальные элементы
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr)
            set.add(Integer.valueOf(i));
        return (set.size() == 2) && set.contains(Integer.valueOf(1)) && set.contains(Integer.valueOf(4));
    }
}
