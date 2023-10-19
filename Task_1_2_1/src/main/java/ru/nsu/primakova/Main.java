package ru.nsu.primakova;
public class Main {
    public static void main(String[] args) {
        var tree = new Tree <>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        var subtree = new Tree <>("R2");
        var c = subtree.addChild("C");
        var d = subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        StringBuilder str = new StringBuilder();
        var bfs = new IteratorBFS<>(tree);
        while (bfs.hasNext()) {
            str.append(bfs.next());
            str.append("  ");
        }
        System.out.println(str);
    }
}