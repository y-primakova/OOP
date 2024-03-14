package ru.nsu.primakova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Deque;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

/**
 * Class TestPizzeria.
 */
public class TestPizzeria {
    @Test
    public void testWorkTime1() throws InterruptedException {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria1");
        p.pizzeria();

        Deque<Integer> actualStorage = new LinkedList<>();
        Deque<Integer> actualOrders = new LinkedList<>();
        actualOrders.add(20);
        actualOrders.add(21);

        assertEquals(p.getStorage().getQueue(),actualStorage);
        assertEquals(p.getOrders().getQueue(),actualOrders);
    }

    @Test
    public void testWorkTime2() throws InterruptedException {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria2");
        p.pizzeria();

        Deque<Integer> actualStorage = new LinkedList<>();
        actualStorage.add(20);
        Deque<Integer> actualOrders = new LinkedList<>();
        actualOrders.add(21);

        assertEquals(p.getStorage().getQueue(),actualStorage);
        assertEquals(p.getOrders().getQueue(),actualOrders);
    }

    @Test
    public void testWorkTime3() throws InterruptedException {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria3");
        p.pizzeria();

        Deque<Integer> actualStorage = new LinkedList<>();
        Deque<Integer> actualOrders = new LinkedList<>();

        assertEquals(p.getStorage().getQueue(),actualStorage);
        assertEquals(p.getOrders().getQueue(),actualOrders);
    }

    @Test
    public void test() throws InterruptedException {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria");
        p.pizzeria();

        Deque<Integer> actualStorage = new LinkedList<>();
        actualStorage.add(26);
        actualStorage.add(27);
        Deque<Integer> actualOrders = new LinkedList<>();
        actualOrders.add(23);
        actualOrders.add(28);

//        assertEquals(p.getStorage().getQueue(),actualStorage);
//        assertEquals(p.getOrders().getQueue(),actualOrders);
    }
}
