package ru.geekbrains.javaCore.lesson14;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

// Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен вернуть
// новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
// массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
// Написать набор тестов для этого метода (по 3-4 варианта входных данных).
//   Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

@RunWith(Parameterized.class)
public class HomeworkParamenrizedTest {

    private Homework14 homework14;
    private int[] arg, res;

    public HomeworkParamenrizedTest(int[] arg, int[] res) {
        this.arg = arg;
        this.res = res;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[] {1, 7}},
                {new int[] {1, 1, 1}, new int[] {}},
                {new int[] {}, new int[] {}},
                {new int[] {1, 2, 3, 4, 3, 2, 1}, new int[] {3, 2, 1}}
        });
    }

    @Before
    public void init() {
        homework14 = new Homework14();
    }

    @Test
    public void testMethod1_1() {
        Assert.assertArrayEquals(res, homework14.method1(arg));
    }
}
