package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Baker.
 */
public class Baker implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage storage;
    private final int cookingTime;
    private boolean isEnd;

    public Baker(int cookingTime, MyBlockingQueue<Integer> orders, Storage storage, boolean isEnd) {
        if (cookingTime <= 0) {
            this.cookingTime = 1;
        } else {
            this.cookingTime = cookingTime;
        }
        this.orders = orders;
        this.storage = storage;
        this.isEnd = isEnd;
    }

    @Override
    public void run() {
        while (!this.orders.isEmpty()) {
            try {
                var order = this.orders.poll();
                System.out.println(order + "\tзаказ готовится");
                Thread.sleep(this.cookingTime);
                System.out.println(order + "\tзаказ ожидает освобождения склада");
                this.storage.add(order);
                System.out.println(order + "\tзаказ ожидает курьера");
//                if (this.orders.isEmpty()) {
//                    this.isEnd = true;
//                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
