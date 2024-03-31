package ru.nsu.primakova;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Double.min;


/**
 * Class Painter.
 */
public class Painter {
    private final GraphicsContext gc;
    private final double startW;
    private final double startH;
    private final double w;
    private final double h;
    private final double size;
    private final int nColumns;
    private final int nRows;
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
        this.size = min(w /nColumns, h /nRows);
        this.startW = (w - size * nColumns) / 2;
        this.startH = (h - size * nRows) / 2;
        this.w = w;
        this.h = h;
        this.nColumns = nColumns;
        this.nRows = nRows;
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
        gc.fillRect(startW, startH, size * nColumns, size * nRows);
    }

    private void paintSnake(Snake snake) {
        gc.setFill(snakeColor);
        for (int i = 0; i < nColumns; i++) {
            for (int j = 0; j < nRows; j++) {
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
        for (int i = 0; i < nColumns; i++) {
            for (int j = 0; j < nRows; j++) {
                if (apple.getApple(i, j)) {
                    gc.fillRect(startW + i * size, startH + j * size, size, size);
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
        gc.fillRect(0, 0, w, h);
        if(snake.isEnd()) {
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
        double s = min(w,h) / 25;
        var startX = (w - 11 * s) / 2;
        var startY = (h - 5 * s) / 2;
        for (int i = 0; i < 27; i++) {
            gc.fillRect(startX + (double)x[i] * s, startY + (double)y[i] * s, s, s);
        }
    }

    private void paintWin() {
        gc.setFill(winColor);
        int[] x = {0, 0, 0, 0, 1, 2, 3, 4, 4, 4, 4,
                6, 6, 6, 8, 8, 8, 9, 10, 10, 10};
        int[] y = {0, 1, 2, 3, 4, 3, 4, 0, 1, 2,
                3, 2, 3, 4, 2, 3, 4, 2, 2, 3, 4};
        double s = min(w,h) / 25;
        var startX = (w - 11 * s) / 2;
        var startY = (h - 5 * s) / 2;
        for (int i = 0; i < 21; i++) {
            gc.fillRect(startX + (double)x[i] * s, startY + (double)y[i] * s, s, s);
        }
    }
}
