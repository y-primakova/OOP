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
        var b = new Barriers(2, 2);
        b.set(0, 0);
        b.set(1, 0);
        var apple = new Apples(1, 2, 2, b.get());
        var snake = new Snake(2, 2, apple, b);

        assertEquals(snake.getLength(), 1);
        assertEquals(snake.getSnake().get(0).get(1), 1);
        if (apple.getApple(0, 1)) {
            assertEquals(snake.getSnake().get(0).get(0), 1);
        } else {
            assertEquals(snake.getSnake().get(0).get(0), 0);
        }
        assertFalse(snake.isEnd());
    }

    @Test
    public void test2() {
        var b = new Barriers(1, 4);
        var apple = new Apples(0, 1, 4, b.get());
        var snake = new Snake(1, 4, apple, b);
        var y = snake.getSnake().get(0).get(1);

        snake.changeSnake();
        assertEquals(snake.getLength(), 1);
        if (y - 1 < 0) {
            assertEquals(snake.getSnake().get(0).get(1), 3);
        } else {
            assertEquals(snake.getSnake().get(0).get(1), y - 1);
        }
        assertEquals(snake.getSnake().get(0).get(0), 0);
        assertFalse(snake.isEnd());

        snake.setCond("DOWN");
        snake.changeSnake();
        assertEquals(snake.getLength(), 1);
        assertEquals(snake.getSnake().get(0).get(0), 0);
        if (y - 2 < 0) {
            assertEquals(snake.getSnake().get(0).get(1), 3);
        } else {
            assertEquals(snake.getSnake().get(0).get(1), y - 2);
        }
        assertFalse(snake.isEnd());

        snake.setCond("RIGHT");
        snake.changeSnake();
        assertEquals(snake.getLength(), 1);
        assertEquals(snake.getSnake().get(0).get(0), 0);
        if (y - 2 < 0) {
            assertEquals(snake.getSnake().get(0).get(1), 3);
        } else {
            assertEquals(snake.getSnake().get(0).get(1), y - 2);
        }
        assertTrue(snake.isEnd());
    }

    @Test
    public void test3() {
        var b = new Barriers(2, 2);
        b.set(0, 0);
        b.set(1, 0);
        var apple = new Apples(1, 2, 2, b.get());
        var snake = new Snake(2, 2, apple, b);
        snake.setCond("LEFT");
        snake.changeSnake();

        assertEquals(snake.getLength(), 2);
        assertFalse(snake.isEnd());
    }
}
