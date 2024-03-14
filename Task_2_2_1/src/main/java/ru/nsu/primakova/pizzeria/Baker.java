package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Baker.
 */
public class Baker implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage<Integer> storage;
    private final int cookingTime;
    private final int indThread;

    public Baker(int cookingTime, MyBlockingQueue<Integer> orders, Storage<Integer> storage, int indThread) {
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
        while (!orders.isEmpty() && !Thread.currentThread().isInterrupted()) {
            int order = 0;
            try {
                order = orders.poll();
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ готовится");
                Thread.sleep(cookingTime);
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ ожидает освобождения склада");
                storage.add(order);
            } catch (InterruptedException e) {
                try {
                    orders.addFirst(order);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }
            System.out.println(order + "\t\t" + indThread + "\t\tзаказ ожидает курьера");
            orders.setCurrSize(orders.getCurrSize() - 1);
        }
    }
}
