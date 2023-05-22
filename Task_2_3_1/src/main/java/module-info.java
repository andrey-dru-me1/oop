module oop.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.googlecode.lanterna;

    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings to com.fasterxml.jackson.databind;
    exports ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings to com.fasterxml.jackson.databind;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game to javafx.fxml;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.menu to javafx.fxml;
    exports ru.nsu.fit.oop.melnikov.game.snake.javafx;
    opens ru.nsu.fit.oop.melnikov.game.snake.javafx to javafx.fxml;
    exports ru.nsu.fit.oop.melnikov.game.snake.terminal;
    opens ru.nsu.fit.oop.melnikov.game.snake.terminal to javafx.fxml;

}