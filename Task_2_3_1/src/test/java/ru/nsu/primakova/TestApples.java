package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Class TestApples.
 */
public class TestApples {
    @Test
    public void test1() {
        var apple = new Apples(6, 3, 2, new boolean[3][2]);

        assertTrue(apple.getApple(0, 0));
        assertTrue(apple.getApple(0, 1));
        assertTrue(apple.getApple(1, 0));
        assertTrue(apple.getApple(1, 1));
        assertTrue(apple.getApple(2, 0));
        assertTrue(apple.getApple(2, 1));
        assertEquals(apple.getNumApple(), 6);
    }

    @Test
    public void test2() {
        var b = new boolean[2][3];
        b[0][1] = true;
        b[1][1] = true;
        b[1][2] = true;
        var apple = new Apples(3, 2, 3, b);

        assertTrue(apple.getApple(0, 0));
        assertFalse(apple.getApple(0, 1));
        assertTrue(apple.getApple(0, 2));
        assertTrue(apple.getApple(1, 0));
        assertFalse(apple.getApple(1, 1));
        assertFalse(apple.getApple(1, 2));
        assertEquals(apple.getNumApple(), 3);
    }

    @Test
    public void test3() {
        var apple = new Apples(8, 3, 3, new boolean[3][3]);
        int[] h = {1, 0};
        ArrayList<ArrayList<Integer>> s = new ArrayList<>();
        apple.createApple(s, h);

        assertTrue(apple.getApple(0, 0));
        assertTrue(apple.getApple(0, 1));
        assertTrue(apple.getApple(0, 2));
        assertFalse(apple.getApple(1, 0));
        assertTrue(apple.getApple(1, 1));
        assertTrue(apple.getApple(1, 2));
        assertTrue(apple.getApple(2, 0));
        assertTrue(apple.getApple(2, 1));
        assertTrue(apple.getApple(2, 2));
        assertEquals(apple.getNumApple(), 8);
    }

    @Test
    public void test4() {
        var apple = new Apples(8, 3, 3, new boolean[3][3]);
        ArrayList<ArrayList<Integer>> s = new ArrayList<>();
        apple.createApple(s);

        assertTrue(apple.getApple(0, 0));
        assertTrue(apple.getApple(0, 1));
        assertTrue(apple.getApple(0, 2));
        assertTrue(apple.getApple(1, 0));
        assertTrue(apple.getApple(1, 1));
        assertTrue(apple.getApple(1, 2));
        assertTrue(apple.getApple(2, 0));
        assertTrue(apple.getApple(2, 1));
        assertTrue(apple.getApple(2, 2));
        assertEquals(apple.getNumApple(), 8);
    }
}
