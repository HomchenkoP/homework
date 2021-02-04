package ru.geekbrains.javaCore.lesson07;

// Интерфейс участника состязаний

public interface Participantable {

    void info();

    boolean run(Obstacleable o);

    boolean jump(Obstacleable o);
}
