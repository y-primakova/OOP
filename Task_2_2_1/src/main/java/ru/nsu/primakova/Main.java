package ru.nsu.primakova;

import static ru.nsu.primakova.Json.readJsonMap;
import static ru.nsu.primakova.Json.writeJson;

import java.util.*;

import ru.nsu.primakova.pizzeria.Pizzeria;

/**
 * Class Main.
 */
public class Main {
    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException {
        List<Integer> cookingTime = new ArrayList<>();
        cookingTime.add(5000);
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(3);
        Config config = new Config(cookingTime, courierCapacity, 5, 3500);
        writeJson(config, "src/main/resources/pizzeria");

        var orders = new HashMap<Integer,Double>();
        for (int i = 20; i < 23; i++) {
            orders.put(i,null);
        }
        writeJson(orders, "src/main/resources/orders");

        Deque<Integer> storage = new LinkedList<>();
        writeJson(storage, "src/main/resources/storage");

        var p = new Pizzeria("src/main/resources/pizzeria", "src/main/resources/orders", "src/main/resources/storage");
        p.pizzeria();
    }
}
