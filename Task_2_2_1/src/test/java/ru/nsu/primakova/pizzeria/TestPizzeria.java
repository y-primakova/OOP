package ru.nsu.primakova.pizzeria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.nsu.primakova.Json.writeJson;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

/**
 * Class TestPizzeria.
 */
public class TestPizzeria {
    private void initialState() {
        var orders = new HashMap<Integer, Double>();
        for (int i = 20; i < 23; i++) {
            orders.put(i, null);
        }
        writeJson(orders, "src/test/resources/testOrders1");

        Deque<Integer> storage = new LinkedList<>();
        writeJson(storage, "src/test/resources/testStorage1");
    }

    @Test
    public void test1() throws InterruptedException {
        initialState();
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria1", "src/test/resources/testOrders1", "src/test/resources/testStorage1");
        p.pizzeria();

        assertEquals(p.getStorage().getQueue().pollFirst(), 20);
        assertEquals(p.getStorage().getQueue().pollFirst(), 21);
        assertEquals(p.getOrders().getQueue().pollFirst(), 22);
    }

    @Test
    public void test2() throws InterruptedException {
        initialState();
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria2", "src/test/resources/testOrders1", "src/test/resources/testStorage1");
        p.pizzeria();

        assertEquals(p.getStorage().getQueue().pollFirst(), 21);
        assertEquals(p.getOrders().getQueue().pollFirst(), 22);
    }

    @Test
    public void test3() throws InterruptedException {
        initialState();
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        var p = new Pizzeria("src/test/resources/testPizzeria3", "src/test/resources/testOrders1", "src/test/resources/testStorage1");
        p.pizzeria();

        assertNull(p.getStorage().getQueue().pollFirst());
        assertEquals(p.getOrders().getQueue().pollFirst(), 20);
        assertEquals(p.getOrders().getQueue().pollFirst(), 21);
        assertEquals(p.getOrders().getQueue().pollFirst(), 22);
    }
}
