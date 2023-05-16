module oop.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.googlecode.lanterna;

    opens ru.nsu.fit.oop.melnikov.game.snake.presenter to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.settings to com.fasterxml.jackson.databind;
    exports ru.nsu.fit.oop.melnikov.game.snake.presenter.settings to com.fasterxml.jackson.databind;
    exports ru.nsu.fit.oop.melnikov.game.snake;
    opens ru.nsu.fit.oop.melnikov.game.snake to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.dto to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.utils to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.menu to javafx.fxml;

}