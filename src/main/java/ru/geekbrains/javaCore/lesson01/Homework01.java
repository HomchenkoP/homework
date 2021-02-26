package ru.geekbrains.javaCore.lesson01;

// 1. Создать пустой проект в IntelliJ IDEA и прописать метод main().
// 2. Создать переменные всех пройденных типов данных и инициализировать их значения.
// 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – аргументы этого метода,
//    имеющие тип float.
// 4. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно),
//    если да – вернуть true, в противном случае – false.
// 5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль, положительное ли число
//    передали или отрицательное. Замечание: ноль считаем положительным числом.
// 6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число отрицательное,
//    и вернуть false если положительное.
// 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя. Метод должен вывести в консоль сообщение
//    «Привет, указанное_имя!».
// 8. *Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным,
//    кроме каждого 100-го, при этом каждый 400-й – високосный.

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
