package ru.nsu.primakova.pizzeria;

import ru.nsu.primakova.queue.MyBlockingQueue;

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

    public MyBlockingQueue<Integer> getStorage() {
        return this.storage;
    }

    @Override
    public boolean isEmpty() {
        if (this.storage.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.storage.size();
    }

    @Override
    public synchronized Integer poll() throws InterruptedException {
        while (this.storage.isEmpty()) {
            wait();
        }
        return this.storage.poll();
    }

    @Override
    public synchronized void add(Integer t) throws InterruptedException {
        while (storage.size() == this.capacity) {
            wait();
        }
        this.storage.add(t);
    }
}
