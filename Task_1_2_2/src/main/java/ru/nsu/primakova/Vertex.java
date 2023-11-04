package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Class Vertex.
 */
public class Vertex<T> {
    private T name;

    /**
     * Class constructor.
     */
    public Vertex() {
        this.name = null;
    }

    public Vertex(T name) {
        this.name = name;
    }

    public T get_name() {
        return this.name;
    }

    public void change_name(T newName) {
        this.name = newName;
    }

    /**
     * Add a vertex to the list if it isn`t there.
     * @param list - list of vertex
     * @return a vertex(v) from the list if it`s there otherwise return the vertex(this)
     */
    public Vertex<T> listAddVertex(ArrayList<Vertex<T>> list) {
        for(var v : list) {
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
