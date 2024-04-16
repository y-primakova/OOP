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
    private int width;
    private int height;
    private int columns;
    private int rows;
    private int numApples;
    private int winLength;
    private int speed;
    private Barriers barriers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        level3();
        check();
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(width, height);
        var context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Snake");
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.setScene(scene);
        stage.show();

        var apple = new Apples(numApples, columns, rows, barriers.get());
        var snake = new Snake(columns, rows, apple, barriers);
        canvas.setOnKeyPressed(
                (KeyEvent event) -> {
                    snake.setCond(event.getCode().toString());
                });
        var painter = new Painter(context, width, height, columns, rows, winLength);
        var game = new Game(snake, apple, barriers, painter, speed, winLength);
        var thread = new Thread(game);
        thread.start();
    }

    private void check() {
        if (winLength <= 0 || winLength > columns * rows - numApples - barriers.getLength() + 1) {
            this.winLength = columns * rows - numApples - barriers.getLength() + 1;
        }
        if (numApples <= 0 || numApples >= columns * rows - barriers.getLength()) {
            numApples = 1;
        }
    }

    private void level1() {
        width = 500;
        height = 700;
        columns = 10;
        rows = 10;
        numApples = 1;
        winLength = 3;
        speed = 300;

        barriers = new Barriers(columns, rows);
        barriers.set(5, 5);
        barriers.set(5, 4);
        barriers.set(5, 3);
    }

    private void level2() {
        width = 700;
        height = 700;
        columns = 15;
        rows = 15;
        numApples = 4;
        winLength = 100;
        speed = 300;

        barriers = new Barriers(columns, rows);
        barriers.set(0, 0);
        barriers.set(0, 1);
        barriers.set(1, 0);
        barriers.set(8, 8);
        barriers.set(9, 8);
        barriers.set(10, 8);
        barriers.set(10, 9);
    }

    private void level3() {
        width = 500;
        height = 500;
        columns = 30;
        rows = 30;
        numApples = 3;
        winLength = 30;
        speed = 200;

        barriers = new Barriers(columns, rows);
        barriers.set(26, 0);
        barriers.set(27, 0);
        barriers.set(28, 0);
        barriers.set(29, 0);
        barriers.set(29, 1);
        barriers.set(26, 25);
        barriers.set(27, 25);
        barriers.set(28, 25);
        barriers.set(29, 25);
        barriers.set(28, 26);
        barriers.set(29, 26);
        barriers.set(29, 27);
        barriers.set(29, 28);
        barriers.set(29, 29);
        barriers.set(5, 7);
        barriers.set(5, 8);
        barriers.set(6, 7);
        barriers.set(10, 19);
        barriers.set(11, 19);
        barriers.set(11, 22);
        barriers.set(12, 19);
        barriers.set(12, 20);
        barriers.set(12, 21);
        barriers.set(12, 22);
        barriers.set(12, 23);
        barriers.set(12, 24);
        barriers.set(12, 25);
        barriers.set(19, 11);
        barriers.set(19, 12);
        barriers.set(19, 13);
        barriers.set(19, 14);
        barriers.set(19, 15);
    }
}
