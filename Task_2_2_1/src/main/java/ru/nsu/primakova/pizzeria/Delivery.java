package ru.nsu.primakova.pizzeria;

import java.util.ArrayList;
import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Delivery.
 */
public class Delivery implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage<Integer> storage;
    private final int capacity;
    private final int deliveryTime = 3000;
    private final int indThread;

    /**
     * class constructor.
     *
     * @param capacity courier capacity
     * @param orders order queue
     * @param storage storage
     * @param indThread index of thread
     */
    public Delivery(int capacity, MyBlockingQueue<Integer> orders, Storage<Integer> storage, int indThread) {
        if (capacity <= 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.storage = storage;
        this.orders = orders;
        this.indThread = indThread;
    }

    private void returnInStorage(ArrayList<Integer> currOrders) {
        for (var o : currOrders) {
            try {
                if (storage.isFull()) {
                    var x = storage.pollLast();
                    orders.addFirst(x);
                }
                storage.addFirst(o);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        storage.decActiveThreads();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (storage.isEmpty()) {
                continue;
            }
            storage.incActiveThreads();
            var currOrders = new ArrayList<Integer>();
            for (int i = this.capacity; i > 0; i--) {
                if (storage.isEmpty()) {
                    break;
                }
                int order;
                try {
                    order = storage.poll();
                } catch (InterruptedException e) {
                    returnInStorage(currOrders);
                    break;
                }
                currOrders.add(order);
            }
            for (var order : currOrders) {
                System.out.println(order + "\t\t" + indThread + "\t\tкурьер забрал заказ");
            }
            try {
                Thread.sleep(deliveryTime);
            } catch (InterruptedException e) {
                returnInStorage(currOrders);
                break;
            }
            for (var order : currOrders) {
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ доставлен");
                orders.removeTime(order);
            }
            storage.decActiveThreads();
        }
    }
}
