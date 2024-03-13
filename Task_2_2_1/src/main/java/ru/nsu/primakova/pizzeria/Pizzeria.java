package ru.nsu.primakova.pizzeria;

import static ru.nsu.primakova.Json.readJson;

import ru.nsu.primakova.queue.MyBlockingQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Pizzeria.
 */
public class Pizzeria {
    private final Storage storage;
    private final MyBlockingQueue<Integer> orders;
    private final List<Integer> cookingTime;
    private final List<Integer> courierCapacity;
    private final int workTime;

    public Pizzeria(String configPath) throws InterruptedException {
        var config = readJson(configPath);

        assert config != null;
        this.cookingTime = config.getcookingTime();
        this.courierCapacity = config.getcourierCapacity();
        this.workTime = config.getworkTime();
        this.orders = new MyBlockingQueue<>();
        this.orders.addAll(config.getorders());
        this.storage = new Storage(config.getstorageCapacity());
    }

    public int getNBakers() {
        return this.cookingTime.size();
    }

    public int getNDelivery() {
        return this.courierCapacity.size();
    }

    public void pizzeria() throws InterruptedException {
        System.out.println("order" + "\tthread" + "\tstate");

        var threadsBaker = new Thread[this.getNBakers()];
        var threadsDelivery = new Thread[this.getNDelivery()];
        for (int i = 0; i < this.getNBakers(); i++) {
            threadsBaker[i] = new Thread(new Baker(cookingTime.get(i), orders, storage, i));
            threadsBaker[i].start();
        }
        for (int i = 0; i < this.getNDelivery(); i++) {
            threadsDelivery[i] = new Thread(new Delivery(courierCapacity.get(i), orders, storage, i + this.getNBakers()));
            threadsDelivery[i].start();
        }

        Thread.sleep(workTime);
        for (int i = 0; i < this.getNBakers(); i++) {
            threadsBaker[i].interrupt();
        }
        for (int i = 0; i < this.getNDelivery(); i++) {
            threadsDelivery[i].interrupt();
        }
        System.out.println("Пиццерия закрыта");

    }
}
