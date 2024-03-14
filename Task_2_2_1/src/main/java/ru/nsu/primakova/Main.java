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
        cookingTime.add(2000);
        cookingTime.add(2000);
        cookingTime.add(3000);
        List<Integer> orders = new ArrayList<>();
        for (int i = 20; i < 29; i++) {
            orders.add(i);
        }
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(2);
        courierCapacity.add(2);
        Config config = new Config(cookingTime, courierCapacity, 2, 5000, orders);
        writeJson(config, "src/main/resources/pizzeria");

        var p = new Pizzeria("src/main/resources/pizzeria");
        p.pizzeria();
        p.pizzeria();
    }
}
