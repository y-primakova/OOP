package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Graph.
 */
public abstract class Graph<T> {
    protected ArrayList<Edge<T>> listEdge;
    protected ArrayList<Vertex<T>> listVertex;

//    public Graph(int value, T nameStart, T nameEnd) {
//        this.listEdge = new ArrayList<>();
//        this.listVertex = new ArrayList<>();
//        var edge = new Edge<T>(value, nameStart, nameEnd);
//        listEdge.add(edge);
//        listVertex.add(edge.get_startVertex());
//        listVertex.add(edge.get_endVertex());
//    }

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

//    public Graph(int value, T nameStart, T nameEnd) {
//        this.edge = new Edge<T>(value, nameStart, nameEnd);
//        this.nextEdge = new ArrayList<>();
//        this.lastEdge = new ArrayList<>();
//    }
//
//    public Graph(int value, Vertex<T> start, Vertex<T> end) throws WrongVertexNameException {
//        this.edge = new Edge<T>(value, start, end);
//        this.nextEdge = new ArrayList<>();
//        this.lastEdge = new ArrayList<>();
//    }
//
//    public Graph(Edge<T> edge) {
//        this.edge = edge;
//        this.nextEdge = new ArrayList<>();
//        this.lastEdge = new ArrayList<>();
//    }
//
//    public Graph(Graph<T> graph) {
//        this.edge = graph.edge;
//        this.nextEdge = graph.nextEdge;
//        this.lastEdge = graph.lastEdge;
//    }
//
//    /**
//     * Add a new edge directed from the end vertex of this graph.
//     *
//     * @param edge - a new edge of the graph
//     * @return new graph
//     */
//    public Graph<T> addEdge_fromEndVertex(Edge<T> edge) throws WrongVertexNameException {
//        var graph = new Graph<T>(edge);
//        this.nextEdge.add(graph);
//        graph.lastEdge.add(this);
//        if (this.edge.get_startVertex().get_name() == edge.get_endVertex().get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        if (this.edge.get_endVertex().get_name() != edge.get_startVertex().get_name()) {
//            if (this.edge.get_endVertex().get_name() != null && edge.get_startVertex().get_name()!= null) {
//                throw new WrongVertexNameException("The name of the common vertex of the graph is different.");
//            }
//            else if (this.edge.get_endVertex().get_name() == null) {
//                this.edge.get_endVertex().change_name(edge.get_startVertex().get_name());
//            }
//            else {
//                edge.get_startVertex().change_name(this.edge.get_endVertex().get_name());
//            }
//        }
//        return graph;
//    }
//
//    /**
//     * Add a new edge directed to the end vertex of this graph.
//     *
//     * @param edge - a new edge of the graph
//     * @return new graph
//     */
//    public Graph<T> addEdge_toEndVertex(Edge<T> edge) throws WrongVertexNameException {
//        var graph = new Graph<T>(edge);
//        this.nextEdge.add(graph);
//        graph.nextEdge.add(this);
//        if (this.edge.get_startVertex().get_name() == edge.get_startVertex().get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        if (this.edge.get_endVertex().get_name() != edge.get_endVertex().get_name()) {
//            if (this.edge.get_endVertex().get_name() != null && edge.get_endVertex().get_name()!= null) {
//                throw new WrongVertexNameException("The name of the common vertex of the graph is different.");
//            }
//            else if (this.edge.get_endVertex().get_name() == null) {
//                this.edge.get_endVertex().change_name(edge.get_endVertex().get_name());
//            }
//            else {
//                edge.get_endVertex().change_name(this.edge.get_endVertex().get_name());
//            }
//        }
//        return graph;
//    }
//
//    /**
//     * Add a new edge directed from the start vertex of this graph.
//     *
//     * @param edge - a new edge of the graph
//     * @return new graph
//     */
//    public Graph<T> addEdge_fromStartVertex(Edge<T> edge) throws WrongVertexNameException {
//        var graph = new Graph<T>(edge);
//        this.lastEdge.add(graph);
//        graph.lastEdge.add(this);
//        if (this.edge.get_endVertex().get_name() == edge.get_endVertex().get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        if (this.edge.get_startVertex().get_name() != edge.get_startVertex().get_name()) {
//            if (this.edge.get_startVertex().get_name() != null && edge.get_startVertex().get_name()!= null) {
//                throw new WrongVertexNameException("The name of the common vertex of the graph is different.");
//            }
//            else if (this.edge.get_startVertex().get_name() == null) {
//                this.edge.get_startVertex().change_name(edge.get_startVertex().get_name());
//            }
//            else {
//                edge.get_startVertex().change_name(this.edge.get_startVertex().get_name());
//            }
//        }
//        return graph;
//    }
//
//    /**
//     * Add a new edge directed to the start vertex of this graph.
//     *
//     * @param edge - a new edge of the graph
//     * @return new graph
//     */
//    public Graph<T> addEdge_toStartVertex(Edge<T> edge) throws WrongVertexNameException {
//        var graph = new Graph<T>(edge);
//        this.lastEdge.add(graph);
//        graph.nextEdge.add(this);
//        if (this.edge.get_endVertex().get_name() == edge.get_startVertex().get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        if (this.edge.get_startVertex().get_name() != edge.get_endVertex().get_name()) {
//            if (this.edge.get_startVertex().get_name() != null && edge.get_endVertex().get_name()!= null) {
//                throw new WrongVertexNameException("The name of the common vertex of the graph is different.");
//            }
//            else if (this.edge.get_startVertex().get_name() == null) {
//                this.edge.get_startVertex().change_name(edge.get_endVertex().get_name());
//            }
//            else {
//                edge.get_endVertex().change_name(this.edge.get_startVertex().get_name());
//            }
//        }
//        return graph;
//    }
//
//    /**
//     * add a new edge directed from the end vertex of this graph.
//     *
//     * @param value - value of the new edge of the graph
//     * @param vertex - a new vertex of the graph
//     * @return new graph
//     */
//    public Graph<T> addVertex_fromEndVertex(int value, Vertex<T> vertex) throws WrongVertexNameException {
//        var graph = new Graph<T>(value, this.edge.get_endVertex(), vertex);
//        this.nextEdge.add(graph);
//        graph.lastEdge.add(this);
//        if (this.edge.get_startVertex().get_name() == vertex.get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        return graph;
//    }
//
//    /**
//     * add a new edge directed to the end vertex of this graph.
//     *
//     * @param value - value of the new edge of the graph
//     * @param vertex - a new vertex of the graph
//     * @return new graph
//     */
//    public Graph<T> addVertex_toEndVertex(int value, Vertex<T> vertex) throws WrongVertexNameException {
//        var graph = new Graph<T>(value, vertex, this.edge.get_endVertex());
//        this.nextEdge.add(graph);
//        graph.nextEdge.add(this);
//        if (this.edge.get_startVertex().get_name() == vertex.get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        return graph;
//    }
//
//    /**
//     * add a new edge directed from the start vertex of this graph.
//     *
//     * @param value - value of the new edge of the graph
//     * @param vertex - a new vertex of the graph
//     * @return new graph
//     */
//    public Graph<T> addVertex_fromStartVertex(int value, Vertex<T> vertex) throws WrongVertexNameException {
//        var graph = new Graph<T>(value, this.edge.get_startVertex(), vertex);
//        this.lastEdge.add(graph);
//        graph.lastEdge.add(this);
//        if (this.edge.get_endVertex().get_name() == vertex.get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        return graph;
//    }
//
//    /**
//     * add a new edge directed to the start vertex of this graph.
//     *
//     * @param value - value of the new edge of the graph
//     * @param vertex - a new vertex of the graph
//     * @return new graph
//     */
//    public Graph<T> addVertex_toStartVertex(int value, Vertex<T> vertex) throws WrongVertexNameException {
//        var graph = new Graph<T>(value, vertex, this.edge.get_startVertex());
//        this.lastEdge.add(graph);
//        graph.nextEdge.add(this);
//        if (this.edge.get_endVertex().get_name() == vertex.get_name()) {
//            throw new WrongVertexNameException("The same name of different vertices of the graph.");
//        }
//        return graph;
//    }
//
//    public void removeEdge() {
//        for (var edge : this.lastEdge) {
//            if (edge.lastEdge.contains(this)) {
//                edge.lastEdge.remove(this);
//            }
//            else {
//                edge.nextEdge.remove(this);
//            }
//        }
//        for (var edge : this.nextEdge) {
//            if (edge.lastEdge.contains(this)) {
//                edge.lastEdge.remove(this);
//            }
//            else {
//                edge.nextEdge.remove(this);
//            }
//        }
//        this.lastEdge = new ArrayList<>();
//        this.nextEdge = new ArrayList<>();
//    }
//
//    public void removeStartVertex() {
//        for (var edge : this.lastEdge) {
//            edge.removeEdge();
//        }
//        this.removeEdge();
//    }
//
//    public void removeEndVertex() {
//        for (var edge : this.nextEdge) {
//            edge.removeEdge();
//        }
//        this.removeEdge();
//    }
}
