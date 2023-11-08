package ru.nsu.primakova;

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
}