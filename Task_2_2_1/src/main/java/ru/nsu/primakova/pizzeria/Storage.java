package ru.nsu.primakova.pizzeria;

import java.util.Deque;
import ru.nsu.primakova.queue.MyBlockingQueue;

/**
 * Class Storage.
 */
public class Storage<T> extends MyBlockingQueue<T> {
    private final MyBlockingQueue<T> storage;
    private final int capacity;
    private int countActiveThreads;

    /**
     * @param capacity storage capacity
     */
    public Storage(int capacity) {
        this.storage = new MyBlockingQueue<>();
        this.capacity = capacity;
        this.countActiveThreads = 0;
    }

    /**
     * @param capacity storage capacity
     * @param storage storage
     * @throws InterruptedException -
     */
    public Storage(int capacity, Deque<T> storage) throws InterruptedException {
        this.storage = new MyBlockingQueue<>();
        if (storage != null) {
            this.storage.addAll(storage);
        }
        this.capacity = capacity;
        this.countActiveThreads = 0;
    }

    public Deque<T> getQueue() {
        return this.storage.getQueue();
    }

    /**
     * is full or not.
     *
     * @return true if storage is full
     */
    public synchronized boolean isFull() {
        if (storage.size() == this.capacity) {
            return true;
        }
        return false;
    }

    /**
     * is end or not.
     *
     * @return true if queue is end
     */
    public synchronized boolean isActiveThreads() {
        if (this.countActiveThreads == 0) {
            return true;
        }
        return false;
    }

    /**
     * increment CountActiveThreads.
     */
    public synchronized void incActiveThreads() {
        this.countActiveThreads++;
    }

    /**
     * decrement CountActiveThreads.
     */
    public synchronized void decActiveThreads() {
        this.countActiveThreads--;
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
        notify();
    }

    @Override
    public synchronized void addFirst(T t) throws InterruptedException {
        while (storage.size() == this.capacity) {
            wait();
        }
        this.storage.addFirst(t);
        notify();
    }

    @Override
    public synchronized void addAll(Deque<T> t) throws InterruptedException {
        while (storage.size() + t.size() == this.capacity) {
            wait();
        }
        this.storage.addAll(t);
        notify();
    }
}
