package ru.nsu.primakova.queue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class MyBlockingQueue.
 */
public class MyBlockingQueue<T> {
    private final Deque<T> queue;
    private final HashMap<T, Double> time;
    private int currSize;
    private int CountActiveThreads;

    public MyBlockingQueue() {
        this.queue = new LinkedList<>();
        this.time = new HashMap<>();
        this.currSize = 0;
        this.CountActiveThreads = 0;
    }

    public MyBlockingQueue(HashMap<T, Double> time) {
        this.queue = new LinkedList<>();
        if (time != null) {
            this.queue.addAll(time.keySet());
            this.time = time;
            this.currSize = time.size();
        } else {
            this.time = new HashMap<>();
            this.currSize = 0;
        }
        this.CountActiveThreads = 0;
    }

    public Deque<T> getQueue() {
        return this.queue;
    }

    public synchronized HashMap<T, Double> getTime() {
        return this.time;
    }

    public synchronized Double getTime(int i) {
        return this.time.get(i);
    }

    public synchronized void setTime(T order, double time) {
        this.time.put(order, time);
    }

    public synchronized void removeTime(T order) {
        this.time.remove(order);
    }

    public synchronized void myNotify() {
        notifyAll();
    }

    /**
     * is end or not.
     *
     * @return true if queue is end
     */
    public synchronized boolean isActiveThreads() {
        if (this.CountActiveThreads == 0) {
            return true;
        }
        return false;
    }

    /**
     * increment CountActiveThreads.
     */
    public synchronized void incActiveThreads() {
        this.CountActiveThreads++;
    }

    /**
     * decrement CountActiveThreads.
     */
    public synchronized void decActiveThreads() {
        this.CountActiveThreads--;
    }

    /**
     * is empty or not.
     *
     * @return true if queue is empty
     */
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

    /**
     * poll first element of queue.
     *
     * @return first element in queue
     * @throws InterruptedException -
     */
    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        return this.queue.poll();
    }

    /**
     * poll last element of queue.
     *
     * @return last element in queue
     * @throws InterruptedException -
     */
    public synchronized T pollLast() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        return this.queue.pollLast();
    }

    /**
     * add element to tail of queue.
     *
     * @throws InterruptedException -
     */
    public synchronized void add(T t) throws InterruptedException {
        this.queue.add(t);
        currSize++;
        notify();
    }

    /**
     * add element to head of queue.
     *
     * @throws InterruptedException -
     */
    public synchronized void addFirst(T t) throws InterruptedException {
        this.queue.addFirst(t);
        currSize++;
        notify();
    }

    /**
     * add list of elements to tail of queue.
     */
    public synchronized void addAll(Deque<T> t) throws InterruptedException {
        this.queue.addAll(t);
        currSize += t.size();
        notify();
    }
}
