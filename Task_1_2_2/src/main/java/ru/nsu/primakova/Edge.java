package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Class Edge.
 */
public class Edge<T> {
    private final Vertex<T> startVertex;
    private final Vertex<T> endVertex;
    private int value;

    /**
     * Class constructor.
     *
     * @param value - value of the new edge
     * @param start - start vertex of the new edge
     * @param end - end vertex of the new edge
     */
    public Edge(int value, Vertex<T> start, Vertex<T> end) {
        this.startVertex = start;
        this.endVertex = end;
        this.value = value;
    }

    public int get_value() {
        return this.value;
    }

    public Vertex<T> get_startVertex() {
        return this.startVertex;
    }

    public Vertex<T> get_endVertex() {
        return this.endVertex;
    }

    public Edge<T> listAddEdge(ArrayList<Edge<T>> list) {
        for (var e : list) {
            if (e.equals(this)) {
                return e;
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
        var edge = (Edge<?>) obj;
        if (edge.get_endVertex().equals(this.endVertex)) {
            if (edge.get_startVertex().equals(this.startVertex)) {
                return edge.get_value() == this.value;
            }
        }
        return false;
    }
}