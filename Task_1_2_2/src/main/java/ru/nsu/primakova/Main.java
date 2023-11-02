package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Main.
 */
public class Main {

    /**
     * main.
     */
    public static void main(String[] args) {
        var v1 = new Vertex<String>("v1");
        var v2 = new Vertex<String>("v2");
        var v3 = new Vertex<String>("v3");
        var v4 = new Vertex<String>("v4");
        var v5 = new Vertex<String>("v5");
        var v6 = new Vertex<String>("v6");
        var v7 = new Vertex<String>("v7");
        var v8 = new Vertex<String>("v8");
        var g = new AdjacencyMatrix<String>(3, v1, v2);
        g.addEdge(new Edge<String>(2, v2, v3));
        g.addEdge(new Edge<String>(7, v1, v7));
        g.addEdge(new Edge<String>(2, v7, v6));
        g.addEdge(new Edge<String>(4, v2, v4));
        g.addEdge(new Edge<String>(5, v4, v6));
        g.addEdge(new Edge<String>(1, v4, v5));
        g.addEdge(new Edge<String>(1, v3, v4));
        g.addEdge(new Edge<String>(5, v8, v1));
        System.out.println(g);
        System.out.println(g.shortestPathString(v1));
    }
}