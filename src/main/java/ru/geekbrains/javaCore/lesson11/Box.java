package ru.geekbrains.javaCore.lesson11;

// Задача:
// b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
// c. Для хранения фруктов внутри коробки можно использовать ArrayList;
// d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
// e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare()
// в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
// f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
// Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
// Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
// g. Не забываем про метод добавления фрукта в коробку.

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> content = new ArrayList<>();

    public Box(T... fruits) {
        this.content.addAll(Arrays.asList(fruits));
    }

    public String getContentType() {
        if (content.size() > 0)
            return content.get(0).getClass().getName();
        else
            return null;
    }

    public void addFruit(T fruit) {
        content.add(fruit);
    }

    public void addFruits(ArrayList<T> heap) {
        content.addAll(heap);
    }

    // вес фруктов в коробке
    public double getWeight() {
        if (content.size() == 0)
            return 0.0;
        else {
            double totalWeight = 0.0;
            // если фрукты могут быть различного веса
//            for (T t : content)
//                totalWeight += (double)t.getWeight();
            // если у все фрукты имеют одинаковый вес
            totalWeight = (content.size() * (double)content.get(0).getWeight());
            return totalWeight;
        }
    }

    // сравниваем коробку с другой коробкой по весу
    public boolean compare(Box<?> another) { // точно Box<?> !!!
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;

    }

    // пересыпать фрукты из текущей коробки в другую
    public void transferAll(Box<T> another) { //точно Box<T> !!!
        // добавляем фрукты в другую коробку
        another.addFruits(content);
        // опустошаем текущую коробку
        this.content.clear();
    }
}
