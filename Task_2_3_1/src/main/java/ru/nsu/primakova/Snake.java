package ru.nsu.primakova;

import java.util.Objects;
import java.util.Random;

/**
 * Class Snake.
 */
public class Snake {
    private final int[] head = new int[2];
    private final int[][] snake;
    private int length;
    private final int maxLength;
    private final int columns;
    private final int rows;
    private String cond = "UP";
    private final Apple apple;
    private boolean isEnd = false;

    public Snake(int columns, int rows, Apple apple, Barrier barrier) {
        this.columns = columns;
        this.rows = rows;
        this.snake = new int[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                this.snake[i][j] = 0;
                if (barrier.get(i, j)) {
                    this.snake[i][j] = -1;
                }
            }
        }
        Random rand = new Random();
        while (true) {
            var x = rand.nextInt(columns);
            var y = rand.nextInt(rows);
            if (!barrier.get(x, y) && !apple.getApple(x, y)) {
                this.snake[x][y] = 1;
                this.head[0] = x;
                this.head[1] = y;
                break;
            }
        }
        this.length = 1;
        this.maxLength = columns * rows - barrier.getLength() - apple.getNumApple() + 1;
        this.apple = apple;
    }

    public int getSnake(int x, int y) {
        return snake[x][y];
    }

    public int getLength() {
        return length;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setCond(String cond) {
        if (Objects.equals(cond, "UP") || Objects.equals(cond, "DOWN")
                || Objects.equals(cond, "LEFT") || Objects.equals(cond, "RIGHT")) {
            this.cond = cond;
        }
    }

    public void changeSnake() {
        if (Objects.equals(cond, "UP")) {
            if (head[1] == 0) {
                head[1] = rows - 1;
            } else {
                head[1] -= 1;
            }
        } else if (Objects.equals(cond, "DOWN")) {
            if (head[1] == rows - 1) {
                head[1] = 0;
            } else {
                head[1] += 1;
            }
        } else if (Objects.equals(cond, "LEFT")) {
            if (head[0] == 0) {
                head[0] = columns - 1;
            } else {
                head[0] -= 1;
            }
        } else if (Objects.equals(cond, "RIGHT")) {
            if (head[0] == columns - 1) {
                head[0] = 0;
            } else {
                head[0] += 1;
            }
        }
        if (isSnakeEatApple()) {
            length++;
            if (maxLength != length) {
                apple.createApple(snake, head);
            }
        } else if (snake[head[0]][head[1]] == -1) {
            isEnd = true;
            return;
        }
        updateSnake();
        if (!isEnd()) {
            snake[head[0]][head[1]] = 1;
        }
    }

    private void updateSnake() {
        int[] tail = {-1, -1};
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (snake[i][j] == length) {
                    tail[0] = i;
                    tail[1] = j;
                    snake[i][j] = 0;
                } else if (snake[i][j] > 0) {
                    if (i == head[0] && j == head[1]) {
                        isEnd = true;
                        if (tail[0] != -1) {
                            snake[tail[0]][tail[1]] = length;
                        }
                        return;
                    }
                    snake[i][j] += 1;
                }
            }
        }
    }

    private boolean isSnakeEatApple() {
        if (apple.getApple(head[0], head[1])) {
            return true;
        }
        return false;
    }
}