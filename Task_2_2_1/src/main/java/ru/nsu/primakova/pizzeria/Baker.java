package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Baker.
 */
public class Baker implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage storage;
    private final int cookingTime;
    private final int indThread;

    public Baker(int cookingTime, MyBlockingQueue<Integer> orders, Storage storage, int indThread) {
        if (cookingTime <= 0) {
            this.cookingTime = 1;
        } else {
            this.cookingTime = cookingTime;
        }
        this.orders = orders;
        this.storage = storage;
        this.indThread = indThread;
    }

    @Override
    public void run() {
        while (!this.orders.isEmpty() && !Thread.currentThread().isInterrupted()) {
            int order = 0;
            try {
                order = this.orders.poll();
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ готовится");
                Thread.sleep(this.cookingTime);
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ ожидает освобождения склада");
                this.storage.add(order);
            } catch (InterruptedException e) {
                try {
                    this.orders.addFirst(order);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }
            System.out.println(order + "\t\t" + indThread + "\t\tзаказ ожидает курьера");
            if (this.orders.isEmpty()) {
                orders.changeIsEnd();
            }
        }
    }
}
