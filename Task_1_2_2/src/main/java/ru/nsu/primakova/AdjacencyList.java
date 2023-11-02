package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

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
        for (var v : this.adjacencyList.keySet()) {
            this.adjacencyList.get(v).remove(vertex);
        }
        this.adjacencyList.remove(vertex);
    }

    @Override
    public HashMap<Vertex<T>, Integer> shortestPath(Vertex<T> vertex) {
        HashMap<Vertex<T>, Integer> min_dist = new HashMap<>();
        ArrayList<Vertex<T>> need_to_visit = new ArrayList<>();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        min_dist.put(vertex, 0);
        need_to_visit.add(vertex);
        while (!need_to_visit.isEmpty()) {
            var v =need_to_visit.get(0);
            if (visited.contains(v)) {
                need_to_visit.remove(v);
                continue;
            }
            need_to_visit.addAll(this.adjacencyList.get(v).keySet());
            for (var key : this.adjacencyList.get(v).keySet()) {
                if(min_dist.containsKey(key)){
                    if(min_dist.get(key) > this.adjacencyList.get(v).get(key) + min_dist.get(v)) {
                        min_dist.put(key, this.adjacencyList.get(v).get(key) + min_dist.get(v));
                    }
                }
                else {
                    min_dist.put(key, this.adjacencyList.get(v).get(key) + min_dist.get(v));
                }
            }
            need_to_visit.remove(v);
            visited.add(v);
        }
        return min_dist;
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
        str.append("    ");
        for (var vertex1: this.adjacencyList.keySet()) {
            str.append(vertex1.get_name()).append("  ");
        }
        for (var vertex1: this.adjacencyList.keySet()) {
            str.append("\n");
            str.append(vertex1.get_name()).append("  ");
            for (var vertex2: this.adjacencyList.keySet()) {
                if(!this.adjacencyList.get(vertex1).containsKey(vertex2)) {
                    str.append("0\t");
                }
                else {
                    str.append(this.adjacencyList.get(vertex1).get(vertex2)).append("\t");
                }
            }
        }
        return str.toString();
    }
}