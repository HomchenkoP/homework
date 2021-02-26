package ru.geekbrains.javaCore.lesson14;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

public class HomeworkTest {

    private Homework14 homework14;

    @Before
    public void init() {
        homework14 = new Homework14();
    }

    @Test
    public void testMethod1_1() {
        Assert.assertArrayEquals(new int[] {1, 7}, homework14.method1(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test(expected = RuntimeException.class)
    public void testMethod1_2() {
        // ожидаем исключение RuntimeException
        Assert.assertArrayEquals(new int[] {}, homework14.method1(new int[] {1, 1, 1}));
    }

    @Test
    public void testMethod1_3() {
        Assert.assertArrayEquals(new int[] {}, homework14.method1(new int[] {}));
    }

    @Test
    public void testMethod1_4() {
        Assert.assertArrayEquals(new int[] {3, 2, 1}, homework14.method1(new int[] {1, 2, 3, 4, 3, 2, 1}));
    }

    @Test
    public void testMethod2_1() {
        Assert.assertTrue(homework14.method2(new int[] {1, 1, 1, 4, 4, 1, 4, 4}));
    }

    @Test
    public void testMethod2_2() {
        Assert.assertFalse(homework14.method2(new int[] {1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testMethod2_3() {
        Assert.assertFalse(homework14.method2(new int[] {4, 4, 4, 4}));
    }

    @Test
    public void testMethod2_4() {
        Assert.assertFalse(homework14.method2(new int[] {1, 4, 4, 1, 1, 4, 3}));
    }
}
