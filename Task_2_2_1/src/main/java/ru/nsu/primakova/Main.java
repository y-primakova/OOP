package ru.nsu.primakova;

import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import ru.nsu.primakova.pizzeria.Pizzeria;

/**
 * Class Main.
 */
public class Main {
    /**
     * main.
     *
     * @param args -
     * @throws InterruptedException -
     */
    public static void main(String[] args) throws InterruptedException {
        List<Integer> cookingTime = new ArrayList<>();
        cookingTime.add(1000);
        cookingTime.add(2000);
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(3);
        Config config = new Config(cookingTime, courierCapacity, 5, 5000);
        writeJson(config, "src/main/resources/pizzeria");

        var orders = new HashMap<Integer, Double>();
        for (int i = 20; i < 23; i++) {
            orders.put(i, 1.0);
        }
        writeJson(orders, "src/main/resources/orders");

        Deque<Integer> storage = new LinkedList<>();
        writeJson(storage, "src/main/resources/storage");

        var p = new Pizzeria("src/main/resources/pizzeria", "src/main/resources/orders", "src/main/resources/storage");
        p.addOrder(1);
        p.addOrder(5);
        p.pizzeria();
    }
}
