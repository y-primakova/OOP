package ru.nsu.primakova;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class Field.
 */
public class Field extends Application {
    private int w;
    private int h;
    private int nColumns;
    private int nRows;
    private int nApple;
    private int winLength;
    private int speed;
    private Barrier barrier;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        level1();
        check();
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(w, h);
        var context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        var apple = new Apple(nApple, nColumns, nRows, barrier.get());
        var snake = new Snake(nColumns, nRows, apple, barrier);
        canvas.setOnKeyPressed(
                (KeyEvent event) -> {
                    snake.setCond(event.getCode().toString());
                });
        var painter = new Painter(context, w, h, nColumns, nRows, winLength);
        var game = new Game(snake, apple, painter, speed, winLength);

        var thread = new Thread(game);
        thread.start();
    }

    private void check() {
        if (winLength <= 0 || winLength > nColumns * nRows - nApple - barrier.getLength() + 1) {
            this.winLength = nColumns * nRows - nApple - barrier.getLength() + 1;
        }
        if (nApple <= 0 || nApple >= nColumns * nRows - barrier.getLength()) {
            nApple = 1;
        }
    }

    private void level1() {
        w = 700;
        h = 700;
        nColumns = 10;
        nRows = 10;
        nApple = 1;
        winLength = 3;
        speed = 300;

        barrier = new Barrier(nColumns, nRows);
        barrier.set(5, 5);
        barrier.set(5, 4);
        barrier.set(5, 3);
    }

    private void level2() {
        w = 700;
        h = 700;
        nColumns = 4;
        nRows = 3;
        nApple = 1;
        winLength = 100;
        speed = 1000;

        barrier = new Barrier(nColumns, nRows);
        barrier.set(0, 0);
    }

    private void level3() {
        w = 700;
        h = 700;
        nColumns = 30;
        nRows = 30;
        nApple = 3;
        winLength = 30;
        speed = 200;

        barrier = new Barrier(nColumns, nRows);
        barrier.set(26, 0);
        barrier.set(27, 0);
        barrier.set(28, 0);
        barrier.set(29, 0);
        barrier.set(29, 1);
        barrier.set(26, 25);
        barrier.set(27, 25);
        barrier.set(28, 25);
        barrier.set(29, 25);
        barrier.set(28, 26);
        barrier.set(29, 26);
        barrier.set(29, 27);
        barrier.set(29, 28);
        barrier.set(29, 29);
        barrier.set(5, 7);
        barrier.set(5, 8);
        barrier.set(6, 7);
        barrier.set(10, 19);
        barrier.set(11, 19);
        barrier.set(11, 22);
        barrier.set(12, 19);
        barrier.set(12, 20);
        barrier.set(12, 21);
        barrier.set(12, 22);
        barrier.set(12, 23);
        barrier.set(12, 24);
        barrier.set(12, 25);
        barrier.set(19, 11);
        barrier.set(19, 12);
        barrier.set(19, 13);
        barrier.set(19, 14);
        barrier.set(19, 15);
    }
}
