package ru.nsu.primakova;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PolynomialTest{
    @Test
    public void plusTest(){
        Polynomial p1 = new Polynomial(new int[] {2, 3, -1});
        Polynomial p2 = new Polynomial(new int[] {4, -3, 3, 5});
        assertArrayEquals(p1.plus(p2).args, new int[] {6, 0, 2, 5});
    }
    @Test
    public void minusTest(){
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6});
        Polynomial p2 = new Polynomial(new int[] {3, -2, 6, 3});
        assertArrayEquals(p1.minus(p2).args, new int[] {1, 5, 0, -3});
    }
    @Test
    public void timesTest(){
        Polynomial p1 = new Polynomial(new int[] {6, 1, 2, -3});
        Polynomial p2 = new Polynomial(new int[] {1, 0, 3});
        assertArrayEquals(p1.times(p2).args, new int[] {6, 1, 20, 0, 6, -9});
    }
    @Test
    public void evaluateTest(){
        Polynomial p = new Polynomial(new int[] {3, 5, 2, -4, 0, 2});
        assertEquals(p.evaluate(2), 53);
    }
    @Test
    public void differentiateTest(){
        Polynomial p1 = new Polynomial(new int[] {5, 3, 7, 2, -3});
        assertArrayEquals(p1.differentiate(2).args, new int[] {14, 12, -36});
    }
    @Test
    public void equalsTest1(){
        Polynomial p1 = new Polynomial(new int[] {5, 0, -7, 2});
        Polynomial p2 = new Polynomial(new int[] {5, 0, -7, 2});
        assertEquals(p1.equals(p2), true);
    }
    @Test
    public void equalsTest2(){
        Polynomial p1 = new Polynomial(new int[] {5, 0, -7, 2});
        Polynomial p2 = new Polynomial(new int[] {5, 0, 7, 2});
        assertEquals(p1.equals(p2), false);
    }
    @Test
    public void toStringTest1(){
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {3, 2, 8});
        assertEquals(p1.plus(p2.differentiate(1)).toString(), "7x^3 + 6x^2 + 19x + 6");
    }
    @Test
    public void toStringTest2(){
        Polynomial p1 = new Polynomial(new int[] {-5, -1, 3, 1, 0, -10});
        assertEquals(p1.toString(), "-10x^5 + x^3 + 3x^2 - x - 5");
    }
    @Test
    public void polynomialTest(){
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {3, 2, 8});
        assertEquals(p1.times(p2).evaluate(2), 3510);
    }
}
