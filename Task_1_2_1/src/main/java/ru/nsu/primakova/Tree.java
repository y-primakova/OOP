package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Class Tree.
 */
public class Tree<T> {
    private final T value;
    private Tree<T> parent;
    private ArrayList<Tree<T>> children;
    private int length;

    /**
     * Class constructor.
     *
     * @param value - value of the tree element
     */
    public Tree(T value) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
        this.length = 1;
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

    public int get_length() {
        return this.length;
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
        this.increaseLength(1);
        return tree;
    }

    /**
     * @param subtree - new subtree of the tree
     */
    public void addChild(Tree<T> subtree) throws NullNodeException {
        if (subtree == null) {
            throw new NullNodeException("Null subtree");
        }
        this.children.add(subtree);
        subtree.parent = this;
        this.increaseLength(subtree.length);
    }

    private void increaseLength(int k) {
        this.length += k;
        if (this.parent != null) {
            this.parent.increaseLength(k);
        }
    }

    /**
     * Remove a child/subtree from a tree.
     */
    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            this.parent.length -= this.length;
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
            this.parent.length -= 1;
            this.length = 1;
            this.children = new ArrayList<>();
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
        var bfs = new IteratorBfs<>(this);
        while (bfs.hasNext()) {
            str.append(bfs.next());
            str.append("  ");
        }
        return str.toString();
    }
}