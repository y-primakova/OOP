package ru.nsu.primakova;

import java.util.Random;

/**
 * Class Apple.
 */
public class Apple {
    private final int[] apple = new int[2];
    private final int nX;
    private final int nY;

    public Apple(int nX, int nY) {
        this.nX = nX;
        this.nY = nY;
        createApple();
    }

    public int[] getApple() {
        return this.apple;
    }

    public void createApple() {
        Random rand = new Random();
        apple[0] = rand.nextInt(nX);
        apple[1] = rand.nextInt(nY);
    }
}
