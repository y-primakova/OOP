package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

import static ru.nsu.primakova.Json.readJson;

/**
 * Class Pizzeria.
 */
public class Pizzeria {
    public static Storage storage;
    public static void pizzeria() throws InterruptedException {
        var config = readJson("test");

        var cookingTime = config.getcookingTime();
        var courierCapacity = config.getcourierCapacity();
        int storageCapacity = config.getstorageCapacity();
        int nBakers = cookingTime.size();
        int nDelivery = courierCapacity.size();

        var orders = new MyBlockingQueue<Integer>();
        orders.add(1);
        orders.add(2);
        orders.add(3);
        storage = new Storage(storageCapacity);

        var threadsBaker = new Thread[1];
        var threadsDelivery = new Thread[1];
        for (int i = 0; i < nBakers; i++) {
            threadsBaker[i] = new Thread(new Baker(cookingTime.get(i), orders, storage));
            threadsBaker[i].start();
        }
        for (int i = 0; i < nDelivery; i++) {
            threadsDelivery[i] = new Thread(new Delivery(courierCapacity.get(i), storage));
            threadsDelivery[i].start();
        }
    }
}
