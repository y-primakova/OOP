package ru.nsu.primakova.pizzeria;

import java.util.Deque;
import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Storage.
 */
public class Storage<T> extends MyBlockingQueue<T> {
    private final MyBlockingQueue<T> storage;
    private final int capacity;
    private int currSize;

    public Storage(int capacity) {
        this.storage = new MyBlockingQueue<>();
        this.capacity = capacity;
        this.currSize = 0;
    }

    public Deque<T> getQueue() {
        return this.storage.getQueue();
    }

    public synchronized boolean isFull() {
        if (storage.size() == this.capacity) {
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean isEmpty() {
        if (this.storage.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public synchronized int size() {
        return this.storage.size();
    }

    public synchronized int getCurrSize() {
        return this.currSize;
    }

    public synchronized void setCurrSize(int size) {
        this.currSize = size;
    }

    @Override
    public synchronized T poll() throws InterruptedException {
        while (this.storage.isEmpty()) {
            wait();
        }
        notify();
        return this.storage.poll();
    }

    @Override
    public synchronized T pollLast() throws InterruptedException {
        while (this.storage.isEmpty()) {
            wait();
        }
        notify();
        return this.storage.pollLast();
    }

    @Override
    public synchronized void add(T t) throws InterruptedException {
        while (storage.size() == this.capacity) {
            wait();
        }
        this.storage.add(t);
        currSize ++;
        notify();
    }

    @Override
    public synchronized void addFirst(T t) throws InterruptedException {
        while (storage.size() == this.capacity) {
            wait();
        }
        this.storage.addFirst(t);
        currSize ++;
        notify();
    }
}
