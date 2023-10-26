package ru.nsu.primakova;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Iterator class using the BFS algorithm.
 */
public class IteratorBfs<T> implements Iterator<T> {
    private final Queue<Tree<T>> queue;
    private final int length;
    private final Tree<T> startTree;

    public IteratorBfs(Tree<T> tree) {
        this.queue = new LinkedList<>();
        this.queue.add(tree);
        this.length = tree.get_length();
        this.startTree = tree;
    }

    @Override
    public boolean hasNext() {
        if (this.length != startTree.get_length()) {
            throw new ConcurrentModificationException();
        }
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