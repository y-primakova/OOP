package ru.nsu.primakova;

import java.util.ArrayList;

public class Tree<T> {
    private final T value;
    private Tree<T> parent;
    private final ArrayList<Tree<T>> children;

    public Tree(T value) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public T get_value() {
        return this.value;
    }

    public Tree<T> get_parent() {
        return this.parent;
    }

    public ArrayList<Tree<T>> get_children() {
        return this.children;
    }

    public Tree<T> addChild(T value) {
        var tree = new Tree<>(value);
        this.children.add(tree);
        tree.parent = this;
        return tree;
    }

    public void addChild(Tree<T> subtree) {
        this.children.add(subtree);
        subtree.parent = this;
    }

    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            this.parent = null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var tree = (Tree<?>) obj;
        var bfs1 = new IteratorBfs<>(tree);
        var bfs2 = new IteratorBfs<>(this);
        while (bfs1.hasNext() && bfs2.hasNext()) {
            if (!bfs1.next().equals(bfs2.next())){
                return false;
            }
        }
        if (bfs1.hasNext() != bfs2.hasNext()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        var bfs = new IteratorBfs<>(this);
        while (bfs.hasNext()) {
            str.append(bfs.next());
            str.append("  ");
        }
        return str.toString();
    }
}