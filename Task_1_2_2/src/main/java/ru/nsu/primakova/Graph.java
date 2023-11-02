package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Graph.
 */
public abstract class Graph<T> {
    protected ArrayList<Edge<T>> listEdge;
    protected ArrayList<Vertex<T>> listVertex;

    public Graph(int value, Vertex<T> start, Vertex<T> end) {
        this.listEdge = new ArrayList<>();
        this.listVertex = new ArrayList<>();
        listEdge.add(new Edge<T>(value, start, end));
        listVertex.add(start);
        listVertex.add(end);
    }

    public Graph(Edge<T> edge) {
        this.listEdge = new ArrayList<>();
        this.listVertex = new ArrayList<>();
        listEdge.add(edge);
        listVertex.add(edge.get_startVertex());
        listVertex.add(edge.get_endVertex());
    }

    public ArrayList<Edge<T>> get_listEdge() {
        return this.listEdge;
    }

    public ArrayList<Vertex<T>> get_listVertex() {
        return this.listVertex;
    }

//    public void addEdge(Edge<T> edge) {
//        if (!this.listEdge.contains(edge)) {
//            this.listEdge.add(edge);
//            if (!this.listVertex.contains(edge.get_endVertex())) {
//                this.listVertex.add(edge.get_endVertex());
//            }
//            if (!this.listVertex.contains(edge.get_startVertex())) {
//                this.listVertex.add(edge.get_startVertex());
//            }
//        }
//    }
//
//    public void removeEdge(Edge<T> edge) {
//        if (this.listEdge.contains(edge)) {
//            this.listVertex.remove(edge.get_endVertex());
//            this.listVertex.remove(edge.get_startVertex());
//            this.listEdge.remove(edge);
//        }
//    }
//    public void ShortestPath() {
//
//    }

    public abstract void addEdge(Edge<T> edge);

    public abstract void addVertex(Vertex<T> vertex);

    public abstract void removeEdge(Edge<T> edge);

    public abstract void removeVertex(Vertex<T> vertex);

    public abstract HashMap<Vertex<T>, Integer> shortestPath(Vertex<T> vertex);
}
