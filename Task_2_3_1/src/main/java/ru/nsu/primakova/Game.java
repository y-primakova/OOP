package ru.nsu.primakova;

import javafx.scene.canvas.GraphicsContext;

import static java.lang.System.currentTimeMillis;

/**
 * Class Game.
 */
public class Game implements Runnable {
    private final GraphicsContext context;
    private final Snake snake;
    private final Apple apple;
    private final int workTime = 10000;

    public Game(GraphicsContext context, Snake snake, Apple apple) {
        this.context = context;
        this.snake = snake;
        this.apple = apple;
    }

    @Override
    public void run() {
        long start = 0;
        long startWork = currentTimeMillis();
        while (!snake.isEnd() && workTime > currentTimeMillis() - startWork) {
            if (currentTimeMillis() - start >= 500) {
                snake.changeSnake();
                try {
                    Painter.paint(context, snake, apple.getApple());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                start = currentTimeMillis();
            }
        }
    }
}
