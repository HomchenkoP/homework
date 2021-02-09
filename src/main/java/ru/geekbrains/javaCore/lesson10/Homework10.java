package ru.geekbrains.javaCore.lesson10;

import java.util.*;

// Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из
// которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
//
// Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный
// справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует
// учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии
// должны выводиться все телефоны. Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
// взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного
// справочника.

public class Homework10 {

    public static ArrayList<String> distinctWords(String[] words) {
        // Коллекция, содержащая только уникальные элементы.
        // Класс LinkedHashSet использует связный список для сохранения порядка добавления в него элементов.
        // Следовательно, при переборе элементов они будут извлекаться в том порядке, в каком были добавлены.
        LinkedHashSet<String> set = new LinkedHashSet<>(Arrays.asList(words));
        return new ArrayList<>(set);
    }

    public static LinkedHashMap<String, Integer> distinctWordsCount(String[] words) {
        // Хеш-таблица для хранения пар ключ-значение.
        // Класс LinkedHashMap сохраняет порядок добавления записей.
        // Следовательно, при переборе элементов они будут извлекаться в том порядке, в каком были добавлены.
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String s : words) {
            if (map.containsKey(s)) {
                // слово уже встречалось
                int cnt = map.get(s);
                map.put(s, ++cnt);
            }
            else
                map.put(s, 1);
        }
        return map;
    }

    public static void main(String[] args) {
        final String[] words = {"один","два","три","четыре","пять","шесть","пять","четыре","три","два","раз","пять"};
        System.out.println("Массив слов: " + Arrays.toString(words));

        ArrayList<String> list = distinctWords(words);
        System.out.println("Уникальные слова: " + list.toString());

        LinkedHashMap<String, Integer> map = distinctWordsCount(words);
        System.out.println("Вхождения слов: " + map.toString());

        // Итератор позволяет обойти все элементы коллекции. Для работы с итераторами служит интерфейс Iterator.
        // Для получения объекта этого типа, необходимо вызвать метод iterator() у коллекции.
//        Iterator mapIterator = map.entrySet().iterator();
//        while (mapIterator.hasNext()) {
//            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) mapIterator.next();
//            System.out.print("Ключ: '" + entry.getKey() + "'");
//            System.out.println(" Значение: '" + entry.getValue() + "'");
//        }

        PhoneBook book = new PhoneBook();
        book.add("Иванов", "111-11-11");
        book.add("Петров", "222-22-22");
        book.add("Сидоров", "333-33-33");
        book.add("Петров", "444-44-44");
        for (String s : book.getAllSurnames())
            System.out.println("Фамилия: " + s + " Телефон: " + book.get(s));
        System.out.println("Для отсутствующих в справочнике выводится пустой список: " + book.get("Аноним"));
    }
}
