package ru.nsu.primakova;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Iterator class using the DFS algorithm.
 */
public class IteratorDfs<T> implements Iterator<T> {
    private final Stack<Tree<T>> stack;
    private final int length;
    private final Tree<T> startTree;


    public IteratorDfs(Tree<T> tree) {
        this.stack = new Stack<>();
        stack.push(tree);
        this.length = tree.get_length();
        this.startTree = tree;
    }

    @Override
    public boolean hasNext() {
        if (this.length != startTree.get_length()) {
            throw new ConcurrentModificationException();
        }
        return !this.stack.isEmpty();
    }

    @Override
    public T next() {
        if (hasNext()) {
            Tree<T> next = this.stack.pop();
            this.stack.addAll(next.get_children());
            return next.get_value();
        }
        throw new NoSuchElementException();
    }
}