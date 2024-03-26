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
    private final int nX;
    private final int nY;
    private String cond = "UP";
    private final Apple apple;

    private boolean isEnd = false;

    public Snake(int nX, int nY, Apple apple) {
        this.nX = nX;
        this.nY = nY;
        this.snake = new int[nX][nY];
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                this.snake[i][j] = 0;
            }
        }
        Random rand = new Random();
        this.snake[rand.nextInt(nX)][rand.nextInt(nY)] = 1;
        this.head[0] = 10;
        this.head[1] = 10;
        this.length = 1;
        this.apple = apple;
    }

    public int getSnake(int x, int y) {
        return snake[x][y];
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setCond(String cond) {
        if(Objects.equals(cond, "UP") || Objects.equals(cond, "DOWN") ||
                Objects.equals(cond, "LEFT") || Objects.equals(cond, "RIGHT")) {
            this.cond = cond;
        }
    }

    public void changeSnake() {
        if (Objects.equals(cond, "UP")) {
            if(head[1] == 0) {
                head[1] = nY - 1;
            } else {
                head[1] -= 1;
            }
        } else if (Objects.equals(cond, "DOWN")) {
            if(head[1] == nY - 1) {
                head[1] = 0;
            } else {
                head[1] += 1;
            }
        } else if (Objects.equals(cond, "LEFT")) {
            if(head[0] == 0) {
                head[0] = nX - 1;
            } else {
                head[0] -= 1;
            }
        } else if (Objects.equals(cond, "RIGHT")) {
            if (head[0] == nX - 1) {
                head[0] = 0;
            } else {
                head[0] += 1;
            }
        }
        if (isSnakeEatApple()) {
            length++;
            while (true) {
                apple.createApple();
                if (snake[apple.getApple()[0]][apple.getApple()[1]] == 0 && !isSnakeEatApple()) {
                    break;
                }
            }
        }
        updateSnake();
        snake[head[0]][head[1]] = 1;
    }

    private void updateSnake() {
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if (snake[i][j] == length) {
                    if (i == head[0] && j == head[1]) {
                        isEnd = true;
                        return;
                    }
                    snake[i][j] = 0;
                } else if (snake[i][j] > 0) {
                    if (i == head[0] && j == head[1]) {
                        isEnd = true;
                        return;
                    }
                    snake[i][j] += 1;
                }
            }
        }
    }

    private boolean isSnakeEatApple() {
        if (apple.getApple()[0] == head[0] && apple.getApple()[1] == head[1]) {
            return true;
        }
        return false;
    }

    private boolean isSnakeEatSnake() {
        if (apple.getApple()[0] == head[0] && apple.getApple()[1] == head[1]) {
            return true;
        }
        return false;
    }
}
