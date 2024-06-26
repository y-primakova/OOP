package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class TestBarriers.
 */
public class TestBarriers {
    @Test
    public void test1() {
        var barrier = new Barriers(2, 2);

        assertEquals(barrier.getLength(), 0);
        assertFalse(barrier.get(0, 0));
        assertFalse(barrier.get(0, 1));
        assertFalse(barrier.get(1, 0));
        assertFalse(barrier.get(1, 1));
    }

    @Test
    public void test2() {
        var barrier = new Barriers(3, 2);
        barrier.set(0, 1);
        barrier.set(2, 0);
        barrier.set(2, 1);
        assertEquals(barrier.getLength(), 3);
        assertFalse(barrier.get(0, 0));
        assertTrue(barrier.get(0, 1));
        assertFalse(barrier.get(1, 0));
        assertFalse(barrier.get(1, 1));
        assertTrue(barrier.get(2, 0));
        assertTrue(barrier.get(2, 1));
    }
}
