package ru.geekbrains.javaCore.lesson07;

// Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и прыгать
// (методы просто выводят информацию о действии в консоль).
// Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие
// действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
// Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
// У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки. Если участник не смог
// пройти одно из препятствий, то дальше по списку он препятствий не идет.

public class Homework07 {
    public static void main(String[] args) {
        // Участники
        Participantable[] participants = new Participantable[3];
        participants[0] = new Human("Сергей");
        participants[1] = new Cat("Рыжик");
        participants[2] = new Robot("Вертер");

        // Препятствия
        Obstacleable[] obstacles = new Obstacleable[5];
        obstacles[0] = new Track();
        obstacles[1] = new Wall();
        obstacles[2] = new Track();
        obstacles[3] = new Wall();
        obstacles[4] = new Track();

        for (Participantable p : participants) {
            p.info();
            for (Obstacleable o : obstacles) {
                if (o instanceof Track) {
                    if (!p.run(o)) break;
                }
                else if (o instanceof Wall) {
                    if (!p.jump(o)) break;
                }
            }
        }
    }
}
