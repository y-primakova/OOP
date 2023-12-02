package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.primakova.Calculator.calculator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Class TestCalculator.
 */
public class TestCalculator {

    @Test
    public void testOneNumber() {
        String input = "-4.567";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = -4.567\n", out.toString());
    }

    @Test
    public void testSplit() {
        String input = "+       8  -1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 7.0\n", out.toString());
    }

    @Test
    public void testPlus() {
        String input = "+ -7.44 5.2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = -2.24\n", out.toString());
    }

    @Test
    public void testMinus() {
        String input = "- 4.8 1.29";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 3.51\n", out.toString());
    }

    @Test
    public void testMultiply() {
        String input = "* 4 -5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = -20.0\n", out.toString());
    }

    @Test
    public void testDiv() {
        String input = "/ 6.48 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 3.24\n", out.toString());
    }

    @Test
    public void testPow() {
        String input = "pow 3 3.0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 27.0\n", out.toString());
    }

    @Test
    public void testLog() {
        String input = "log 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 0.0\n", out.toString());
    }

    @Test
    public void testSqrt() {
        String input = "sqrt 1.44";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 1.2\n", out.toString());
    }

    @Test
    public void testSin() {
        String input = "sin 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 0.0\n", out.toString());
    }

    @Test
    public void testCos() {
        String input = "cos -180";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = -1.0\n", out.toString());
    }

    @Test
    public void test1() {
        String input = "sin + - 1 2 1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 0.0\n", out.toString());
    }

    @Test
    public void test2() {
        String input = "cos sin sin sin sin sin sin sin sin sin 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 1.0\n", out.toString());
    }

    @Test
    public void test3() {
        String input = "- + sqrt / 100 4 50.34 5.35";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 49.99\n", out.toString());
    }

    @Test
    public void test4() {
        String input = "+ sqrt sqrt * * sqrt cos 360 3.2 5 9.8";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        calculator();
        assertEquals(" = 11.8\n", out.toString());
    }
}
