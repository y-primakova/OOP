package ru.nsu.primakova;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Class Snake.
 */
public class Snake {
    private ArrayList<ArrayList<Integer>> snake;
    private int length = 1;
    private final int maxLength;
    private final int columns;
    private final int rows;
    private String cond = "UP";
    private String prevCond = "UP";
    private final Apples apples;
    private boolean isEnd;

    public Snake(int columns, int rows, Apples apples, Barriers barriers) {
        this.columns = columns;
        this.rows = rows;
        this.snake = new ArrayList<>();
        Random rand = new Random();
        while (true) {
            var x = rand.nextInt(columns);
            var y = rand.nextInt(rows);
            if (!barriers.get(x, y) && !apples.getApple(x, y)) {
                snake.add(new ArrayList<>());
                snake.get(0).add(x);
                snake.get(0).add(y);
                break;
            }
        }
        this.maxLength = columns * rows - apples.getNumApple() - barriers.getLength() + 1;
        this.apples = apples;
    }

    public ArrayList<ArrayList<Integer>> getSnake() {
        return snake;
    }

    public int getLength() {
        return length;
    }

    public boolean isEnd() {
        return isEnd;
    }

    /**
     * set condition.
     *
     * @param cond - condition
     */
    public void setCond(String cond) {
        if (Objects.equals(cond, "UP") || Objects.equals(cond, "DOWN")
                || Objects.equals(cond, "LEFT") || Objects.equals(cond, "RIGHT")) {
            this.cond = cond;
        }
    }

    public void changeSnake() {
        if (Objects.equals(prevCond, "DOWN") && Objects.equals(cond, "UP")) {
            cond = "DOWN";
        } else if (Objects.equals(prevCond, "UP") && Objects.equals(cond, "DOWN")) {
            cond = "UP";
        } else if (Objects.equals(prevCond, "LEFT") && Objects.equals(cond, "RIGHT")) {
            cond = "LEFT";
        } else if (Objects.equals(prevCond, "RIGHT") && Objects.equals(cond, "LEFT")) {
            cond = "RIGHT";
        }
        prevCond = cond;

        var head = new int[2];
        head[0] = snake.get(0).get(0);
        head[1] = snake.get(0).get(1);
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
        if (isSnakeEatApple(head)) {
            length++;
            if (maxLength != length) {
                apples.createApple(snake, head);
            }
            snake.add(new ArrayList<>());
        }
        snake = updateSnake(head);
    }

    private ArrayList<ArrayList<Integer>> updateSnake(int[] head) {
        ArrayList<ArrayList<Integer>> newS = new ArrayList<>();
        newS.add(new ArrayList<>());
        newS.get(0).add(head[0]);
        newS.get(0).add(head[1]);
        if (snake.get(0).get(0) == head[0] && snake.get(0).get(1) == head[1]) {
            isEnd = true;
            return snake;
        }

        for (int i = 1; i < length; i++) {
            if (i != length - 1) {
                if (snake.get(i).get(0) == head[0] && snake.get(i).get(1) == head[1]) {
                    isEnd = true;
                    return snake;
                }
            }
            newS.add(new ArrayList<>());
            newS.get(i).add(snake.get(i - 1).get(0));
            newS.get(i).add(snake.get(i - 1).get(1));
        }
        return newS;
    }

    private boolean isSnakeEatApple(int[] head) {
        if (apples.getApple(head[0], head[1])) {
            return true;
        }
        return false;
    }
}