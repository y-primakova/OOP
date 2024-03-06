package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Baker.
 */
public class Baker implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage storage;
    private final int cookingTime;

    public Baker(int cookingTime, MyBlockingQueue<Integer> orders, Storage storage) {
        if (cookingTime <= 0) {
            this.cookingTime = 1;
        } else {
            this.cookingTime = cookingTime;
        }
        this.orders = orders;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int order;
                try {
                    order = this.orders.poll();
                } catch (NullPointerException e) {
                    continue;
                }
                System.out.println(order + "\tзаказ готовится");
                Thread.sleep(cookingTime);
                storage.add(order);
                System.out.println(order + "\tзаказ ожидает курьера");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
