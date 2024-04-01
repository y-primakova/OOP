package ru.nsu.primakova;

import static java.lang.Double.min;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    public Painter(GraphicsContext gc, double w, double h, int nColumns, int nRows, int winLength) {
        this.gc = gc;
        this.size = min(w / nColumns, h / nRows);
        this.startW = (w - size * nColumns) / 2;
        this.startH = (h - size * nRows) / 2;
        this.width = w;
        this.height = h;
        this.columns = nColumns;
        this.rows = nRows;
        this.winLength = winLength;
    }

    public void paint(Snake snake, Apple apple) throws InterruptedException {
        if (snake.isEnd() || snake.getLength() == winLength) {
            end(snake, apple);
        } else {
            paintField();
            paintSnake(snake);
            paintApple(apple);
        }
    }

    private void paintField() {
        gc.setFill(fieldColor);
        gc.fillRect(startW, startH, size * columns, size * rows);
    }

    private void paintSnake(Snake snake) {
        gc.setFill(snakeColor);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (snake.getSnake(i, j) > 0) {
                    if (snake.getSnake(i, j) == 1) {
                        gc.setFill(headColor);
                    } else {
                        gc.setFill(snakeColor);
                    }
                    gc.fillRect(startW + i * size, startH + j * size, size, size);
                } else if (snake.getSnake(i, j) == -1) {
                    gc.setFill(barrierColor);
                    gc.fillRect(startW + i * size, startH + j * size, size, size);
                }
            }
        }
    }

    private void paintApple(Apple apple) {
        gc.setFill(appleColor);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (apple.getApple(i, j)) {
                    gc.fillOval(startW + i * size, startH + j * size, size, size);
                }
            }
        }
    }

    private void end(Snake snake, Apple apple) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            paintField();
            Thread.sleep(500);
            paintApple(apple);
            paintSnake(snake);
            Thread.sleep(500);
        }
        gc.setFill(fieldColor);
        gc.fillRect(0, 0, width, height);
        if (snake.isEnd()) {
            paintEnd();
        } else {
            paintWin();
        }
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
