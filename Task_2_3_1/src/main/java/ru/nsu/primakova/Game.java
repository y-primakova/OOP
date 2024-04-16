package ru.nsu.primakova;

import static java.lang.System.currentTimeMillis;

/**
 * Class Game.
 */
public class Game implements Runnable {
//    private final int[][] fieldState;
    private final Snake snake;
    private final Apples apples;
    private final Barriers barriers;
    private final Painter painter;
    private final int speed;
    private final int winLength;

    /**
     * class constructor.
     *
     * @param snake - snake
     * @param apples - apples
     * @param barriers - barriers
     * @param painter - draws the snake
     * @param speed - speed of the snake
     * @param winLength - win length of the snake
     */
    public Game(Snake snake, Apples apples, Barriers barriers, Painter painter, int speed, int winLength) {
        this.snake = snake;
        this.apples = apples;
        this.barriers = barriers;
        this.painter = painter;
        this.speed = speed;
        this.winLength = winLength;
    }

    @Override
    public void run() {
        long start = 0;
        while (!snake.isEnd() && snake.getLength() != winLength) {
            if (currentTimeMillis() - start >= speed) {
                var head = new int[2];
                var tail = new int[2];
                head[0] = snake.getSnake().get(0).get(0);
                head[1] = snake.getSnake().get(0).get(1);
                tail[0] = snake.getSnake().get(snake.getLength() - 1).get(0);
                tail[1] = snake.getSnake().get(snake.getLength() - 1).get(1);
                snake.changeSnake();
                if (barriers.get(snake.getSnake().get(0).get(0), snake.getSnake().get(0).get(1))) {
                    try {
                        painter.end(snake, apples, barriers, true, head, tail);
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    painter.paint(snake, apples, barriers);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                start = currentTimeMillis();
            }
        }
    }
}
