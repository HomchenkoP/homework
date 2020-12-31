package ru.geekbrains.javaCore.lesson01;

public class Homework01 {

    byte byteVal = 1;
    short shortVal = 2;
    int intVal = 3;
    long longVal = 4L;
    float floatVal = 5.0f;
    double doubleVal = 6.0;
    char charVal = '7';
    boolean boolVal = true;
    String strVal = "some string";

    public static void main(String[] args) {
        System.out.println(method1(2.0f, 3.0f, 4.0f, 5.0f));
        System.out.println(method2(5,14));
        method3(-1);
        System.out.println(method4(-1));
        method5("Сеня");
        method6(1900);
        method6(1904);
        method6(1961);
        method6(2000);
    }

    public static float method1(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static boolean method2(int a, int b) {
        if(10 <= (a+b) && (a+b) <= 20) {
            return true;
        } else {
            return false;
        }
    }

    public static void method3(int a) {
        if(a >= 0) {
            System.out.println("положительное");
        } else {
            System.out.println("отрицательное");
        }
    }

    public static boolean method4(int a) {
        if(a >= 0) {
            return false;
        } else {
            return true ;
        }
    }

    public static void method5(String a) {
        System.out.println("Привет, " + a + "!");
    }

    public static void method6(int year) {
        if(year % 4 == 0) {
            //каждый четвертый
            if(year % 100 == 0) {
                //каждый сотый
                if(year % 400 == 0) {
                    //каждый четырехсотый
                    System.out.println(year + " - високосный");
                } else {
                    System.out.println(year + " - не високосный");
                }
            } else {
                System.out.println(year + " - високосный");
            }
        } else {
            System.out.println(year + " - не високосный");
        }
    }
}
