package ru.nsu.primakova;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class .
 */
public class Field extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        var width = 500;
        var height = 500;
        var size = 20;
        Canvas canvas = new Canvas(width, height);
        var context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        var apple = new Apple(width / size, height / size);
        var snake = new Snake(width / size, height / size, apple);
        var game = new Game(context, snake, apple);

        canvas.setOnKeyPressed(
                (KeyEvent event) -> {
                    snake.setCond(event.getCode().toString());
                });

        var thread = new Thread(game);
        thread.start();
    }
}
