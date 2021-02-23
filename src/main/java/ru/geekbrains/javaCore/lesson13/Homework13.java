package ru.geekbrains.javaCore.lesson13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

// Все участники должны стартовать одновременно, несмотря на разное время подготовки.
// В тоннель не может одновременно заехать больше половины участников (условность).
//
// Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
// Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима).
// Когда все завершат гонку, нужно выдать объявление об окончании.

public class Homework13 {

    public static final int CARS_COUNT = 4;
    public static final int TUNNEL_COUNT = 2;

    public static void main(String[] args) {

        final CountDownLatch cdlStart = new CountDownLatch(CARS_COUNT);
        final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
        final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
//      Семафор лучше все-таки объявить в тоннеле, а не в main.
//      Потому как если у вас было бы несколько тоннелей, они бы синхронно блочились, и если в одном тоннеле занято, то другие бы блокировались.
//      Семафор бы хорошо отработал будь он в каждом тоннеле свой.
        final Semaphore smp = new Semaphore(TUNNEL_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cdlStart, cdlFinish, cb, smp);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            // Режим ожидания запускается методом await().
            cdlStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            // Режим ожидания запускается методом await().
            cdlFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
