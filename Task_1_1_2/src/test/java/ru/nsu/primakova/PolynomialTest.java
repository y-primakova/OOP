package ru.nsu.primakova;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PolynomialTest {

    @Test
    public void plusTest() {
        var p1 = new Polynomial(new int[] {2, 3, -1});
        var p2 = new Polynomial(new int[] {4, -3, 3, 5});
        var actual = new Polynomial(new int[] {6, 0, 2, 5});
        assertEquals(p1.plus(p2), actual);
    }

    @Test
    public void minusTest() {
        var p1 = new Polynomial(new int[] {4, 3, 6});
        var p2 = new Polynomial(new int[] {3, -2, 6, 3});
        var actual = new Polynomial(new int[] {1, 5, 0, -3});
        assertEquals(p1.minus(p2), actual);
    }

    @Test
    public void timesTest() {
        var p1 = new Polynomial(new int[] {6, 1, 2, -3});
        var p2 = new Polynomial(new int[] {1, 0, 3});
        var actual = new Polynomial(new int[] {6, 1, 20, 0, 6, -9});
        assertEquals(p1.times(p2), actual);
    }

    @Test
    public void evaluateTest() {
        var p = new Polynomial(new int[] {3, 5, 2, -4, 0, 2});
        assertEquals(p.evaluate(2), 53);
    }

    @Test
    public void differentiateTest() {
        var p1 = new Polynomial(new int[] {5, 3, 7, 2, -3});
        var actual = new Polynomial(new int[] {14, 12, -36});
        assertEquals(p1.differentiate(2), actual);
    }

    @Test
    public void equalsTest1() {
        var p1 = new Polynomial(new int[] {5, 0, -7, 2});
        var p2 = new Polynomial(new int[] {5, 0, -7, 2});
        assertEquals(p1.equals(p2), true);
    }

    @Test
    public void equalsTest2() {
        var p1 = new Polynomial(new int[] {5, 0, -7, 2});
        var p2 = new Polynomial(new int[] {5, 0, 7, 2});
        assertEquals(p1.equals(p2), false);
    }

    @Test
    public void toStringTest1() {
        var p1 = new Polynomial(new int[] {4, 3, 6, 7});
        var p2 = new Polynomial(new int[] {3, 2, 8});
        assertEquals(p1.plus(p2.differentiate(1)).toString(), "7x^3 + 6x^2 + 19x + 6");
    }

    @Test
    public void toStringTest2() {
        var p1 = new Polynomial(new int[] {-5, -1, 3, 1, 0, -10});
        assertEquals(p1.toString(), "-10x^5 + x^3 + 3x^2 - x - 5");
    }

    @Test
    public void polynomialTest() {
        var p1 = new Polynomial(new int[] {4, 3, 6, 7});
        var p2 = new Polynomial(new int[] {3, 2, 8});
        assertEquals(p1.times(p2).evaluate(2), 3510);
    }
}
