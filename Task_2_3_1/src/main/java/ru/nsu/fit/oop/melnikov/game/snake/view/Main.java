package ru.nsu.fit.oop.melnikov.game.snake.view;

import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Snake the game");
    primaryStage
        .getIcons()
        .add(
            new Image(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream("icon.png")
                )
            )
        );
    primaryStage.setWidth(800);
    primaryStage.setHeight(600);
    primaryStage.show();

    Label label = new Label("Hello world");
    Scene scene = new Scene(label);
    primaryStage.setScene(scene);
    label.setAlignment(Pos.CENTER);
  }
}
