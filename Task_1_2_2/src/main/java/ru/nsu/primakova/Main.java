package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Class Main.
 */
public class Main {

    /**
     * main.
     */
    public static void main(String[] args) {
        var graph = new AdjacencyMatrix<String>(new ArrayList<>(), new ArrayList<>());
        graph = graph.read("src/main/resources/graph1.txt");
        System.out.println(graph);
    }
}