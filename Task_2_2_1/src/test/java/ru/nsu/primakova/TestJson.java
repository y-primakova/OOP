package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class TestJson.
 */
public class TestJson {
    @Test
    public void testWrite() {
        var filepath = "src/test/resources/testJson";
        List<Integer> cookingTime = new ArrayList<>();
        cookingTime.add(1000);
        cookingTime.add(2000);
        List<Integer> orders = new ArrayList<>();
        for (int i = 20; i < 25; i++) {
            orders.add(i);
        }
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(10);
        Config config = new Config(cookingTime,courierCapacity,2, 4000, orders);
        writeJson(config, filepath);
        var res = readJson(filepath);
        assertEquals(config.getstorage(), res.getstorage());
        assertEquals(config.getorders(), res.getorders());
        assertEquals(config.getcookingTime(), res.getcookingTime());
        assertEquals(config.getcourierCapacity(), res.getcourierCapacity());
        assertEquals(config.getworkTime(), res.getworkTime());
        assertEquals(config.getstorageCapacity(), res.getstorageCapacity());
    }

    @Test
    public void testRead() {
        var filepath = "src/test/resources/testJson";
        var res = readJson(filepath);

        List<Integer> cookingTime = new ArrayList<>();
        cookingTime.add(1000);
        cookingTime.add(2000);
        List<Integer> orders = new ArrayList<>();
        for (int i = 20; i < 25; i++) {
            orders.add(i);
        }
        List<Integer> courierCapacity = new ArrayList<>();
        courierCapacity.add(10);

        assertEquals(new ArrayList<>(), res.getstorage());
        assertEquals(orders, res.getorders());
        assertEquals(cookingTime, res.getcookingTime());
        assertEquals(courierCapacity, res.getcourierCapacity());
        assertEquals(4000, res.getworkTime());
        assertEquals(2, res.getstorageCapacity());
    }
}
