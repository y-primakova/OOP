package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Class TreeTest.
 */
public class TreeTest {
    private Tree<String> tree1(String x, String r) {
        try {
            var tree = new Tree<>("R1");             //  R1
            var a = tree.addChild("A");              //  | \
            var b = a.addChild("B");                 //  A  R2
            var subtree = new Tree<>("R2");          //  |  | \
            var c = subtree.addChild("C");           //  B  C  D
            var d = subtree.addChild("D");
            tree.addChild(subtree);

            if (Objects.equals(r, "remove_B")) {
                b.remove();
            }
            if (Objects.equals(r, "remove_subtree")) {
                subtree.remove();
            }

            if (Objects.equals(x, "R1")) {
                return tree;
            }
            if (Objects.equals(x, "A")) {
                return a;
            }
            if (Objects.equals(x, "B")) {
                return b;
            }
            if (Objects.equals(x, "R2")) {
                return subtree;
            }
            if (Objects.equals(x, "C")) {
                return c;
            }
            if (Objects.equals(x, "D")) {
                return d;
            }
        } catch (NullNodeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Test
    public void testR1() {
        var tree = tree1("R1", null);
        var a = tree1("A", null);
        var subtree = tree1("R2", null);

        var children = new ArrayList<>();
        children.add(a);
        children.add(subtree);

        assertEquals("R1", tree.get_value());
        assertEquals(children, tree.get_children());
        assertNull(tree.get_parent());
    }

    @Test
    public void testA() {
        var tree = tree1("R1", null);
        var a = tree1("A", null);
        var b = tree1("B", null);

        var children = new ArrayList<>();
        children.add(b);

        assertEquals("A", a.get_value());
        assertEquals(children, a.get_children());
        assertEquals(tree, a.get_parent());
    }

    @Test
    public void testB() {
        var a = tree1("A", null);
        var b = tree1("B", null);

        assertEquals("B", b.get_value());
        assertEquals(new ArrayList<>(), b.get_children());
        assertEquals(a, b.get_parent());
    }

    @Test
    public void testR2() {
        var tree = tree1("R1", null);
        var subtree = tree1("R2", null);
        var c = tree1("C", null);
        var d = tree1("D", null);

        var children = new ArrayList<>();
        children.add(c);
        children.add(d);

        assertEquals("R2", subtree.get_value());
        assertEquals(children, subtree.get_children());
        assertEquals(tree, subtree.get_parent());
    }

    @Test
    public void testC() {
        var subtree = tree1("R2", null);
        var c = tree1("C", null);

        assertEquals("C", c.get_value());
        assertEquals(new ArrayList<>(), c.get_children());
        assertEquals(subtree, c.get_parent());
    }

    @Test
    public void testD() {
        var subtree = tree1("R2", null);
        var d = tree1("D", null);

        assertEquals("D", d.get_value());
        assertEquals(new ArrayList<>(), d.get_children());
        assertEquals(subtree, d.get_parent());
    }

    @Test
    public void testRemoveB() {
        var tree = tree1("R1", "remove_B");
        var a = tree1("A", "remove_B");
        var b = tree1("B", "remove_B");

        assertEquals("A", a.get_value());
        assertEquals(new ArrayList<>(), a.get_children());
        assertEquals(tree, a.get_parent());
        assertEquals("B", b.get_value());
        assertEquals(new ArrayList<>(), b.get_children());
        assertNull(b.get_parent());
    }

    @Test
    public void testRemoveSubtree() {
        var tree = tree1("R1", "remove_subtree");
        var a = tree1("A", "remove_subtree");
        var subtree = tree1("R2", "remove_subtree");
        var c = tree1("C", "remove_subtree");
        var d = tree1("D", "remove_subtree");

        var childrenTree = new ArrayList<>();
        childrenTree.add(a);
        var childrenSubtree = new ArrayList<>();
        childrenSubtree.add(c);
        childrenSubtree.add(d);

        assertEquals("R1", tree.get_value());
        assertEquals(childrenTree, tree.get_children());
        assertNull(tree.get_parent());
        assertEquals("R2", subtree.get_value());
        assertEquals(childrenSubtree, subtree.get_children());
        assertNull(subtree.get_parent());
    }

    @Test
    public void testEquals1() {
        var tree1 = tree1("R1", null);
        var tree2 = tree1("R1", null);
        assertTrue(tree1.equals(tree2));
    }

    @Test
    public void testEquals2() {
        var tree1 = tree1("R1", null);
        var tree2 = tree1("R1", "remove_B");
        assertFalse(tree1.equals(tree2));
    }

    @Test
    public void testNullNodeException() {
        var tree = tree1("R1", null);
        boolean a = false;
        try {
            tree.addChild((String) null);
        } catch (NullNodeException e) {
            a = true;
        }
        assertTrue(a);
    }
}