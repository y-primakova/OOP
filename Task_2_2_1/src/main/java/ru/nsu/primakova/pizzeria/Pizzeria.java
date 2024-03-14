package ru.nsu.primakova.pizzeria;

import static ru.nsu.primakova.Json.readJson;

import java.util.List;
import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Pizzeria.
 */
public class Pizzeria {
    private final Storage<Integer> storage;
    private final MyBlockingQueue<Integer> orders;
    private final List<Integer> cookingTime;
    private final List<Integer> courierCapacity;
    private final int workTime;

    public Pizzeria(String configPath) {
        var config = readJson(configPath);

        assert config != null;
        this.cookingTime = config.getcookingTime();
        this.courierCapacity = config.getcourierCapacity();
        this.workTime = config.getworkTime();
        this.orders = new MyBlockingQueue<>();
        this.orders.addAll(config.getorders());
        this.storage = new Storage<>(config.getstorageCapacity());
    }

    public int getSizeBakers() {
        return this.cookingTime.size();
    }

    public int getSizeDelivery() {
        return this.courierCapacity.size();
    }

    public void pizzeria() throws InterruptedException {
        System.out.println("order" + "\tthread" + "\tstate");

        var threadsBaker = new Thread[this.getSizeBakers()];
        var threadsDelivery = new Thread[this.getSizeDelivery()];
        for (int i = 0; i < this.getSizeBakers(); i++) {
            threadsBaker[i] = new Thread(new Baker(cookingTime.get(i), orders, storage, i));
            threadsBaker[i].start();
        }
        for (int i = 0; i < this.getSizeDelivery(); i++) {
            threadsDelivery[i] = new Thread(new Delivery(courierCapacity.get(i), orders, storage, i + this.getSizeBakers()));
            threadsDelivery[i].start();
        }

        var time = workTime;
        while (storage.getCurrSize() != 0 || orders.getCurrSize() != 0) {
            if (time > 1000) {
                Thread.sleep(1000);
                time -= 1000;
            } else {
                Thread.sleep(time);
                break;
            }
        }

        for (int i = 0; i < this.getSizeBakers(); i++) {
            threadsBaker[i].interrupt();
        }
        for (int i = 0; i < this.getSizeDelivery(); i++) {
            threadsDelivery[i].interrupt();
        }
        System.out.println("Пиццерия закрыта");
        orders.myNotify();
        storage.myNotify();
    }

    public Storage<Integer> getStorage() {
        return this.storage;
    }

    public MyBlockingQueue<Integer> getOrders() {
        return this.orders;
    }
}
