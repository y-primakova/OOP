package ru.nsu.primakova;

import java.util.Random;

/**
 * Class Apple.
 */
public class Apple {
    private final boolean[][] apple;
    private final int columns;
    private final int rows;
    private final int numApple;

    public Apple(int numApple, int columns, int rows, boolean[][] barrier) {
        this.columns = columns;
        this.rows = rows;
        this.numApple = numApple;
        this.apple = new boolean[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                this.apple[i][j] = false;
            }
        }
        for (int i = 0; i < numApple; i++) {
            createApple(barrier);
        }
    }

    public boolean getApple(int x, int y) {
        return this.apple[x][y];
    }

    public int getNumApple() {
        return this.numApple;
    }

    public void createApple(boolean[][] array) {
        Random rand = new Random();
        while (true) {
            var x = rand.nextInt(columns);
            var y = rand.nextInt(rows);
            if (!apple[x][y] && !array[x][y]) {
                apple[x][y] = true;
                break;
            }
        }
    }

    public void createApple(int[][] array) {
        Random rand = new Random();
        while (true) {
            var x = rand.nextInt(columns);
            var y = rand.nextInt(rows);
            if (!apple[x][y] && array[x][y] == 0) {
                apple[x][y] = true;
                break;
            }
        }
    }

    public void createApple(int[][] array, int[] head) {
        createApple(array);
        apple[head[0]][head[1]] = false;
    }
}
