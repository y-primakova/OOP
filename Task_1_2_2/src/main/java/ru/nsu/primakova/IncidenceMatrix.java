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
    private final HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> incidenceMatrix;

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
        var edge = new Edge<>(value, start, end);
        this.incidenceMatrix.get(start).put(edge, value);
        this.incidenceMatrix.get(end).put(edge, -value);
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
        this.incidenceMatrix.get(edge.get_startVertex()).put(edge, edge.get_value());
        this.incidenceMatrix.get(edge.get_endVertex()).put(edge, -edge.get_value());
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
            for (var v : this.incidenceMatrix.keySet()) {
                this.incidenceMatrix.get(v).put(edge, 0);
            }
            this.incidenceMatrix.get(edge.get_startVertex()).put(edge, edge.get_value());
            this.incidenceMatrix.get(edge.get_endVertex()).put(edge, -edge.get_value());
        }
    }

    public HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> get_incidenceMatrix() {
        return this.incidenceMatrix;
    }

    @Override
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
        scanner.close();
        return new IncidenceMatrix<>(listEdge, listVertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.get_endVertex());
        addVertex(edge.get_startVertex());
        for (var v : this.incidenceMatrix.keySet()) {
            this.incidenceMatrix.get(v).put(edge, 0);
        }
        var x = this.incidenceMatrix.get(edge.get_startVertex());
        x.put(edge, edge.get_value());
        var y = this.incidenceMatrix.get(edge.get_endVertex());
        y.put(edge, -edge.get_value());
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!this.incidenceMatrix.containsKey(vertex)) {
            this.incidenceMatrix.put(vertex, new HashMap<>());
            for (var v : this.incidenceMatrix.keySet()) {
                for (var edge : this.incidenceMatrix.get(v).keySet()) {
                    this.incidenceMatrix.get(vertex).put(edge, 0);
                }
            }
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        for (var v : this.incidenceMatrix.keySet()) {
            this.incidenceMatrix.get(v).remove(edge);
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        for (var edge : this.incidenceMatrix.get(vertex).keySet()) {
            if (vertex.equals(edge.get_startVertex())) {
                for (var v : this.incidenceMatrix.keySet()) {
                    if (!v.equals(vertex)) {
                        this.incidenceMatrix.get(v).remove(edge);
                    }
                }
            }
            if (vertex.equals(edge.get_endVertex())) {
                for (var v : this.incidenceMatrix.keySet()) {
                    if (!v.equals(vertex)) {
                        this.incidenceMatrix.get(v).remove(edge);
                    }
                }
            }
        }
        this.incidenceMatrix.remove(vertex);
    }

    @Override
    public void changeValueEdge(Edge<T> edge, int newValue) {
        this.incidenceMatrix.get(edge.get_startVertex()).put(edge, newValue);
        this.incidenceMatrix.get(edge.get_endVertex()).put(edge, -newValue);
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
            for (var edge : this.incidenceMatrix.get(v).keySet()) {
                if (this.incidenceMatrix.get(v).get(edge) > 0) {
                    needToVisit.add(edge.get_endVertex());
                    if (res.contains(edge.get_endVertex())) {
                        if (edge.get_endVertex().get_dist() > this.incidenceMatrix.get(v).get(edge) + v.get_dist()) {
                            edge.get_endVertex().change_dist(this.incidenceMatrix.get(v).get(edge) + v.get_dist());
                        }
                    } else {
                        edge.get_endVertex().change_dist(this.incidenceMatrix.get(v).get(edge) + v.get_dist());
                        res.add(edge.get_endVertex());
                    }
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
        str.append("    ");
        for (var vertex : this.incidenceMatrix.keySet()) {
            for (var edge : this.incidenceMatrix.get(vertex).keySet()) {
                str.append(edge.get_startVertex().get_name()).append("->").append(edge.get_endVertex().get_name()).append("  ");
            }
            break;
        }
        for (var vertex : this.incidenceMatrix.keySet()) {
            str.append("\n");
            str.append(vertex.get_name()).append("     ");
            for (var edge : this.incidenceMatrix.get(vertex).keySet()) {
                str.append(this.incidenceMatrix.get(vertex).get(edge)).append("\t\t");
            }
        }
        return str.toString();
    }
}