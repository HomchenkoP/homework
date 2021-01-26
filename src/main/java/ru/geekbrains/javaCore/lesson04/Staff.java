package ru.geekbrains.javaCore.lesson04;


//    Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
//    Конструктор класса должен заполнять эти поля при создании объекта.
//    Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
//    Создать массив из 5 сотрудников.
//    С помощью цикла вывести информацию только о сотрудниках старше 40 лет.

public class Staff {
    private String fio;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Staff(String fio, String position, String email, String phone, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void info () {
        System.out.println("Сотрудник ФИО: "+fio+", должность: "+position+", email: "+email+", телефон: "+phone+", зарплата: "+salary+", возраст: "+age);
    }

    public static void main(String[] args) {
        Staff[] staff = new Staff[5];
        staff[0] = new Staff("Иванов Иван Иванович","генеральный директор","gendir@sharaga.ru","77-77-71",100,55);
        staff[1] = new Staff("Петров Петр Петрович","главный бухгалтер","glavbuh@sharaga.ru","77-77-72",90,45);
        staff[2] = new Staff("Сидоров Сидр Сидорович","менеджер","manager@sharaga.ru","77-77-73",50,40);
        staff[3] = new Staff("Васичкин Василий Васильевич","водитель","","77-77-74",30,25);
        staff[4] = new Staff("Кузнецов Кузьма Кузьмич","специалист","rabotyaga@sharaga.ru","77-77-75",25,50);

        for (Staff member : staff) {
            if (member.age > 40) member.info();
        }
    }
}
