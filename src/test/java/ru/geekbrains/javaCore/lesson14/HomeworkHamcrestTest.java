package ru.geekbrains.javaCore.lesson14;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class HomeworkHamcrestTest {

    @Test
    public void test1() {
        MatcherAssert.assertThat(new Integer[] {1, 7}, Matchers.arrayContainingInAnyOrder(7, 1));
    }
}
