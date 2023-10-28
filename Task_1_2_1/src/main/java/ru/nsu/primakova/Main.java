package ru.nsu.primakova;

/**
 * Class Main.
 */
public class Main {

    /**
     * main.
     */
    public static void main(String[] args) {
        var tree = new Tree<>("R1");
        var subtree = new Tree<>("R2");
        try {
            var a = tree.addChild((String) null);
            a.addChild("B");
        } catch (NullNodeException e) {
            System.out.println("Error(a,b): " + e.getMessage());
        }
        try {
            subtree.addChild("C");
            subtree.addChild("D");
        } catch (NullNodeException e) {
            System.out.println("Error(c,d): " + e.getMessage());
        }
        try {
            tree.addChild(subtree);
        } catch (NullNodeException e) {
            System.out.println("Error(subtree): " + e.getMessage());
        }
        System.out.println(tree.toString());
    }
}

