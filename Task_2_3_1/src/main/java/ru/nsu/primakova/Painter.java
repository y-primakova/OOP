package ru.nsu.primakova;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.System.currentTimeMillis;

/**
 * Class Painter.
 */
public class Painter {
    private static int size = 20;
    private static int w = 50;
    private static int h = 50;
    private final static Color fieldColor = new Color(0.1, 0.1, 0.1, 1);
    private final static Color headColor = new Color(0.1, 0.1, 0.9, 1);
    private final static Color snakeColor = new Color(0.1, 0.1, 0.5, 1);
    private final static Color appleColor = new Color(0.9, 0.1, 0.1, 1);
    private final static Color endColor = new Color(0.7, 0.7, 0.7, 1);

    public static void paint(GraphicsContext gc, Snake snake, int[] apple) throws InterruptedException {
        if (snake.isEnd()) {
            end(gc, snake, apple);
        } else {
            paintField(gc);
            paintSnake(gc, snake);
            paintApple(gc, apple);
        }
    }

    private static void paintField(GraphicsContext gc) {
        gc.setFill(fieldColor);
        gc.fillRect(0, 0, size * w, size * h);
    }

    private static void paintSnake(GraphicsContext gc, Snake snake) {
        gc.setFill(snakeColor);
        for (int i = 0; i < 25; i++) {//n
            for (int j = 0; j < 25; j++) {//n
                if (snake.getSnake(i, j) > 0) {
                    if (snake.getSnake(i, j) == 1) {
                        gc.setFill(headColor);
                    } else {
                        gc.setFill(snakeColor);
                    }
                    gc.fillRect(i * size, j * size, size, size);
                }
            }
        }
    }

    private static void paintApple(GraphicsContext gc, int[] apple) {
        gc.setFill(appleColor);
        gc.fillRect(apple[0] * size, apple[1] * size, size, size);
    }

    private static void end(GraphicsContext gc, Snake snake, int[] apple) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            paintField(gc);
            Thread.sleep(500);
            paintSnake(gc, snake);
            paintApple(gc, apple);
            Thread.sleep(500);
        }
        paintField(gc);
        paintEnd(gc);
    }

    private static void paintEnd(GraphicsContext gc) {
        gc.setFill(endColor);
        int[] x = {7,7,7,7,7,8,8,8,9,9,11,11,11,12,13,13,13,15,15,15,16,16,17,17,17,17,17};
        int[] y = {10,11,12,13,14,10,12,14,10,14,12,13,14,12,12,13,14,12,13,14,12,14,10,11,12,13,14};
        for (int i = 0; i < 27; i++) {
            gc.fillRect(x[i] * size, y[i] * size, size, size);
        }
    }
}
