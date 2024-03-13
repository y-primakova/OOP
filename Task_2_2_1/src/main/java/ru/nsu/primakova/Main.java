package ru.nsu.primakova;

import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
import java.util.List;
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
        cookingTime.add(1000);
        cookingTime.add(2000);
        cookingTime.add(3000);
        cookingTime.add(3000);
        List<Integer> orders = new ArrayList<>();
        for (int i = 20; i < 30; i++) {
            orders.add(i);
        }
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(10);
        Config config = new Config(cookingTime,courierCapacity,3, 4*1000, orders);
        writeJson(config,"test");

        var p = new Pizzeria("test");
        p.pizzeria();
        p.pizzeria();
    }
}