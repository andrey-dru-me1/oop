package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.fxml.loaders.FXMLLoadersRepository;

public class FXMLPresenter {

    protected FXMLLoadersRepository loadersRepository;
    protected Stage primaryStage;

    public void setFXMLLoadersRepository(FXMLLoadersRepository repository) {
        loadersRepository = repository;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
