package ru.nsu.primakova;

import java.util.Random;

/**
 * Class Apple.
 */
public class Apple {
    private final boolean[][] apple;
    private final int nColumns;
    private final int nRows;
    private final int nApple;

    public Apple(int nApple, int nColumns, int nRows, boolean[][] barrier) {
        this.nColumns = nColumns;
        this.nRows = nRows;
        this.nApple = nApple;
        this.apple = new boolean[nColumns][nRows];
        for (int i = 0; i < nColumns; i++) {
            for (int j = 0; j < nRows; j++) {
                this.apple[i][j] = false;
            }
        }
        for (int i = 0; i < nApple; i++){
            createApple(barrier);
        }
    }

    public boolean getApple(int x, int y) {
        return this.apple[x][y];
    }

    public int getNumApple() {
        return this.nApple;
    }

    public void createApple(boolean[][] array) {
        Random rand = new Random();
        while (true) {
            var x = rand.nextInt(nColumns);
            var y = rand.nextInt(nRows);
            if (!apple[x][y] && !array[x][y]) {
                apple[x][y] = true;
                break;
            }
        }
    }

    public void createApple(int[][] array) {
        Random rand = new Random();
        while (true) {
            var x = rand.nextInt(nColumns);
            var y = rand.nextInt(nRows);
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
