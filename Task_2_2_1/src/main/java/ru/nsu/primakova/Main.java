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
        cookingTime.add(1000);
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(1);
        Config config = new Config(cookingTime,courierCapacity,1);
        writeJson(config,"test");
        Pizzeria.pizzeria();
    }
}