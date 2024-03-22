package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.readJsonDeque;
import static ru.nsu.primakova.Json.readJsonMap;
import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class TestJson.
 */
public class TestJson {
    @Test
    public void testReadConfig() {
        List<Integer> cookingTime = new ArrayList<>();
        cookingTime.add(1000);
        cookingTime.add(2000);
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(10);

        var res = readJson("src/test/resources/testJsonConfig");
        assertEquals(cookingTime, res.getcookingTime());
        assertEquals(courierCapacity, res.getcourierCapacity());
        assertEquals(4000, res.getworkTime());
        assertEquals(2, res.getstorageCapacity());
    }

    @Test
    public void testReadList() {
        Deque<Integer> orders = new LinkedList<>();
        for (int i = 20; i < 25; i++) {
            orders.add(i);
        }

        var res = readJsonDeque("src/test/resources/testJsonDeque");
        assertEquals(orders, res);
    }

    @Test
    public void testReadMap() {
        var orders = new HashMap<Integer, Double>();
        for (int i = 20; i < 25; i++) {
            orders.put(i, (double) (i / 2));
        }

        var res = readJsonMap("src/test/resources/testJsonMap");
        assertEquals(orders, res);
    }

    @Test
    public void testWriteConfig() {
        List<Integer> cookingTime = new ArrayList<>();
        cookingTime.add(1000);
        cookingTime.add(2000);
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(10);
        Config config = new Config(cookingTime, courierCapacity, 2, 4000);
        writeJson(config, "src/test/resources/testJsonConfig");

        var res = readJson("src/test/resources/testJsonConfig");
        assertEquals(config.getcookingTime(), res.getcookingTime());
        assertEquals(config.getcourierCapacity(), res.getcourierCapacity());
        assertEquals(config.getworkTime(), res.getworkTime());
        assertEquals(config.getstorageCapacity(), res.getstorageCapacity());
    }

    @Test
    public void testWriteDeque() {
        Deque<Integer> orders = new LinkedList<>();
        for (int i = 20; i < 25; i++) {
            orders.add(i);
        }
        writeJson(orders, "src/test/resources/testJsonDeque");

        var res = readJsonDeque("src/test/resources/testJsonDeque");
        assertEquals(orders, res);
    }

    @Test
    public void testWriteMap() {
        var orders = new HashMap<Integer, Double>();
        for (int i = 20; i < 25; i++) {
            orders.put(i, (double) (i / 2));
        }
        writeJson(orders, "src/test/resources/testJsonMap");

        var res = readJsonMap("src/test/resources/testJsonMap");
        assertEquals(orders, res);
    }
}
