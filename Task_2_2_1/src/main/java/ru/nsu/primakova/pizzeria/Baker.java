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
            orders.incActiveThreads();
            int order = 0;
            long start = 0;
            try {
                order = orders.poll();
                if (orders.getTime(order) == null || orders.getTime(order) != 0L) {
                    System.out.println(order + "\t\t" + indThread + "\t\tзаказ готовится");
                    start = System.currentTimeMillis();
                    if (orders.getTime(order) != null) {
                        Thread.sleep((long) (cookingTime * orders.getTime(order)));
                    } else {
                        Thread.sleep(cookingTime);
                    }
                    orders.setTime(order, 0);
                }
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ ожидает освобождения склада");
                storage.add(order);
            } catch (InterruptedException e) {
                long end = System.currentTimeMillis();
                double time = 1 - (double) (end - start) / cookingTime;
                if (orders.getTime(order) != null) {
                    time += orders.getTime(order) - 1;
                }
                if (time < 0) {
                   time = 0;
                }
                orders.setTime(order, time);
                try {
                    orders.addFirst(order);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                orders.decActiveThreads();
                break;
            }
            orders.setTime(order, 0);
            System.out.println(order + "\t\t" + indThread + "\t\tзаказ ожидает курьера");
            orders.setCurrSize(orders.getCurrSize() - 1);
            orders.decActiveThreads();
        }
    }
}
