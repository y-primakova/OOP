package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

import java.util.Deque;

/**
 * Class Storage.
 */
public class Storage extends MyBlockingQueue<Integer>{
    private final MyBlockingQueue<Integer> storage;
    private final int capacity;

    public Storage(int capacity) {
        this.storage = new MyBlockingQueue<>();
        this.capacity = capacity;
    }

    public Deque<Integer> getStorage() {
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

    @Override
    public synchronized Integer pollLast() throws InterruptedException {
        while (this.storage.isEmpty()) {
            wait();
        }
        notify();
        return this.storage.pollLast();
    }

    @Override
    public synchronized void add(Integer t) throws InterruptedException {
        while (storage.size() == this.capacity) {
            wait();
        }
        this.storage.add(t);
        notify();
    }

    @Override
    public synchronized void addFirst(Integer t) throws InterruptedException {
        while (storage.size() == this.capacity) {
            wait();
        }
        this.storage.addFirst(t);
        notify();
    }
}
