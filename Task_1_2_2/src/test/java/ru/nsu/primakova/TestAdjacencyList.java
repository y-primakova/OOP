package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

/**
 * Class TestAdjacencyList.
 */
public class TestAdjacencyList {

    @Test
    public void testRead() {
        var readGraph = new AdjacencyList<String>(new ArrayList<>(), new ArrayList<>());
        readGraph = readGraph.read("src/test/resources/graph1.txt");
        var v1 = (new Vertex<>("v1")).listAddVertex(readGraph.listVertex);
        var v2 = (new Vertex<>("v2")).listAddVertex(readGraph.listVertex);
        var v3 = (new Vertex<>("v3")).listAddVertex(readGraph.listVertex);
        var v4 = (new Vertex<>("v4")).listAddVertex(readGraph.listVertex);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.put(v4, new HashMap<>());
        actual.get(v1).put(v2, 1);
        actual.get(v2).put(v3, 2);
        actual.get(v2).put(v4, 3);
        actual.get(v3).put(v4, 4);
        actual.get(v2).put(v1, 5);

        assertEquals(readGraph.get_adjacencyList(), actual);
    }

    @Test
    public void testAddEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyList<>(3, v1, v2);
        g.addEdge(new Edge<>(2, v2, v3));

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);
        actual.get(v2).put(v3, 2);

        assertEquals(g.get_adjacencyList(), actual);
    }

    @Test
    public void testAddVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyList<>(3, v1, v2);
        g.addVertex(v3);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);

        assertEquals(g.get_adjacencyList(), actual);
    }

    @Test
    public void testRemoveEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge = new Edge<>(3, v1, v2);
        var g = new AdjacencyList<>(edge);
        g.addEdge(new Edge<>(2, v2, v3));
        g.removeEdge(edge);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v2).put(v3, 2);

        assertEquals(g.get_adjacencyList(), actual);
    }

    @Test
    public void testRemoveVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyList<>(3, v1, v2);
        g.addEdge(new Edge<>(2, v2, v3));
        g.removeVertex(v2);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v3, new HashMap<>());

        assertEquals(g.get_adjacencyList(), actual);
    }

    @Test
    public void testChangeValueEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var g = new AdjacencyList<>(3, v1, v2);
        var edge = new Edge<>(2, v2, v3);
        g.addEdge(edge);
        g.changeValueEdge(edge, 10);

        HashMap<Vertex<String>, HashMap<Vertex<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(v2, 3);
        actual.get(v2).put(v3, 10);

        assertEquals(g.get_adjacencyList(), actual);
    }

    @Test
    public void testShortestPath() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var v4 = new Vertex<>("v4");
        var v5 = new Vertex<>("v5");
        var g = new AdjacencyList<>(7, v1, v2);
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

    @Test
    public void testShortestPathString() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var v4 = new Vertex<>("v4");
        var v5 = new Vertex<>("v5");
        var g = new AdjacencyList<>(7, v1, v2);
        g.addEdge(new Edge<>(3, v1, v4));
        g.addEdge(new Edge<>(2, v4, v2));
        g.addEdge(new Edge<>(5, v4, v3));
        g.addEdge(new Edge<>(10, v5, v1));

        var str = "v1 0\nv4 3\nv2 5\nv3 8\n";

        assertEquals(g.shortestPathString(v1), str);
    }
}
