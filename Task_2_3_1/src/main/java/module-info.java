module oop.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ru.nsu.fit.oop.melnikov.game.snake.presenter to javafx.fxml;
    exports ru.nsu.fit.oop.melnikov.game.snake;
    opens ru.nsu.fit.oop.melnikov.game.snake to javafx.fxml;

}