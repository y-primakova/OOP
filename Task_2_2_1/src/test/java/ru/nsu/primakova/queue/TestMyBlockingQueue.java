package ru.nsu.primakova.queue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class TestMyBlockingQueue.
 */
public class TestMyBlockingQueue {
    /**
     * create queue for tests.
     *
     * @return queue
     */
    private MyBlockingQueue<Integer> testQueue() {
        var q = new HashMap<Integer, Double>();
        q.put(1, 0.77);
        q.put(2, 0.9);
        q.put(3, null);
        return new MyBlockingQueue<>(q);
    }

    @Test
    public void testTime() {
        var s = testQueue();
        assertEquals(s.getTime(1),0.77);
        assertEquals(s.getTime(2),0.9);
        assertNull(s.getTime(3));
        assertNull(s.getTime(10));
    }

    @Test
    public void testIsEmpty() {
        var q = testQueue();
        assertFalse(q.isEmpty());
    }

    @Test
    public void testIsEnd() {
        var q = testQueue();
        assertTrue(q.isActiveThreads());
        q.incActiveThreads();
        assertFalse(q.isActiveThreads());
        q.decActiveThreads();
        assertTrue(q.isActiveThreads());
    }

    @Test
    public void testCurrSize() {
        var s = testQueue();
        assertEquals(s.getCurrSize(), 3);
    }

    @Test
    public void testSize() {
        var q = testQueue();
        assertEquals(q.size(), 3);
    }

    @Test
    public void testPoll() throws InterruptedException {
        var q = testQueue();
        q.poll();
        Deque<Integer> actual = new LinkedList<>();
        actual.add(2);
        actual.add(3);
        assertEquals(q.getQueue(), actual);
    }

    @Test
    public void testPollLast() throws InterruptedException {
        var q = testQueue();
        q.pollLast();
        Deque<Integer> actual = new LinkedList<>();
        actual.add(1);
        actual.add(2);
        assertEquals(q.getQueue(), actual);
    }

    @Test
    public void testAdd() throws InterruptedException {
        var q = testQueue();
        q.add(4);
        Deque<Integer> actual = new LinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        assertEquals(q.getQueue(), actual);
    }

    @Test
    public void testAddAll() throws InterruptedException {
        var q = testQueue();
        var x = new LinkedList<Integer>();
        x.add(4);
        x.add(5);
        q.addAll(x);
        Deque<Integer> actual = new LinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.add(5);
        assertEquals(q.getQueue(), actual);
    }

    @Test
    public void testAddFirst() throws InterruptedException {
        var q = testQueue();
        q.addFirst(4);
        Deque<Integer> actual = new LinkedList<>();
        actual.add(4);
        actual.add(1);
        actual.add(2);
        actual.add(3);
        assertEquals(q.getQueue(), actual);
    }
}
