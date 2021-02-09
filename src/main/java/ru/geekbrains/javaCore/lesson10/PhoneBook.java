package ru.geekbrains.javaCore.lesson10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный
// справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует
// учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии
// должны выводиться все телефоны. Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес)

public class PhoneBook {

    private HashMap<String, ArrayList<String>> map = new HashMap<>();

    public ArrayList<String> get(String surname) {
        ArrayList<String> phoneList;
        if (map.containsKey(surname)) {
            // фамилия уже встречалась
            phoneList = map.get(surname);
        }
        else {
            phoneList = new ArrayList<>();
        }
        return phoneList;
    }

    public void add(String surname, String phone) {
        ArrayList<String> phoneList = get(surname);
        phoneList.add(phone);
        map.put(surname, phoneList);
    }

    public Set<String> getAllSurnames() {
        return map.keySet();
    }
}
