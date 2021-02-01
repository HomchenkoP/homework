package ru.geekbrains.javaCore.lesson07;

// Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого размера
// необходимо бросить исключение MyArraySizeException.
// Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе массива
// преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение
// MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
// В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException и вывести результат расчета.

public class Homework07_5_6_7 {

    public static void main(String[] args) {
        // корректный массив
//        String[][] arr = new String[][] {
//                {"1", "2", "3", "4"},
//                {"5", "6", "7", "8"},
//                {"9", "10", "11", "12"},
//                {"13", "14", "15", "16"}};

        // массив неправильной размерности
//        String[][] arr = new String[][] {
//                {"1", "2", "3", "4"},
//                {"5", "6", "7", "8"},
//                {"9", "10", "11", "12"}};

        // массив с некорректным элементом
        String[][] arr = new String[][] {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"девять", "10", "11", "12"},
                {"13", "14", "15", "16"}};

        try {
            checkArrSize(arr);
            calcSum(arr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void checkArrSize(String[][] arr) {
        if (arr.length != 4)
            throw new MyArraySizeException("Размер массива должен быть 4х4.");
        for (int i = 0; i < arr.length; i++)
            if (arr[i].length != 4)
                throw new MyArraySizeException("Размер массива должен быть 4х4.");
    }

    static boolean isNumber(String s) {
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int calcSum(String[][] arr) {
        int sum = 0;
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                if (!isNumber(arr[x][y]))
                    throw new MyArrayDataException("Элемент [" + (y+1) + "] в строке [" + (x+1) + "] не является числом.");
                sum += Integer.parseInt(arr[x][y]);
            }
        }
        System.out.println("Сумма всех элементов = " + sum);
        return sum;
    }
}
