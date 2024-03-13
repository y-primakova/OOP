package ru.nsu.primakova.queue;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import ru.nsu.primakova.pizzeria.Storage;

public class TestStorage {
    private Storage testStorage() throws InterruptedException {
        var s = new Storage(4);
        s.add(1);
        s.add(2);
        s.add(3);
        return s;
    }

    @Test
    public void testIsEmpty() throws InterruptedException {
        var s = testStorage();
        assertFalse(s.isEmpty());
    }

    @Test
    public void testIsFull() throws InterruptedException {
        var s = testStorage();
        assertFalse(s.isFull());
    }

    @Test
    public void testSize() throws InterruptedException {
        var s = testStorage();
        assertEquals(s.size(), 3);
    }

    @Test
    public void testPollLast() throws InterruptedException {
        var s = testStorage();
        s.pollLast();
        Deque<Integer> actual = new LinkedList<>();
        actual.add(1);
        actual.add(2);
        assertEquals(s.getStorage(),actual);
    }

    @Test
    public void testAdd() throws InterruptedException {
        var s = testStorage();
        s.add(4);
        Deque<Integer> actual = new LinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        assertEquals(s.getStorage(),actual);
    }

    @Test
    public void testAddFirst() throws InterruptedException {
        var s = testStorage();
        s.addFirst(4);
        Deque<Integer> actual = new LinkedList<>();
        actual.add(4);
        actual.add(1);
        actual.add(2);
        actual.add(3);
        assertEquals(s.getStorage(),actual);
    }
}
