package ru.nsu.primakova;

/**
 * Class Main.
 */
public class Main {
    /**
     *
     */
    public static void main(String[] args) {
        var tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        var subtree = new Tree<>("R2");
        var c = subtree.addChild("C");
        var d = subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        System.out.println(tree.toString());
    }
}