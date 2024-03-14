package ru.nsu.primakova.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

/**
 * Class TestMyBlockingQueue.
 */
public class TestMyBlockingQueue {
    private MyBlockingQueue<Integer> testQueue() throws InterruptedException {
        var q = new MyBlockingQueue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        return q;
    }

    @Test
    public void testIsEmpty() throws InterruptedException {
        var q = testQueue();
        assertFalse(q.isEmpty());
    }

    @Test
    public void testCurrSize() throws InterruptedException {
        var s = testQueue();
        assertEquals(s.getCurrSize(), 3);
    }

    @Test
    public void testSize() throws InterruptedException {
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
        var x = new ArrayList<Integer>();
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
