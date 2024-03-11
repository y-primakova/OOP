package ru.nsu.primakova.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class MyBlockingQueue.
 */
public class MyBlockingQueue<T> {
    private final Queue<T> queue;

    public MyBlockingQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized boolean isEmpty() {
        if (this.queue.isEmpty()) {
            return true;
        }
        return false;
    }

    public synchronized int size() {
        return this.queue.size();
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        return this.queue.poll();
    }

    public synchronized void add(T t) throws InterruptedException {
        this.queue.add(t);
        notify();
    }
}
