package ru.nsu.primakova;

import ru.nsu.primakova.pizzeria.Pizzeria;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.writeJson;

/**
 * Class Main.
 */
public class Main {
    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException {
        Pizzeria.pizzeria();
//        List<Integer> a = new ArrayList<>();
//        a.add(1);
//        Config config = new Config(a,a,10);
//        writeJson(config,"test");
//        readJson("test");
    }
}