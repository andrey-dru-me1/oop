package ru.nsu.fit.oop.melnikov.game.snake.presenter.utils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class JavafxDesigner {

  private JavafxDesigner() {}

  public static void makeMatchParent(Node child) {
    AnchorPane.setRightAnchor(child, 0.0);
    AnchorPane.setLeftAnchor(child, 0.0);
    AnchorPane.setTopAnchor(child, 0.0);
    AnchorPane.setBottomAnchor(child, 0.0);
  }
}
