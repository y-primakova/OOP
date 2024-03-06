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

    public boolean isEmpty() {
        if (this.queue.isEmpty()) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.queue.size();
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        return this.queue.poll();
    }

    public synchronized void add(T t) {
        this.queue.add(t);
    }
}
