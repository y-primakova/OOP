package ru.nsu.primakova;

import static java.lang.Double.min;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class Painter.
 */
public class Painter {
    private final GraphicsContext gc;
    private final double startW;
    private final double startH;
    private final double width;
    private final double height;
    private final double size;
    private final int columns;
    private final int rows;
    private final int winLength;
    private final Color fieldColor = new Color(0.1, 0.1, 0.1, 1);
    private final Color barrierColor = new Color(0, 0.2, 0, 1);
    private final Color headColor = new Color(0.1, 0.1, 0.9, 1);
    private final Color snakeColor = new Color(0.1, 0.1, 0.5, 1);
    private final Color appleColor = new Color(0.9, 0.1, 0.1, 1);
    private final Color endColor = new Color(0.7, 0.7, 0.7, 1);
    private final Color winColor = new Color(0.7, 0.7, 0, 1);

    public Painter(GraphicsContext gc, double width, double height, int columns, int rows, int winLength) {
        this.gc = gc;
        this.size = min(width / columns, height / rows);
        this.startW = (width - size * columns) / 2;
        this.startH = (height - size * rows) / 2;
        this.width = width;
        this.height = height;
        this.columns = columns;
        this.rows = rows;
        this.winLength = winLength;
    }

    public void paint(Snake snake, Apples apples, Barriers barriers) throws InterruptedException {
        if (snake.isEnd() || snake.getLength() == winLength) {
            var x = new int[1];
            end(snake, apples, barriers, false, x, x);
        } else {
            paintField();
            paintSnake(snake);
            paintBarriers(barriers);
            paintApple(apples);
        }
    }

    private void paintField() {
        gc.setFill(fieldColor);
        gc.fillRect(startW, startH, size * columns, size * rows);
    }

    private void paintSnake(Snake snake) {
        var s = snake.getSnake();
        for (int i = 0; i < s.size(); i++) {
            if (i == 0) {
                gc.setFill(headColor);
            } else {
                gc.setFill(snakeColor);
            }
            gc.fillRect(startW + s.get(i).get(0) * size, startH + s.get(i).get(1) * size, size, size);
        }
    }

    private void paintBarriers(Barriers barriers) {
        gc.setFill(barrierColor);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (barriers.get(i, j)) {
                    gc.fillRect(startW + i * size, startH + j * size, size, size);
                }
            }
        }
    }

    private void paintApple(Apples apples) {
        gc.setFill(appleColor);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (apples.getApple(i, j)) {
                    gc.fillOval(startW + i * size, startH + j * size, size, size);
                }
            }
        }
    }

    public void end(Snake snake, Apples apples, Barriers barriers, boolean isBreak, int[] head, int[] tail) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            paintField();
            Thread.sleep(500);
            paintApple(apples);
            paintSnake(snake);
            paintBarriers(barriers);
            if (isBreak) {
                ifBreak(head, tail);
            }
            Thread.sleep(500);
        }
        gc.setFill(fieldColor);
        gc.fillRect(0, 0, width, height);
        if (snake.isEnd() || isBreak) {
            paintEnd();
        } else {
            paintWin();
        }
    }

    private void ifBreak(int[] head, int[] tail) {
        gc.setFill(snakeColor);
        gc.fillRect(startW + tail[0] * size, startH + tail[1] * size, size, size);

        gc.setFill(headColor);
        gc.fillRect(startW + head[0] * size, startH + head[1] * size, size, size);
    }

    private void paintEnd() {
        gc.setFill(endColor);
        int[] x = {0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 4, 4, 4, 5, 6,
                   6, 6, 8, 8, 8, 9, 9, 10, 10, 10, 10, 10};
        int[] y = {0, 1, 2, 3, 4, 0, 2, 4, 0, 4, 2, 3, 4,
                   2, 2, 3, 4, 2, 3, 4, 2, 4, 0, 1, 2, 3, 4};
        double s = min(width, height) / 25;
        var startX = (width - 11 * s) / 2;
        var startY = (height - 5 * s) / 2;
        for (int i = 0; i < 27; i++) {
            gc.fillRect(startX + (double) x[i] * s, startY + (double) y[i] * s, s, s);
        }
    }

    private void paintWin() {
        gc.setFill(winColor);
        int[] x = {0, 0, 0, 0, 1, 2, 3, 4, 4, 4, 4,
                   6, 6, 6, 8, 8, 8, 9, 10, 10, 10};
        int[] y = {0, 1, 2, 3, 4, 3, 4, 0, 1, 2,
                   3, 2, 3, 4, 2, 3, 4, 2, 2, 3, 4};
        double s = min(width, height) / 25;
        var startX = (width - 11 * s) / 2;
        var startY = (height - 5 * s) / 2;
        for (int i = 0; i < 21; i++) {
            gc.fillRect(startX + (double) x[i] * s, startY + (double) y[i] * s, s, s);
        }
    }
}
