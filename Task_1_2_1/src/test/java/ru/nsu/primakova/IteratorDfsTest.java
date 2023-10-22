package ru.nsu.primakova;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IteratorDfsTest {
    @Test
    public void testDfs() {
        var tree = new Tree<>("R1");             //  R1
        var a = tree.addChild("A");              //  | \
        a.addChild("B");                         //  A  R2
        var subtree = new Tree<>("R2");          //  |  | \
        subtree.addChild("C");                   //  B  C  D
        subtree.addChild("D");
        tree.addChild(subtree);

        StringBuilder str = new StringBuilder();
        var dfs = new IteratorDfs<>(tree);
        while (dfs.hasNext()) {
            str.append(dfs.next());
            str.append("  ");
        }

        var actual = "R1  R2  D  C  A  B  ";
        assertEquals(actual, str.toString());
    }
}
