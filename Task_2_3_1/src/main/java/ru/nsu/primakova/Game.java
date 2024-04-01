package ru.nsu.primakova;

import static java.lang.System.currentTimeMillis;

/**
 * Class Game.
 */
public class Game implements Runnable {
    private final Snake snake;
    private final Apple apple;
    private final Painter painter;
    private final int speed;
    private final int winLength;

    /**
     * class constructor.
     *
     * @param snake - snake
     * @param apple - apple
     * @param painter - draws the snake
     * @param speed - speed of the snake
     * @param winLength - win length of the snake
     */
    public Game(Snake snake, Apple apple, Painter painter, int speed, int winLength) {
        this.snake = snake;
        this.apple = apple;
        this.painter = painter;
        this.speed = speed;
        this.winLength = winLength;
    }

    @Override
    public void run() {
        long start = 0;
        while (!snake.isEnd() && snake.getLength() != winLength) {
            if (currentTimeMillis() - start >= speed) {
                snake.changeSnake();
                try {
                    painter.paint(snake, apple);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                start = currentTimeMillis();
            }
        }
    }
}
