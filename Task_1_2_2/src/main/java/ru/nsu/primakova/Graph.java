package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Graph.
 */
public abstract class Graph<T> {
    protected ArrayList<Edge<T>> listEdge;
    protected ArrayList<Vertex<T>> listVertex;

    /**
     * Class constructor.
     *
     * @param value - value of the new edge
     * @param start - start vertex of the new edge
     * @param end - end vertex of the new edge
     */
    public Graph(int value, Vertex<T> start, Vertex<T> end) {
        this.listEdge = new ArrayList<>();
        this.listVertex = new ArrayList<>();
        this.listEdge.add(new Edge<T>(value, start, end));
        this.listVertex.add(start);
        this.listVertex.add(end);
    }

    /**
     * Class constructor.
     *
     * @param edge - new edge
     */
    public Graph(Edge<T> edge) {
        this.listEdge = new ArrayList<>();
        this.listVertex = new ArrayList<>();
        this.listEdge.add(edge);
        this.listVertex.add(edge.get_startVertex());
        this.listVertex.add(edge.get_endVertex());
    }

    /**
     * Class constructor.
     *
     * @param listEdge - list of new edges
     * @param listVertex - list of new vertexes
     */
    public Graph(ArrayList<Edge<T>> listEdge, ArrayList<Vertex<T>> listVertex) {
        this.listEdge = new ArrayList<>();
        this.listVertex = new ArrayList<>();
        this.listEdge.addAll(listEdge);
        this.listVertex.addAll(listVertex);
        for (var edge : listEdge) {
            if (!this.listVertex.contains(edge.get_endVertex())) {
                this.listVertex.add(edge.get_endVertex());
            }
            if (!this.listVertex.contains(edge.get_startVertex())) {
                this.listVertex.add(edge.get_startVertex());
            }
        }
    }

    public ArrayList<Edge<T>> get_listEdge() {
        return this.listEdge;
    }

    public ArrayList<Vertex<T>> get_listVertex() {
        return this.listVertex;
    }

    public abstract Graph<String> read(String filename);
    public abstract void addEdge(Edge<T> edge);

    public abstract void removeEdge(Edge<T> edge);

    public abstract void addVertex(Vertex<T> vertex);

    public abstract void removeVertex(Vertex<T> vertex);

    public abstract void changeValueEdge(Edge<T> edge, int newValue);

    public abstract HashMap<Vertex<T>, Integer> shortestPath(Vertex<T> vertex);
}
