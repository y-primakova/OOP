package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

public class AdjacencyMatrix<T> extends Graph<T>{
    private final HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> adjacencyMatrix;

//    `public AdjacencyMatrix(Graph<T> graph) {
//        this.adjacencyMatrix = new HashMap<>();
////        for (var vertex: graph.listVertex) {
////            adjacencyMatrix.put(vertex, new HashMap<>());
////        }
//        for (var edge: graph.listEdge) {
//            adjacencyMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), true);
//        }
//    }

//    public AdjacencyMatrix(int value, T nameStart, T nameEnd) throws WrongVertexNameException {
//        super(value, nameStart, nameEnd);
//        this.adjacencyMatrix = new HashMap<>();
//        var edge = new Edge<T>(value, nameStart, nameEnd);
//        this.adjacencyMatrix.put(edge.get_startVertex(), new HashMap<>());
//        this.adjacencyMatrix.put(edge.get_endVertex(), new HashMap<>());
//        this.adjacencyMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), value);
//    }

    public AdjacencyMatrix(int value, Vertex<T> start, Vertex<T> end) throws WrongVertexNameException {
        super(value, start, end);
        this.adjacencyMatrix = new HashMap<>();
        this.adjacencyMatrix.put(start, new HashMap<>());
        this.adjacencyMatrix.put(end, new HashMap<>());
        this.adjacencyMatrix.get(start).put(end, value);
    }

    public AdjacencyMatrix(Edge<T> edge) {
        super(edge);
        this.adjacencyMatrix = new HashMap<>();
        this.adjacencyMatrix.put(edge.get_startVertex(), new HashMap<>());
        this.adjacencyMatrix.put(edge.get_endVertex(), new HashMap<>());
        this.adjacencyMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
    }

    public HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> get_adjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.get_endVertex());
        addVertex(edge.get_startVertex());
        this.adjacencyMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.adjacencyMatrix.containsKey(vertex)) {
            this.adjacencyMatrix.put(vertex, new HashMap<>());
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        this.adjacencyMatrix.get(edge.get_startVertex()).remove(edge.get_endVertex());
        if (this.adjacencyMatrix.get(edge.get_startVertex()).isEmpty()) {
            removeVertex(edge.get_startVertex());
        }
        if (this.adjacencyMatrix.get(edge.get_endVertex()).isEmpty()) {
            removeVertex(edge.get_endVertex());
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        for (var v : this.adjacencyMatrix.keySet()) {
            this.adjacencyMatrix.get(v).remove(vertex);
        }
        this.adjacencyMatrix.remove(vertex);
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
            need_to_visit.addAll(this.adjacencyMatrix.get(v).keySet());
            for (var key : this.adjacencyMatrix.get(v).keySet()) {
                if(min_dist.containsKey(key)){
                    if(min_dist.get(key) > this.adjacencyMatrix.get(v).get(key) + min_dist.get(v)) {
                        min_dist.put(key, this.adjacencyMatrix.get(v).get(key) + min_dist.get(v));
                    }
                }
                else {
                    min_dist.put(key, this.adjacencyMatrix.get(v).get(key) + min_dist.get(v));
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
        str.append("   ");
        for (var vertex1: this.adjacencyMatrix.keySet()) {//this.adjacencyMatrix.keySet()
            str.append(vertex1.get_name()).append(" ");
        }
        for (var vertex1: this.adjacencyMatrix.keySet()) {
            str.append("\n");
            str.append(vertex1.get_name()).append("  ");
            for (var vertex2: this.adjacencyMatrix.keySet()) {
                if(!this.adjacencyMatrix.get(vertex1).containsKey(vertex2)) {
                    str.append("0  ");
                }
                else {
                    str.append(this.adjacencyMatrix.get(vertex1).get(vertex2)).append("  ");
                }
            }
        }
        return str.toString();
    }
}
