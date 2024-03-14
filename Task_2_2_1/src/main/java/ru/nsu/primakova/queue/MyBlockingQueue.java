package ru.nsu.primakova.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Class MyBlockingQueue.
 */
public class MyBlockingQueue<T> {
    private final Deque<T> queue;
    private int currSize;

    public MyBlockingQueue() {
        this.queue = new LinkedList<>();
        this.currSize = 0;
    }

    public Deque<T> getQueue() {
        return this.queue;
    }

    public synchronized void myNotify() {
        notifyAll();
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

    public synchronized int getCurrSize() {
        return this.currSize;
    }

    public synchronized void setCurrSize(int size) {
        this.currSize = size;
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
        currSize ++;
        notify();
    }

    public synchronized void addFirst(T t) throws InterruptedException {
        this.queue.addFirst(t);
        currSize ++;
        notify();
    }

    public synchronized void addAll(List<T> t) {
        this.queue.addAll(t);
        currSize += t.size();
        notify();
    }
}
