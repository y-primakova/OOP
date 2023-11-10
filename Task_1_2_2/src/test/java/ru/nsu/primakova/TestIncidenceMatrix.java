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

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.put(v4, new HashMap<>());
        var edge1 = (new Edge<>(1, v1, v2)).listAddEdge(readGraph.listEdge);
        var edge2 = (new Edge<>(2, v2, v3)).listAddEdge(readGraph.listEdge);
        var edge3 = (new Edge<>(3, v2, v4)).listAddEdge(readGraph.listEdge);
        var edge4 = (new Edge<>(4, v3, v4)).listAddEdge(readGraph.listEdge);
        var edge5 = (new Edge<>(5, v2, v1)).listAddEdge(readGraph.listEdge);
        actual.get(v1).put(edge1, 1);
        actual.get(v2).put(edge1, -1);
        actual.get(v2).put(edge2, 2);
        actual.get(v3).put(edge2, -2);
        actual.get(v2).put(edge3, 3);
        actual.get(v4).put(edge3, -3);
        actual.get(v3).put(edge4, 4);
        actual.get(v4).put(edge4, -4);
        actual.get(v2).put(edge5, 5);
        actual.get(v1).put(edge5, -5);

        actual.get(v1).put(edge2, 0);
        actual.get(v1).put(edge3, 0);
        actual.get(v1).put(edge4, 0);
        actual.get(v2).put(edge4, 0);
        actual.get(v3).put(edge1, 0);
        actual.get(v3).put(edge3, 0);
        actual.get(v3).put(edge5, 0);
        actual.get(v4).put(edge1, 0);
        actual.get(v4).put(edge2, 0);
        actual.get(v4).put(edge5, 0);

        assertEquals(readGraph.get_incidenceMatrix(), actual);
    }

    @Test
    public void testAddEdge() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge1 = new Edge<>(3, v1, v2);
        var edge2 = new Edge<>(2, v2, v3);
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
        actual.get(v1).put(edge2, 0);
        actual.get(v3).put(edge1, 0);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testAddVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge1 = new Edge<>(3, v1, v2);
        var g = new IncidenceMatrix<>(edge1);
        g.addVertex(v3);

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v1).put(edge1, 3);
        actual.get(v2).put(edge1, -3);
        actual.get(v3).put(edge1, 0);

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
        actual.put(v1, new HashMap<>());
        actual.put(v2, new HashMap<>());
        actual.put(v3, new HashMap<>());
        actual.get(v2).put(edge2, 2);
        actual.get(v3).put(edge2, -2);
        actual.get(v1).put(edge2, 0);

        assertEquals(g.get_incidenceMatrix(), actual);
    }

    @Test
    public void testRemoveVertex() {
        var v1 = new Vertex<>("v1");
        var v2 = new Vertex<>("v2");
        var v3 = new Vertex<>("v3");
        var edge = new Edge<>(3, v1, v2);
        var g = new IncidenceMatrix<>(edge);
        var edge2 = new Edge<>(2, v2, v3);
        g.addEdge(edge2);
        g.removeVertex(v2);

        HashMap<Vertex<String>, HashMap<Edge<String>, Integer>> actual = new HashMap<>();
        actual.put(v1, new HashMap<>());
        actual.put(v3, new HashMap<>());

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
        actual.get(v1).put(edge1, 0);
        actual.get(v3).put(edge, 0);

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

    @Test
    public void testShortestPathString() {
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

        var str = "v1 0\nv4 3\nv2 5\nv3 8\n";

        assertEquals(g.shortestPathString(v1), str);
    }
}
