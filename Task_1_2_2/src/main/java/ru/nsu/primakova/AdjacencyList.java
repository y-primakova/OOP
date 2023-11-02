package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class AdjacencyList.
 */
public class AdjacencyList<T> extends Graph<T>{
    private final HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> adjacencyList;

    public AdjacencyList(int value, Vertex<T> start, Vertex<T> end) {
        super(value, start, end);
        this.adjacencyList = new HashMap<>();
        this.adjacencyList.put(start, new HashMap<>());
        this.adjacencyList.put(end, new HashMap<>());
        this.adjacencyList.get(start).put(end, value);
    }

    public AdjacencyList(Edge<T> edge) {
        super(edge);
        this.adjacencyList = new HashMap<>();
        this.adjacencyList.put(edge.get_startVertex(), new HashMap<>());
        this.adjacencyList.put(edge.get_endVertex(), new HashMap<>());
        this.adjacencyList.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
    }

    public AdjacencyList(ArrayList<Edge<T>> listEdge, ArrayList<Vertex<T>> listVertex) {
        super(listEdge, listVertex);
        this.adjacencyList = new HashMap<>();
        for (var vertex: listVertex) {
            this.adjacencyList.put(vertex, new HashMap<>());
        }
        for (var edge: listEdge) {
            this.adjacencyList.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
            if (!this.adjacencyList.containsKey(edge.get_endVertex())) {
                this.adjacencyList.put(edge.get_endVertex(), new HashMap<>());
            }
            if (!this.adjacencyList.containsKey(edge.get_startVertex())) {
                this.adjacencyList.put(edge.get_startVertex(), new HashMap<>());
            }
        }
    }

    public HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> get_adjacencyList() {
        return this.adjacencyList;
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.get_endVertex());
        addVertex(edge.get_startVertex());
        this.adjacencyList.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.adjacencyList.containsKey(vertex)) {
            this.adjacencyList.put(vertex, new HashMap<>());
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        this.adjacencyList.get(edge.get_startVertex()).remove(edge.get_endVertex());
        if (this.adjacencyList.get(edge.get_startVertex()).isEmpty()) {
            removeVertex(edge.get_startVertex());
        }
        if (this.adjacencyList.get(edge.get_endVertex()).isEmpty()) {
            removeVertex(edge.get_endVertex());
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        for (var v: this.adjacencyList.keySet()) {
            this.adjacencyList.get(v).remove(vertex);
        }
        this.adjacencyList.remove(vertex);
    }

    @Override
    public HashMap<Vertex<T>, Integer> shortestPath(Vertex<T> vertex) {
        HashMap<Vertex<T>, Integer> minDist = new HashMap<>();
        ArrayList<Vertex<T>> needToVisit = new ArrayList<>();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        minDist.put(vertex, 0);
        needToVisit.add(vertex);
        while (!needToVisit.isEmpty()) {
            var v =needToVisit.get(0);
            if (visited.contains(v)) {
                needToVisit.remove(v);
                continue;
            }
            needToVisit.addAll(this.adjacencyList.get(v).keySet());
            for (var key: this.adjacencyList.get(v).keySet()) {
                if(minDist.containsKey(key)){
                    if (minDist.get(key) > this.adjacencyList.get(v).get(key) + minDist.get(v)) {
                        minDist.put(key, this.adjacencyList.get(v).get(key) + minDist.get(v));
                    }
                }
                else {
                    minDist.put(key, this.adjacencyList.get(v).get(key) + minDist.get(v));
                }
            }
            needToVisit.remove(v);
            visited.add(v);
        }
        return minDist;
    }

    public String shortestPathString(Vertex<T> vertex) {
        StringBuilder str = new StringBuilder();
        var res = shortestPath(vertex);
        for (var key : this.shortestPath(vertex).keySet()) {
            str.append(key.get_name()).append(" ");
        }
        str.append("\n");
        str.append(this.shortestPath(vertex).values());
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (var vertex1: this.adjacencyList.keySet()) {
            str.append(vertex1.get_name()).append("   ");

            for (var vertex2: this.adjacencyList.get(vertex1).keySet()) {
                str.append("(");
                str.append(vertex2.get_name()).append(",  ");
                str.append(this.adjacencyList.get(vertex1).get(vertex2));
                str.append(");\t\t");
            }
            str.append("\n");
        }
        return str.toString();
    }
}