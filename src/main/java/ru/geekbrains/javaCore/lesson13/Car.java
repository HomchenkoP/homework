package ru.geekbrains.javaCore.lesson13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Все участники должны стартовать одновременно, несмотря на разное время подготовки.
// В тоннель не может одновременно заехать больше половины участников (условность).
//
// Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
// Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима).
// Когда все завершат гонку, нужно выдать объявление об окончании.

public class Car implements Runnable {

    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    // CountDownLatch позволяет потоку ожидать завершения операций, выполняющихся в других потоках.
    // Режим ожидания запускается методом await(). При создании объекта определяется количество требуемых операций,
    // после чего уменьшается при вызове метода countDown().
    // Как только счетчик доходит до нуля, с ожидающего потока снимается блокировка.
    private CountDownLatch cdlStart;
    private CountDownLatch cdlFinish;
    // CyclicBarrier выполняет синхронизацию заданного количества потоков в одной точке.
    // Как только заданное количество потоков заблокировалось (вызовами метода await()), с них одновременно снимается блокировка.
    private CyclicBarrier cb;
    // Semaphore ограничивает количество потоков при работе с ресурсами.
    // Для этого служит счетчик. Если его значение больше нуля, то потоку разрешается доступ, а значение уменьшается.
    // Если счетчик равен нулю, то текущий поток блокируется до освобождения ресурса.
    // Для получения доступа используется метод acquire(), для освобождения – release().
    public Semaphore smp;
    // Интерфейс Lock гарантируют, что сохранится порядок обращения потоков к критической секции.
    // Создаем объект типа Lock и вызываем у него метод lock() – он захватывается.
    // Если другой поток попытается вызвать этот метод у того же объекта – он будет блокирован до тех пор, пока поток,
    // удерживающий объект lock, не освободит его через метод unlock(). Тогда этот объект смогут захватить другие потоки.
    private static Lock lock = new ReentrantLock();

    // количество участников, закончивших гонку
    private static int finishedCnt = 0;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch cdlStart, CountDownLatch cdlFinish, CyclicBarrier cb, Semaphore smp) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        //
        this.cdlStart = cdlStart;
        this.cdlFinish = cdlFinish;
        this.cb = cb;
        this.smp = smp;
    }

    private boolean checkWin() {
        finishedCnt ++;
        return (finishedCnt == 1);
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            // точка готовности всех участников к гонке
            cdlStart.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        try {
            lock.lock();
            // критическая секция
            if (checkWin())
                System.out.println("Победа! " + this.name + " - победитель");
        } finally {
            lock.unlock();
        }
        // точка окончания гонки
        cdlFinish.countDown();
    }
}
