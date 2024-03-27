package ru.nsu.primakova.pizzeria;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Baker.
 */
public class Baker implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage<Integer> storage;
    private final int cookingTime;
    private static final Logger log = LogManager.getLogger();

    /**
     * class constructor.
     *
     * @param cookingTime cooking time
     * @param orders order queue
     * @param storage storage
     */
    public Baker(int cookingTime, MyBlockingQueue<Integer> orders, Storage<Integer> storage) {
        if (cookingTime <= 0) {
            this.cookingTime = 1;
        } else {
            this.cookingTime = cookingTime;
        }
        this.orders = orders;
        this.storage = storage;
    }

    private void sleep(int order) throws InterruptedException {
        if (orders.getTime(order) != null) {
            Thread.sleep((long) (cookingTime * orders.getTime(order)));
        } else {
            Thread.sleep(cookingTime);
        }
        orders.setTime(order, 0);
    }

    private void changeTime(int order, long start) {
        if (start != 0) {
            long end = System.currentTimeMillis();
            double time = 1 - (double) (end - start) / cookingTime;
            if (orders.getTime(order) != null) {
                time += orders.getTime(order) - 1;
            }
            if (time < 0) {
                time = 0;
            }
            orders.setTime(order, time);
        }
        try {
            orders.addFirst(order);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        orders.decActiveThreads();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (orders.isEmpty()) {
                continue;
            }
            orders.incActiveThreads();
            int order = 0;
            long start = 0;
            try {
                order = orders.poll();
                if (orders.getTime(order) == null || orders.getTime(order) != 0) {
                    log.info("\t" + order + "\t\tзаказ готовится");
                    start = System.currentTimeMillis();
                    sleep(order);
                }
                log.info("\t" + order + "\t\tзаказ ожидает освобождения склада");
                storage.add(order);
            } catch (InterruptedException e) {
                changeTime(order, start);
                break;
            }
            log.info("\t" + order + "\t\tзаказ ожидает курьера");
            orders.decActiveThreads();
        }
    }
}
