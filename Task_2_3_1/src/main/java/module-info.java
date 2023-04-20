module oop.snake.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static awaitility;

    exports ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash;
    exports ru.nsu.fit.oop.melnikov.game.snake.model.exceptions;
    exports ru.nsu.fit.oop.melnikov.game.snake.presenter;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter to javafx.fxml;
    exports ru.nsu.fit.oop.melnikov.game.snake;
    opens ru.nsu.fit.oop.melnikov.game.snake to javafx.fxml;

}