package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Pizzeria.
 */
public class Pizzeria {
    public static void main(String[] args) {
        var orders = new MyBlockingQueue<Integer>();
        var storage = new MyBlockingQueue<Integer>();
        List<Thread> baker = new ArrayList<>();
        List<Thread> delivery = new ArrayList<>();

        baker.add(new Thread(new Baker(1, orders, storage)));
        delivery.add(new Thread(new Delivery(1, storage)));

//        for (var i : baker) {
//            i.start();
//        }
//        for (var i : delivery) {
//            i.start();
//        }
    }
}
