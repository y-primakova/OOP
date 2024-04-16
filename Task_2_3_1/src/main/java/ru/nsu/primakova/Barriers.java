package ru.nsu.primakova;

/**
 * Class Barriers.
 */
public class Barriers {
    private final boolean[][] barriers;
    private int length;

    /**
     * class constructor.
     *
     * @param columns - number of columns
     * @param rows - number of rows
     */
    public Barriers(int columns, int rows) {
        barriers = new boolean[columns][rows];
        length = 0;
    }

    public boolean[][] get() {
        return barriers;
    }

    public boolean get(int x, int y) {
        return barriers[x][y];
    }


    public int getLength() {
        return length;
    }

    public void set(int x, int y) {
        barriers[x][y] = true;
        length++;
    }
}
