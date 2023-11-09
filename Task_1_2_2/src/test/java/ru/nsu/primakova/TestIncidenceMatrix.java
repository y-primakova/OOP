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

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.put(v4, new HashMap<>());
        actual.put(v5, new HashMap<>());
        actual.put(v6, new HashMap<>());
        actual.put(v7, new HashMap<>());
        actual.put(v8, new HashMap<>());
        var edge1 = (new Edge<>(1, v1, v2)).listAddEdge(readGraph.listEdge);
        var edge2 = (new Edge<>(2, v2, v3)).listAddEdge(readGraph.listEdge);
        var edge3 = (new Edge<>(3, v1, v7)).listAddEdge(readGraph.listEdge);
        var edge4 = (new Edge<>(4, v7, v6)).listAddEdge(readGraph.listEdge);
        var edge5 = (new Edge<>(5, v2, v4)).listAddEdge(readGraph.listEdge);
        var edge6 = (new Edge<>(6, v4, v6)).listAddEdge(readGraph.listEdge);
        var edge7 = (new Edge<>(7, v4, v5)).listAddEdge(readGraph.listEdge);
        var edge8 = (new Edge<>(8, v3, v4)).listAddEdge(readGraph.listEdge);
        var edge9 = (new Edge<>(9, v8, v1)).listAddEdge(readGraph.listEdge);

        actual.get(v1).put(edge1, 1);
        actual.get(v2).put(edge1, -1);
        actual.get(v2).put(edge2, 2);
        actual.get(v3).put(edge2, -2);
        actual.get(v1).put(edge3, 3);
        actual.get(v7).put(edge3, -3);
        actual.get(v7).put(edge4, 4);
        actual.get(v6).put(edge4, -4);
        actual.get(v2).put(edge5, 5);
        actual.get(v4).put(edge5, -5);
        actual.get(v4).put(edge6, 6);
        actual.get(v6).put(edge6, -6);
        actual.get(v4).put(edge7, 7);
        actual.get(v5).put(edge7, -7);
        actual.get(v3).put(edge8, 8);
        actual.get(v4).put(edge8, -8);
        actual.get(v8).put(edge9, 9);
        actual.get(v1).put(edge9, -9);

        assertEquals(readGraph.get_incidenceMatrix(), actual);
    }

    @Test
    public void testAddEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge1 = new Edge(3, v1, v2);
        var edge2 = new Edge(2, v2, v3);
        var g = new IncidenceMatrix<>(edge1);
        g.addEdge(edge2);

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(edge1, 3);
        actual.get(v2).put(edge1, -3);
        actual.get(v2).put(edge2, 2);
        actual.get(v3).put(edge2, -2);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testAddVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge1 = new Edge(3, v1, v2);
        var g = new IncidenceMatrix<>(edge1);
        g.addVertex(v3);

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(edge1, 3);
        actual.get(v2).put(edge1, -3);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testRemoveEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge = new Edge<>(3, v1, v2);
        var g = new IncidenceMatrix<>(edge);
        var edge2 = new Edge<>(2, v2, v3);
        g.addEdge(edge2);
        g.removeEdge(edge);

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v2).put(edge2, 2);
        actual.get(v3).put(edge2, -2);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testChangeValueEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge = new Edge<>(3, v1, v2);
        var g = new IncidenceMatrix<>(edge);
        var edge1 = new Edge<>(2, v2, v3);
        g.addEdge(edge1);
        g.changeValueEdge(edge1, 10);

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(edge, 3);
        actual.get(v2).put(edge1, 10);
        actual.get(v2).put(edge, -3);
        actual.get(v3).put(edge1, -10);

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

        var actual = new ArrayList<>();
        actual.add(v1);
        actual.add(v4);
        actual.add(v2);
        actual.add(v3);

        assertEquals(g.shortestPath(v1), actual);
        assertEquals(g.shortestPath(v1).get(0).get_dist(), 0);
        assertEquals(g.shortestPath(v1).get(1).get_dist(), 3);
        assertEquals(g.shortestPath(v1).get(2).get_dist(), 5);
        assertEquals(g.shortestPath(v1).get(3).get_dist(), 8);
    }
}
