package ru.nsu.primakova.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Class MyBlockingQueue.
 */
public class MyBlockingQueue<T> {
    private final Deque<T> queue;
    private boolean isEnd;

    public MyBlockingQueue() {
        this.queue = new LinkedList<>();
        this.isEnd = false;
    }

    public synchronized boolean isEmpty() {
        if (this.queue.isEmpty()) {
            return true;
        }
        return false;
    }

    public synchronized boolean isEnd() {
        if (this.isEnd) {
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

    public synchronized T pollLast() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        return this.queue.pollLast();
    }

    public synchronized void add(T t) throws InterruptedException {
        this.queue.add(t);
        notify();
    }

    public synchronized void addFirst(T t) throws InterruptedException {
        this.queue.addFirst(t);
        notify();
    }

    public synchronized void addAll(List<T> t) throws InterruptedException {
        this.queue.addAll(t);
        notify();
    }

    public synchronized void changeIsEnd() {
        this.isEnd = true;
    }
}
