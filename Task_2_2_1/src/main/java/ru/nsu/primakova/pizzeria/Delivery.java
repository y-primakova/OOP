package ru.nsu.primakova.pizzeria;

/**
 * Class Delivery.
 */
public class Delivery implements Runnable {
    public Storage storage;
    private final int capacity;
    private final int deliveryTime = 1;

    public Delivery(int capacity, Storage storage) {
        if (capacity <= 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = this.capacity; i > 0; i--) {
                    if (storage.isEmpty()) {
                        break;
                    }
                    int order = this.storage.poll();
                    Thread.sleep(deliveryTime);
                    System.out.println(order + "\tзаказ доставлен");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
