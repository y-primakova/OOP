package ru.nsu.primakova;

/**
 * Class Barrier.
 */
public class Barrier {
    private final boolean[][] barrier;
    private int length;

    public Barrier(int nColumns, int nRows) {
        barrier = new boolean[nColumns][nRows];
        for (int i = 0; i < nColumns; i++) {
            for (int j = 0; j < nRows; j++) {
                this.barrier[i][j] = false;
            }
        }
        length = 0;
    }

    public boolean[][] get() {
        return barrier;
    }

    public boolean get(int x, int y) {
        return barrier[x][y];
    }


    public int getLength() {
        return length;
    }

    public void set(int x, int y) {
        barrier[x][y] = true;
        length++;
    }
}
