package ru.nsu.primakova;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Objects;

public class TreeTest {
    private Tree<String> tree1(String x, String r){
        var tree = new Tree <>("R1");             //  R1
        var a = tree.addChild("A");               //  | \
        var b = a.addChild("B");                  //  A  R2
        var subtree = new Tree <>("R2");          //  |  | \
        var c = subtree.addChild("C");            //  B  C  D
        var d = subtree.addChild("D");
        tree.addChild(subtree);

        if(Objects.equals(r, "remove_B")) {
            b.remove();
        }
        if(Objects.equals(r, "remove_subtree")) {
            subtree.remove();
        }

        if (Objects.equals(x, "R1")) return tree;
        if (Objects.equals(x, "A")) return a;
        if (Objects.equals(x, "B")) return b;
        if (Objects.equals(x, "R2")) return subtree;
        if (Objects.equals(x, "C")) return c;
        if (Objects.equals(x, "D")) return d;
        return null;
    }
    @Test
    public void TestR1(){
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
    public void TestA(){
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
    public void TestB(){
        var a = tree1("A", null);
        var b = tree1("B", null);

        assertEquals("B", b.get_value());
        assertEquals(new ArrayList<>(), b.get_children());
        assertEquals(a, b.get_parent());
    }
    @Test
    public void TestR2(){
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
    public void TestC(){
        var subtree = tree1("R2", null);
        var c = tree1("C", null);

        assertEquals("C", c.get_value());
        assertEquals(new ArrayList<>(), c.get_children());
        assertEquals(subtree, c.get_parent());
    }
    @Test
    public void TestD(){
        var subtree = tree1("R2", null);
        var d = tree1("D", null);

        assertEquals("D", d.get_value());
        assertEquals(new ArrayList<>(), d.get_children());
        assertEquals(subtree, d.get_parent());
    }
    @Test
    public void TestRemoveB(){
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
    public void TestRemoveSubtree(){
        var tree = tree1("R1", "remove_subtree");
        var a = tree1("A", "remove_subtree");
        var subtree = tree1("R2", "remove_subtree");
        var c = tree1("C", "remove_subtree");
        var d = tree1("D", "remove_subtree");

        var children_tree = new ArrayList<>();
        children_tree.add(a);
        var children_subtree = new ArrayList<>();
        children_subtree.add(c);
        children_subtree.add(d);

        assertEquals("R1", tree.get_value());
        assertEquals(children_tree, tree.get_children());
        assertNull(tree.get_parent());
        assertEquals("R2", subtree.get_value());
        assertEquals(children_subtree, subtree.get_children());
        assertNull(subtree.get_parent());
    }
    @Test
    public void TestEquals1(){
        var tree1 = tree1("R1", null);
        var tree2 = tree1("R1", null);
        assertTrue(tree1.equals(tree2));
    }
    @Test
    public void TestEquals2(){
        var tree1 = tree1("R1", null);
        var tree2 = tree1("R1", "remove_B");
        assertFalse(tree1.equals(tree2));
    }
    @Test
    public void TestBFS(){
        var tree = tree1("R1", null);

        StringBuilder str = new StringBuilder();
        var bfs = new IteratorBFS<>(tree);
        while (bfs.hasNext()) {
            str.append(bfs.next());
            str.append("  ");
        }

        var actual = "R1  A  R2  B  C  D  ";
        assertEquals(actual , str.toString());
    }
    @Test
    public void TestDFS(){
        var tree = tree1("R1", null);

        StringBuilder str = new StringBuilder();
        var dfs = new IteratorDFS<>(tree);
        while (dfs.hasNext()) {
            str.append(dfs.next());
            str.append("  ");
        }

        var actual = "R1  R2  D  C  A  B  ";
        assertEquals(actual , str.toString());
    }
}