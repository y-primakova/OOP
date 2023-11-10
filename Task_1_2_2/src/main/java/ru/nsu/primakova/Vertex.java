package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Class Vertex.
 */
public class Vertex<T> {
    private T name;
    private int dist;

    /**
     * Class constructor.
     */
    public Vertex() {
        this.name = null;
        this.dist = -1;
    }

    public Vertex(T name) {
        this.name = name;
    }

    public T get_name() {
        return this.name;
    }

    int get_dist() {
        return this.dist;
    }

    public void change_dist(int newDist) {
        this.dist = newDist;
    }

    /**
     * Add a vertex to the list if it isn`t there.
     *
     * @param list - list of vertex
     * @return a vertex(v) from the list if it`s there otherwise return the vertex(this)
     */
    public Vertex<T> listAddVertex(ArrayList<Vertex<T>> list) {
        for (var v : list) {
            if (v.equals(this)) {
                return v;
            }
        }
        list.add(this);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var vertex = (Vertex<?>) obj;
        return this.name.equals(vertex.name);
    }
}
