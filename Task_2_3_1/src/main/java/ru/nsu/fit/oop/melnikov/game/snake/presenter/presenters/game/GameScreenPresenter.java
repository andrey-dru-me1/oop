package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.Game;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTOSRepository;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings.SettingsPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreen;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.JavafxDesigner;

public class GameScreenPresenter extends FXMLPresenter {
  @FXML public Label scoreLabel;
  @FXML public Pane pane;
  @FXML public BorderPane borderPane;
  private Game game;

  private Field field;
  @FXML private Canvas canvas;
  private SimpleIntegerProperty score;

  public void setScore(int score) {
    this.score.set(score);
  }

  public Field getField() {
    return field;
  }

  public void initialize(String filename) {
    Snake snake;

    JavafxDesigner.makeMatchParent(borderPane);

    canvas.setOnKeyPressed(this::onKeyPressed);
    canvas.getScene().setOnKeyPressed(keyEvent -> canvas.getOnKeyPressed().handle(keyEvent));

    canvas.getGraphicsContext2D().setImageSmoothing(false);

    DataLoader dataLoader = new DataLoader(filename);
    field = dataLoader.getField();

    CellDTO[][] cellDTOS = new CellDTO[field.getWidth()][field.getHeight()];

    NumberBinding rectSize = calculateRectSize();

    canvas.heightProperty().bind(rectSize.multiply(field.getHeight()));
    canvas.widthProperty().bind(rectSize.multiply(field.getWidth()));

    CellObjectDTOSRepository repository = new CellObjectDTOSRepository("default");

    snake = dataLoader.getSnake();
    game = new Game(snake, gameSettings, this, filename);

    for (int i = 0; i < field.getWidth(); i++) {
      Cell[] row = field.getCells()[i];
      for (int j = 0; j < field.getHeight(); j++) {
        Cell cell = row[j];
        cellDTOS[i][j] =
            new CellDTO(
                game,
                cell,
                new Rect<>(rectSize.multiply(i), rectSize.multiply(j), rectSize, rectSize),
                repository,
                canvas.getGraphicsContext2D());
      }
    }

    game.setCellDTOS(cellDTOS);
    game.start();

    this.score = new SimpleIntegerProperty(0);
    scoreLabel.textProperty().bind((new SimpleStringProperty("Score: ")).concat(score.asString()));

    field.generateApple();
  }

  private NumberBinding calculateRectSize() {
    return Bindings.min(
        canvas
            .getScene()
            .heightProperty()
            .subtract(scoreLabel.heightProperty())
            .divide(field.getHeight()),
        canvas.getScene().widthProperty().divide(field.getWidth()));
  }

  private void onKeyPressed(KeyEvent keyEvent) {
    if (gameSettings.getKeys().containsKey(keyEvent.getCode())) {
      switch (gameSettings.getSnakeKey(keyEvent.getCode())) {
        case LEFT -> game.addDirection(Direction.LEFT);
        case RIGHT -> game.addDirection(Direction.RIGHT);
        case DOWN -> game.addDirection(Direction.DOWN);
        case UP -> game.addDirection(Direction.UP);
        case MENU -> {
          FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.SETTINGS);
          SettingsPresenter settingsPresenter = loader.getController();
          settingsPresenter.setPrevScene(stage.getScene());
          settingsPresenter.initialize(gameSettings, () -> game.play());
          game.pause();
          stage.setScene(loader.getRoot());
        }
      }
    }
  }

  public void stopAll() {
    game.stop();
  }

  public void onGameEnd() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.GAME_END);
    loader.<EndScreenPresenter>getController().updateScore(game.getGameState());
    stage.setScene(loader.getRoot());
  }
}
