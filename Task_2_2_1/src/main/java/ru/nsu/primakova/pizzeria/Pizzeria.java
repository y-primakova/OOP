package ru.nsu.primakova.pizzeria;

import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.readJsonDeque;
import static ru.nsu.primakova.Json.readJsonMap;
import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
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
    private final String ordersPath;
    private final String storagePath;

    public Pizzeria(String configPath, String ordersPath, String storagePath) throws InterruptedException {
        var config = readJson(configPath);
        var storage = readJsonDeque(storagePath);
        if (config != null) {
            this.cookingTime = config.getcookingTime();
            this.courierCapacity = config.getcourierCapacity();
            this.workTime = config.getworkTime();
            this.storage = new Storage<>(config.getstorageCapacity(),storage);
        } else {
            this.cookingTime = new ArrayList<>();
            this.courierCapacity = new ArrayList<>();
            this.workTime = 0;
            this.storage = new Storage<>(0,storage);
        }

        var orders = readJsonMap(ordersPath);
        this.orders = new MyBlockingQueue<>(orders);
        this.ordersPath = ordersPath;
        this.storagePath = storagePath;
    }

    /**
     * start pizzeria.
     * @throws InterruptedException -
     */
    public void pizzeria() throws InterruptedException {
        System.out.println("order" + "\tthread" + "\tstate");

        var threadsBaker = new Thread[cookingTime.size()];
        var threadsDelivery = new Thread[courierCapacity.size()];
        for (int i = 0; i < cookingTime.size(); i++) {
            threadsBaker[i] = new Thread(new Baker(cookingTime.get(i), orders, storage, i));
            threadsBaker[i].start();
        }
        for (int i = 0; i < courierCapacity.size(); i++) {
            threadsDelivery[i] = new Thread(new Delivery(courierCapacity.get(i), orders, storage, i + cookingTime.size()));
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

        for (int i = 0; i < cookingTime.size(); i++) {
            threadsBaker[i].interrupt();
        }
        for (int i = 0; i < courierCapacity.size(); i++) {
            threadsDelivery[i].interrupt();
        }
        orders.myNotify();
        storage.myNotify();
        while (!orders.isActiveThreads() || !storage.isActiveThreads()) {

        }
        System.out.println("Пиццерия закрыта");

        writeJson(orders.getTime(), ordersPath);
        writeJson(storage.getQueue(), storagePath);
    }

    public Storage<Integer> getStorage() {
        return this.storage;
    }

    public MyBlockingQueue<Integer> getOrders() {
        return this.orders;
    }
}
