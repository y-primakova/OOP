package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

/**
 * Class TestIncidenceMatrix.
 */
public class TestIncidenceMatrix {

    @Test
    public void testRead() {
        var readGraph = new IncidenceMatrix<String>(new ArrayList<>(), new ArrayList<>());
        readGraph = readGraph.read("src/test/resources/graph1.txt");
        var v1 = (new Vertex<>("v1")).listAddVertex(readGraph.listVertex);
        var v2 = (new Vertex<>("v2")).listAddVertex(readGraph.listVertex);
        var v3 = (new Vertex<>("v3")).listAddVertex(readGraph.listVertex);
        var v4 = (new Vertex<>("v4")).listAddVertex(readGraph.listVertex);
        var v5 = (new Vertex<>("v5")).listAddVertex(readGraph.listVertex);
        var v6 = (new Vertex<>("v6")).listAddVertex(readGraph.listVertex);
        var v7 = (new Vertex<>("v7")).listAddVertex(readGraph.listVertex);
        var v8 = (new Vertex<>("v8")).listAddVertex(readGraph.listVertex);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.put(v4, new HashMap<>());
        actual.put(v5, new HashMap<>());
        actual.put(v6, new HashMap<>());
        actual.put(v7, new HashMap<>());
        actual.put(v8, new HashMap<>());
        actual.get(v1).put(v2, 1);
        actual.get(v2).put(v1, -1);
        actual.get(v2).put(v3, 2);
        actual.get(v3).put(v2, -2);
        actual.get(v1).put(v7, 3);
        actual.get(v7).put(v1, -3);
        actual.get(v7).put(v6, 4);
        actual.get(v6).put(v7, -4);
        actual.get(v2).put(v4, 5);
        actual.get(v4).put(v2, -5);
        actual.get(v4).put(v6, 6);
        actual.get(v6).put(v4, -6);
        actual.get(v4).put(v5, 7);
        actual.get(v5).put(v4, -7);
        actual.get(v3).put(v4, 8);
        actual.get(v4).put(v3, -8);
        actual.get(v8).put(v1, 9);
        actual.get(v1).put(v8, -9);

        assertEquals(readGraph.get_incidenceMatrix(), actual);
    }

    @Test
    public void testAddEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new IncidenceMatrix<>(3, v1, v2);
        g.addEdge(new Edge<>(2, v2, v3));

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);
        actual.get(v2).put(v1, -3);
        actual.get(v2).put(v3, 2);
        actual.get(v3).put(v2, -2);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testAddVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new IncidenceMatrix<>(3, v1, v2);
        g.addVertex(v3);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);
        actual.get(v2).put(v1, -3);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testRemoveEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge = new Edge<>(3, v1, v2);
        var g = new IncidenceMatrix<>(edge);
        g.addEdge(new Edge<>(2, v2, v3));
        g.removeEdge(edge);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v2).put(v3, 2);
        actual.get(v3).put(v2, -2);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testRemoveVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new IncidenceMatrix<>(3, v1, v2);
        g.addEdge(new Edge<>(2, v2, v3));
        g.removeVertex(v2);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v3, new HashMap<>());

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testShortestPath() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var v4 = new Vertex<>("v4");
        var v5 = new Vertex<>("v5");
        var g = new IncidenceMatrix<>(7, v1, v2);
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
