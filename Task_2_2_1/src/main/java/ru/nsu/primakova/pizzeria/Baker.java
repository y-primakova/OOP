package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Baker.
 */
public class Baker implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final MyBlockingQueue<Integer> storage;
    private final int cookingTime;

    public Baker(int cookingTime, MyBlockingQueue<Integer> orders, MyBlockingQueue<Integer> storage) {
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
                System.out.println(order + "\tготовится");
                Thread.sleep(cookingTime);
                storage.add(order);
                System.out.println(order + "\tперемещен на складе");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
