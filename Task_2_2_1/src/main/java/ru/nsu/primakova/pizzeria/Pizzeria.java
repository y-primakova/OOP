package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Pizzeria.
 */
public class Pizzeria {
    public static void pizzeria() throws InterruptedException {
        int nBakers = 1;
        int nDilivery = 1;
        int[] cookingTime = {1};
        int[] capacity = {1};
        int storageCapacity = 1;
        var orders = new MyBlockingQueue<Integer>();
        orders.add(1);
        orders.add(2);
        orders.add(3);
        var storage = new Storage(storageCapacity);

        var threadsBaker = new Thread[1];
        var threadsDelivery = new Thread[1];

        for (int i = 0; i < nDilivery; i++) {
            threadsDelivery[i] = new Thread(new Delivery(capacity[i], storage));
            threadsDelivery[i].start();
        }

        for (int i = 0; i < nBakers; i++) {
            threadsBaker[i] = new Thread(new Baker(cookingTime[i], orders, storage));
            threadsBaker[i].start();
        }

    }
}
