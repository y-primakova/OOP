package ru.nsu.primakova;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class IncidenceMatrix.
 */
public class IncidenceMatrix<T> extends Graph<T> {
    private final HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> incidenceMatrix;

    /**
     * Class constructor.
     *
     * @param value - value of the new edge
     * @param start - start vertex of the new edge
     * @param end - end vertex of the new edge
     */
    public IncidenceMatrix(int value, Vertex<T> start, Vertex<T> end) {
        super(value, start, end);
        this.incidenceMatrix = new HashMap<>();
        this.incidenceMatrix.put(start, new HashMap<>());
        this.incidenceMatrix.put(end, new HashMap<>());
        this.incidenceMatrix.get(start).put(end, value);
        this.incidenceMatrix.get(end).put(start, -value);
    }

    /**
     * Class constructor.
     *
     * @param edge - new edge
     */
    public IncidenceMatrix(Edge<T> edge) {
        super(edge);
        this.incidenceMatrix = new HashMap<>();
        this.incidenceMatrix.put(edge.get_startVertex(), new HashMap<>());
        this.incidenceMatrix.put(edge.get_endVertex(), new HashMap<>());
        this.incidenceMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
        this.incidenceMatrix.get(edge.get_endVertex()).put(edge.get_startVertex(), -edge.get_value());
    }

    /**
     * Class constructor.
     *
     * @param listEdge - list of new edges
     * @param listVertex - list of new vertexes
     */
    public IncidenceMatrix(ArrayList<Edge<T>> listEdge, ArrayList<Vertex<T>> listVertex) {
        super(listEdge, listVertex);
        this.incidenceMatrix = new HashMap<>();
        for (var vertex : listVertex) {
            this.incidenceMatrix.put(vertex, new HashMap<>());
        }
        for (var edge : listEdge) {
            if (!this.incidenceMatrix.containsKey(edge.get_endVertex())) {
                this.incidenceMatrix.put(edge.get_endVertex(), new HashMap<>());
            }
            if (!this.incidenceMatrix.containsKey(edge.get_startVertex())) {
                this.incidenceMatrix.put(edge.get_startVertex(), new HashMap<>());
            }
            this.incidenceMatrix.get(edge.get_startVertex()).put(edge.get_endVertex(), edge.get_value());
            this.incidenceMatrix.get(edge.get_endVertex()).put(edge.get_startVertex(), -edge.get_value());
        }
    }

    public HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> get_incidenceMatrix() {
        return this.incidenceMatrix;
    }

    /**
     * load a graph from a txt file.
     *
     * @param filename - file name
     * @return AdjacencyList
     */
    public IncidenceMatrix<String> read(String filename) {
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
        return new IncidenceMatrix<>(listEdge, listVertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.get_endVertex());
        addVertex(edge.get_startVertex());
        var x = this.incidenceMatrix.get(edge.get_startVertex());
        x.put(edge.get_endVertex(), edge.get_value());
        var y = this.incidenceMatrix.get(edge.get_endVertex());
        y.put(edge.get_startVertex(), -edge.get_value());
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.incidenceMatrix.containsKey(vertex)) {
            this.incidenceMatrix.put(vertex, new HashMap<>());
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        this.incidenceMatrix.get(edge.get_startVertex()).remove(edge.get_endVertex());
        this.incidenceMatrix.get(edge.get_endVertex()).remove(edge.get_startVertex());
        if (this.incidenceMatrix.get(edge.get_startVertex()).isEmpty()) {
            removeVertex(edge.get_startVertex());
        }
        if (this.incidenceMatrix.get(edge.get_endVertex()).isEmpty()) {
            removeVertex(edge.get_endVertex());
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        for (var v : this.incidenceMatrix.keySet()) {
            this.incidenceMatrix.get(v).remove(vertex);
        }
        this.incidenceMatrix.remove(vertex);
    }

    @Override
    public HashMap<Vertex<T>, Integer> shortestPath(Vertex<T> vertex) {
        HashMap<Vertex<T>, Integer> minDist = new HashMap<>();
        ArrayList<Vertex<T>> needToVisit = new ArrayList<>();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        minDist.put(vertex, 0);
        needToVisit.add(vertex);
        while (!needToVisit.isEmpty()) {
            var v = needToVisit.get(0);
            if (visited.contains(v)) {
                needToVisit.remove(v);
                continue;
            }
            for (var key : this.incidenceMatrix.get(v).keySet()) {
                if (this.incidenceMatrix.get(v).get(key) >= 0) {
                    needToVisit.add(key);
                    if (minDist.containsKey(key)) {
                        if (minDist.get(key) > this.incidenceMatrix.get(v).get(key) + minDist.get(v)) {
                            minDist.put(key, this.incidenceMatrix.get(v).get(key) + minDist.get(v));
                        }
                    } else {
                        minDist.put(key, this.incidenceMatrix.get(v).get(key) + minDist.get(v));
                    }
                }
            }
            needToVisit.remove(v);
            visited.add(v);
        }
        return minDist;
    }

    /**
     * shortestPath as a string.
     *
     * @param vertex - the vertex from which the distances are calculated
     * @return string
     */
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
        for (var vertex1 : this.incidenceMatrix.keySet()) {
            str.append(vertex1.get_name()).append("  ");
        }
        for (var vertex1 : this.incidenceMatrix.keySet()) {
            str.append("\n");
            str.append(vertex1.get_name()).append("  ");
            for (var vertex2 : this.incidenceMatrix.keySet()) {
                if (!this.incidenceMatrix.get(vertex1).containsKey(vertex2)) {
                    str.append("0\t");
                } else {
                    str.append(this.incidenceMatrix.get(vertex1).get(vertex2)).append("\t");
                }
            }
        }
        return str.toString();
    }
}