package ru.nsu.primakova;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class AdjacencyList.
 */
public class AdjacencyList<T> extends Graph<T> {
    private final HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> adjacencyList;

    /**
     * Class constructor.
     *
     * @param value - value of the new edge
     * @param start - start vertex of the new edge
     * @param end - end vertex of the new edge
     */
    public AdjacencyList(int value, Vertex<T> start, Vertex<T> end) {
        super(value, start, end);
        this.adjacencyList = new HashMap<>();
        this.adjacencyList.put(start, new HashMap<>());
        this.adjacencyList.put(end, new HashMap<>());
        this.adjacencyList.get(start).put(end, value);
    }

    /**
     * Class constructor.
     *
     * @param edge - new edge
     */
    public AdjacencyList(Edge<T> edge) {
        super(edge);
        this.adjacencyList = new HashMap<>();
        this.adjacencyList.put(edge.get_startVertex(), new HashMap<>());
        this.adjacencyList.put(edge.get_endVertex(), new HashMap<>());
        this.adjacencyList.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
    }

    /**
     * Class constructor.
     *
     * @param listEdge - list of new edges
     * @param listVertex - list of new vertexes
     */
    public AdjacencyList(ArrayList<Edge<T>> listEdge, ArrayList<Vertex<T>> listVertex) {
        super(listEdge, listVertex);
        this.adjacencyList = new HashMap<>();
        for (var vertex : listVertex) {
            this.adjacencyList.put(vertex, new HashMap<>());
        }
        for (var edge : listEdge) {
            if (!this.adjacencyList.containsKey(edge.get_endVertex())) {
                this.adjacencyList.put(edge.get_endVertex(), new HashMap<>());
            }
            if (!this.adjacencyList.containsKey(edge.get_startVertex())) {
                this.adjacencyList.put(edge.get_startVertex(), new HashMap<>());
            }
            var x = this.adjacencyList.get(edge.get_startVertex());
            x.put(edge.get_endVertex(), edge.get_value());
        }
    }

    public HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> get_adjacencyList() {
        return this.adjacencyList;
    }

  @Override
    public AdjacencyList<T> read(String filename) {
        super.read(filename);
        return new AdjacencyList<>(this.listEdge, this.listVertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.get_endVertex());
        addVertex(edge.get_startVertex());
        var x = this.adjacencyList.get(edge.get_startVertex());
        x.put(edge.get_endVertex(), edge.get_value());
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
    public void changeValueEdge(Edge<T> edge, int newValue) {
        this.adjacencyList.get(edge.get_startVertex()).put(edge.get_endVertex(), newValue);
    }

    @Override
    public ArrayList<Vertex<T>> shortestPath(Vertex<T> vertex) {
        ArrayList<Vertex<T>> res = new ArrayList<>();
        ArrayList<Vertex<T>> needToVisit = new ArrayList<>();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        vertex.change_dist(0);
        res.add(vertex);
        needToVisit.add(vertex);
        while (!needToVisit.isEmpty()) {
            var v = needToVisit.get(0);
            if (visited.contains(v)) {
                needToVisit.remove(v);
                continue;
            }
            needToVisit.addAll(this.adjacencyList.get(v).keySet());
            for (var key : this.adjacencyList.get(v).keySet()) {
                if (res.contains(key)) {
                    if (key.get_dist() > this.adjacencyList.get(v).get(key) + v.get_dist()) {
                        key.change_dist(this.adjacencyList.get(v).get(key) + v.get_dist());
                    }
                } else {
                    key.change_dist(this.adjacencyList.get(v).get(key) + v.get_dist());
                    res.add(key);
                }
            }
            needToVisit.remove(v);
            visited.add(v);
        }
        for (int i = 0; i < res.size() - 1; i++) {
            for (int j = 0; j < res.size() - i - 1; j++) {
                if (res.get(j).get_dist() > res.get(j + 1).get_dist()) {
                    res.add(j, res.get(j + 1));
                    res.remove(j + 2);
                }
            }
        }

        return res;
    }

    /**
     * shortestPath as a string.
     *
     * @param vertex - the vertex from which the distances are calculated
     * @return string
     */
    public String shortestPathString(Vertex<T> vertex) {
        StringBuilder str = new StringBuilder();
        for (var v : this.shortestPath(vertex)) {
            str.append(v.get_name()).append(" ").append(v.get_dist()).append("\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (var vertex1 : this.adjacencyList.keySet()) {
            str.append(vertex1.get_name()).append("   ");

            for (var vertex2 : this.adjacencyList.get(vertex1).keySet()) {
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