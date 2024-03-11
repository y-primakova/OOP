package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Delivery.
 */
public class Delivery implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage storage;
    private final int capacity;
    private final int deliveryTime = 6000;
    private final boolean isEnd;

    public Delivery(int capacity, MyBlockingQueue<Integer> orders, Storage storage, boolean isEnd) {
        if (capacity <= 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.storage = storage;
        this.orders = orders;
        this.isEnd = isEnd;
    }

    @Override
    public void run() {
        while (true) {
//        while (isEnd || !this.storage.isEmpty()) {
            try {
                for (int i = this.capacity; i > 0; i--) {
                    if (this.storage.isEmpty()) {
                        break;
                    }
                    var order = this.storage.poll();
                    System.out.println(order + "\tкурьер забрал заказ");
                    if (i == this.capacity) {
                        Thread.sleep(deliveryTime);
                    }
                    System.out.println(order + "\tзаказ доставлен");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
