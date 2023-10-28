package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Tree.
 */
public class Tree<T> implements Iterable<T> {
    private final T value;
    private Tree<T> parent;
    private ArrayList<Tree<T>> children;
    private int modification;

    /**
     * Class constructor.
     *
     * @param value - value of the tree element
     */
    public Tree(T value) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
        this.modification = 0;
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

    public int get_nModification() {
        return this.modification;
    }

    /**
     * Add a child to the tree.
     *
     * @param value - value of the new tree element
     * @return new tree
     */
    public Tree<T> addChild(T value) throws NullNodeException {
        if (value == null) {
            throw new NullNodeException("Null node");
        }
        var tree = new Tree<>(value);
        this.children.add(tree);
        tree.parent = this;
        this.increaseModification();
        return tree;
    }

    /**
     * Add a subtree to the tree.
     *
     * @param subtree - new subtree of the tree
     */
    public void addChild(Tree<T> subtree) throws NullNodeException {
        if (subtree == null) {
            throw new NullNodeException("Null subtree");
        }
        this.children.add(subtree);
        subtree.parent = this;
        this.increaseModification();
    }

    private void increaseModification() {
        this.modification += 1;
        if (this.parent != null) {
            this.parent.increaseModification();
        }
    }

    /**
     * Remove a child/subtree from a tree.
     */
    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            this.parent.increaseModification();
            this.parent = null;
        }
    }

    /**
     * Remove only child from a tree.
     */
    public void removeAndSaveChildren() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            this.parent.children.addAll(this.children);
            for (var child : this.children) {
                child.parent = this.parent;
            }
            this.increaseModification();
            this.children = new ArrayList<>();
            this.parent = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorBfs<>(this);
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
        var bfs1 = tree.iterator();
        var bfs2 = this.iterator();
        while (bfs1.hasNext() && bfs2.hasNext()) {
            if (!bfs1.next().equals(bfs2.next())) {
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
        for (var elem : this) {
            str.append(elem);
            str.append("  ");
        }
        return str.toString();
    }
}