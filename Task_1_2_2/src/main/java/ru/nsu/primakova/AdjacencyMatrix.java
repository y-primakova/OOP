package ru.nsu.primakova;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class AdjacencyMatrix.
 */
public class AdjacencyMatrix<T> extends Graph<T> {
    private final HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> adjacencyMatrix;

    /**
     * Class constructor.
     *
     * @param value - value of the new edge
     * @param start - start vertex of the new edge
     * @param end - end vertex of the new edge
     */
    public AdjacencyMatrix(int value, Vertex<T> start, Vertex<T> end) {
        super(value, start, end);
        this.adjacencyMatrix = new HashMap<>();
        this.adjacencyMatrix.put(start, new HashMap<>());
        this.adjacencyMatrix.put(end, new HashMap<>());
        this.adjacencyMatrix.get(start).put(end, value);
    }

    /**
     * Class constructor.
     *
     * @param edge - new edge
     */
    public AdjacencyMatrix(Edge<T> edge) {
        super(edge);
        this.adjacencyMatrix = new HashMap<>();
        this.adjacencyMatrix.put(edge.get_startVertex(), new HashMap<>());
        this.adjacencyMatrix.put(edge.get_endVertex(), new HashMap<>());
        var x = this.adjacencyMatrix.get(edge.get_startVertex());
        x.put(edge.get_endVertex(), edge.get_value());
    }

    /**
     * Class constructor.
     *
     * @param listEdge - list of new edges
     * @param listVertex - list of new vertexes
     */
    public AdjacencyMatrix(ArrayList<Edge<T>> listEdge, ArrayList<Vertex<T>> listVertex) {
        super(listEdge, listVertex);
        this.adjacencyMatrix = new HashMap<>();
        for (var vertex : listVertex) {
            this.adjacencyMatrix.put(vertex, new HashMap<>());
        }
        for (var edge : listEdge) {
            if (!this.adjacencyMatrix.containsKey(edge.get_endVertex())) {
                this.adjacencyMatrix.put(edge.get_endVertex(), new HashMap<>());
            }
            if (!this.adjacencyMatrix.containsKey(edge.get_startVertex())) {
                this.adjacencyMatrix.put(edge.get_startVertex(), new HashMap<>());
            }
            var x = this.adjacencyMatrix.get(edge.get_startVertex());
            x.put(edge.get_endVertex(), edge.get_value());
        }
    }

    public HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> get_adjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    @Override
    public AdjacencyMatrix<String> read(String filename) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int edgesAmount = scanner.nextInt();

        var listVertex = new ArrayList<Vertex<String>>();
        var listEdge = new ArrayList<Edge<String>>();

        for (int i = 0; i < edgesAmount; i++) {
            var startVertex = new Vertex<>(scanner.next());
            var endVertex = new Vertex<>(scanner.next());
            var value = scanner.nextInt();

            listEdge.add(new Edge<>(value, startVertex.listAddVertex(listVertex), endVertex.listAddVertex(listVertex)));
        }
        return new AdjacencyMatrix<>(listEdge, listVertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.get_endVertex());
        addVertex(edge.get_startVertex());
        var x = this.adjacencyMatrix.get(edge.get_startVertex());
        x.put(edge.get_endVertex(), edge.get_value());
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
    public void changeValueEdge(Edge<T> edge, int newValue) {
        this.adjacencyMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), newValue);
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
            needToVisit.addAll(this.adjacencyMatrix.get(v).keySet());
            for (var key : this.adjacencyMatrix.get(v).keySet()) {
                if (res.contains(key)) {
                    if (key.get_dist() > this.adjacencyMatrix.get(v).get(key) + v.get_dist()) {
                        key.change_dist(this.adjacencyMatrix.get(v).get(key) + v.get_dist());
                    }
                } else {
                    key.change_dist(this.adjacencyMatrix.get(v).get(key) + v.get_dist());
                    res.add(key);
                }
            }
            needToVisit.remove(v);
            visited.add(v);
        }
        for (int i = 0; i < res.size() - 1; i++){
            for (int j = 0; j < res.size() - i - 1; j++) {
                if(res.get(j).get_dist() > res.get(j+1).get_dist()) {
                    res.add(j, res.get(j+1));
                    res.remove(j+2);
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
        str.append("    ");
        for (var vertex1 : this.adjacencyMatrix.keySet()) {
            str.append(vertex1.get_name()).append("  ");
        }
        for (var vertex1 : this.adjacencyMatrix.keySet()) {
            str.append("\n");
            str.append(vertex1.get_name()).append("  ");
            for (var vertex2 : this.adjacencyMatrix.keySet()) {
                if (!this.adjacencyMatrix.get(vertex1).containsKey(vertex2)) {
                    str.append("0\t");
                } else {
                    str.append(this.adjacencyMatrix.get(vertex1).get(vertex2)).append("\t");
                }
            }
        }
        return str.toString();
    }
}
