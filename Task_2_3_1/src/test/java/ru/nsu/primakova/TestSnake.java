package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class TestSnake.
 */
public class TestSnake {
    @Test
    public void test1() {
        var b = new Barrier(2, 2);
        b.set(0, 0);
        b.set(1, 0);
        var apple = new Apple(1, 2, 2, b.get());
        var snake = new Snake(2, 2, apple, b);

        assertEquals(snake.getLength(), 1);
        assertEquals(snake.getSnake(0, 0) , -1);
        assertEquals(snake.getSnake(1, 0) , -1);
        if (apple.getApple(0, 1)) {
            assertEquals(snake.getSnake(0, 1) , 0);
            assertEquals(snake.getSnake(1, 1) , 1);
        } else {
            assertEquals(snake.getSnake(0, 1) , 1);
            assertEquals(snake.getSnake(1, 1) , 0);
        }
        assertFalse(snake.isEnd());
    }

    @Test
    public void test2() {
        var b = new Barrier(2, 2);
        b.set(0, 0);
        b.set(1, 0);
        var apple = new Apple(1, 2, 2, b.get());
        var snake = new Snake(2, 2, apple, b);
        snake.changeSnake();

        assertEquals(snake.getLength(), 1);
        assertEquals(snake.getSnake(0, 0) , -1);
        assertEquals(snake.getSnake(1, 0) , -1);
        if (apple.getApple(0, 1)) {
            assertEquals(snake.getSnake(0, 1) , 0);
            assertEquals(snake.getSnake(1, 1) , 1);
        } else {
            assertEquals(snake.getSnake(0, 1) , 1);
            assertEquals(snake.getSnake(1, 1) , 0);
        }
        assertTrue(snake.isEnd());
    }

    @Test
    public void test3() {
        var b = new Barrier(2, 2);
        b.set(0, 0);
        b.set(1, 0);
        var apple = new Apple(1, 2, 2, b.get());
        var snake = new Snake(2, 2, apple, b);
        snake.setCond("LEFT");
        snake.changeSnake();

        assertEquals(snake.getLength(), 2);
        assertFalse(snake.isEnd());
    }
}
