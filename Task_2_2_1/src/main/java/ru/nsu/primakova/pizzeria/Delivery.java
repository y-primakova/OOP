package ru.nsu.primakova.pizzeria;

import java.util.ArrayList;
import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Delivery.
 */
public class Delivery implements Runnable {
    private final MyBlockingQueue<Integer> orders;
    private final Storage storage;
    private final int capacity;
    private final int deliveryTime = 3000;
    private final int indThread;

    public Delivery(int capacity, MyBlockingQueue<Integer> orders, Storage storage, int indThread) {
        if (capacity <= 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.storage = storage;
        this.orders = orders;
        this.indThread = indThread;
    }

    @Override
    public void run() {
        while ((!orders.isEnd() || !this.storage.isEmpty()) && !Thread.currentThread().isInterrupted()) {
            var currOrders = new ArrayList<Integer>();
            for (int i = this.capacity; i > 0; i--) {
                if (this.storage.isEmpty()) {
                    break;
                }
                int order;
                try {
                    order = this.storage.poll();
                } catch (InterruptedException e) {
                    for (var o : currOrders) {
                        try {
                            if (storage.isFull()) {
                                var x = storage.pollLast();
                                orders.addFirst(x);
                            } else {
                                storage.addFirst(o);
                            }
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
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
                break;
            }
            for (var order : currOrders) {
                System.out.println(order + "\t\t" + indThread + "\t\tзаказ доставлен");
            }
        }
    }
}
