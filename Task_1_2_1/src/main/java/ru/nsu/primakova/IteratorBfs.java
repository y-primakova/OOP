package ru.nsu.primakova;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Iterator class using the BFS algorithm.
 */
public class IteratorBfs<T> implements Iterator<T> {
    private final Queue<Tree<T>> queue;

    public IteratorBfs(Tree<T> tree) {
        this.queue = new LinkedList<>();
        queue.add(tree);
    }

    @Override
    public boolean hasNext() {
        return !this.queue.isEmpty();
    }

    @Override
    public T next() {
        if (hasNext()) {
            Tree<T> next = this.queue.remove();
            this.queue.addAll(next.get_children());
            return next.get_value();
        }
        throw new NoSuchElementException();
    }
}