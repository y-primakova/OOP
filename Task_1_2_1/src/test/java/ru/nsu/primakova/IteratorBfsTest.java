package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class IteratorBfsTest.
 */
public class IteratorBfsTest {
    @Test
    public void testBfs() {
        var tree = new Tree<>("R1");             //  R1
        var a = tree.addChild("A");              //  | \
        a.addChild("B");                         //  A  R2
        var subtree = new Tree<>("R2");          //  |  | \
        subtree.addChild("C");                   //  B  C  D
        subtree.addChild("D");
        tree.addChild(subtree);

        var actual = "R1  A  R2  B  C  D  ";
        assertEquals(actual, tree.toString());
    }
}