package ru.nsu.primakova;

import java.util.Iterator;
import java.util.Stack;

public class IteratorDFS<T> implements Iterator<T> {
    private final Stack<Tree<T>> stack;
    public IteratorDFS(Tree<T> tree){
        this.stack = new Stack<>();
        stack.push(tree);
    }
    @Override
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    @Override
    public T next() {
        if(hasNext()){
            Tree<T> next = this.stack.pop();
            this.stack.addAll(next.get_children());
            return next.get_value();
        }
        return null;
    }
}
