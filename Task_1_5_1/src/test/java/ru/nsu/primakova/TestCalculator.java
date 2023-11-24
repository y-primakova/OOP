package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.primakova.Calculator.calculator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * Class TestCalculator.
 */
public class TestCalculator {
    @Test
    public void testPlus() throws IncorrectInputException {
        String input = "+ -7.44 5.2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(-2.24, calculator());
    }

    @Test
    public void testMinus() throws IncorrectInputException {
        String input = "- 4.8 1.29";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(3.51, calculator());
    }

    @Test
    public void testMultiply() throws IncorrectInputException {
        String input = "* 4 -5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(-20, calculator());
    }

    @Test
    public void testDiv() throws IncorrectInputException {
        String input = "/ 6.48 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(3.24, calculator());
    }

    @Test
    public void testPow() throws IncorrectInputException {
        String input = "pow 3 3.0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(27, calculator());
    }

    @Test
    public void testLog() throws IncorrectInputException {
        String input = "log 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(0, calculator());
    }

    @Test
    public void testSqrt() throws IncorrectInputException {
        String input = "sqrt 1.44";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(1.2, calculator());
    }

    @Test
    public void testSin() throws IncorrectInputException {
        String input = "sin 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(0, calculator());
    }

    @Test
    public void testCos() throws IncorrectInputException {
        String input = "cos -180";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(-1, calculator());
    }

    @Test
    public void test1() throws IncorrectInputException {
        String input = "sin + - 1 2 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(0, calculator());
    }

    @Test
    public void test2() throws IncorrectInputException {
        String input = "cos sin sin sin sin sin sin sin sin sin 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(1, calculator());
    }

    @Test
    public void test3() throws IncorrectInputException {
        String input = "- + sqrt / 100 4 50.34 5.35";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(49.99, calculator());
    }

    @Test
    public void test4() throws IncorrectInputException {
        String input = "+ sqrt sqrt * * sqrt cos 360 3.2 5 9.8";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(11.8, calculator());
    }
}
