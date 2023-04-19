module oop.snake.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires awaitility;

    opens ru.nsu.fit.oop.melnikov.game.snake.view to javafx.fxml;
    exports ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash;
    exports ru.nsu.fit.oop.melnikov.game.snake.view;

}