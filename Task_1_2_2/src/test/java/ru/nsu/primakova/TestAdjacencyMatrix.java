package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

/**
 * Class TestAdjacencyMatrix.
 */
public class TestAdjacencyMatrix {

    @Test
    public void testAddEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyMatrix<>(3, v1, v2);
        g.addEdge(new Edge<>(2, v2, v3));

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);
        actual.get(v2).put(v3, 2);

        assertEquals(g.get_adjacencyMatrix(), actual);
    }

    @Test
    public void testAddVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyMatrix<>(3, v1, v2);
        g.addVertex(v3);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);

        assertEquals(g.get_adjacencyMatrix(), actual);
    }

    @Test
    public void testRemoveEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge = new Edge<>(3, v1, v2);
        var g = new AdjacencyMatrix<>(edge);
        g.addEdge(new Edge<>(2, v2, v3));
        g.removeEdge(edge);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v2).put(v3, 2);

        assertEquals(g.get_adjacencyMatrix(), actual);
    }

    @Test
    public void testRemoveVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyMatrix<>(3, v1, v2);
        g.addEdge(new Edge<>(2, v2, v3));
        g.removeVertex(v2);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v3, new HashMap<>());

        assertEquals(g.get_adjacencyMatrix(), actual);
    }

    @Test
    public void testShortestPath() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var v4 = new Vertex<>("v4");
        var v5 = new Vertex<>("v5");
        var g = new AdjacencyMatrix<>(7, v1, v2);
        g.addEdge(new Edge<>(3, v1, v4));
        g.addEdge(new Edge<>(2, v4, v2));
        g.addEdge(new Edge<>(5, v4, v3));
        g.addEdge(new Edge<>(10, v5, v1));

        HashMap<Vertex<String>, Integer> actual = new HashMap<>();
        actual.put(v1, 0);
        actual.put(v2, 5);
        actual.put(v3, 8);
        actual.put(v4, 3);

        assertEquals(g.shortestPath(v1), actual);
    }
}
