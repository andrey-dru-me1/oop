package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game;

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
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Game;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTOSRepository;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings.SettingsPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.JavafxDesigner;
import ru.nsu.fit.oop.melnikov.game.snake.model.GameData;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;

public class GameScreenPresenter extends FXMLPresenter {
  @FXML public Label scoreLabel;
  @FXML public Pane pane;
  @FXML public BorderPane borderPane;
  private Game game;

  private GameData gameData;
  @FXML private Canvas canvas;
  private SimpleIntegerProperty score;

  public void setScore(int score) {
    this.score.set(score);
  }

  public GameData getGameData() {
    return gameData;
  }

  /**
   * Creates game, loads snake and field and initializes all the textures.
   *
   * @param filename level data
   */
  public void initialize(String filename) {
    gameData = DataLoader.load(filename);
    initialize(gameData, filename);
  }

  public void initialize(GameData gameData, String mapName) {

    this.gameData = gameData;

    JavafxDesigner.makeMatchParent(borderPane);
    canvas.getGraphicsContext2D().setImageSmoothing(false);

    Field field = gameData.field();

    CellDTO[][] cellDTOS = new CellDTO[field.getWidth()][field.getHeight()];

    NumberBinding rectSize = calculateRectSize();

    canvas.heightProperty().bind(rectSize.multiply(field.getHeight()));
    canvas.widthProperty().bind(rectSize.multiply(field.getWidth()));

    CellObjectDTOSRepository repository =
            new CellObjectDTOSRepository(GameSettings.INSTANCE.getTextureName());
    GameSettings.INSTANCE.setRepository(repository);

    game = new Game(gameData, this, mapName);

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
    game.regenerateApples(
            Math.max(
                    GameSettings.INSTANCE.getApplesPercentage()
                            * (field.getWidth() * field.getHeight())
                            / 100,
                    1));
    pauseGame();

    this.score = new SimpleIntegerProperty(0);
    scoreLabel.textProperty().bind((new SimpleStringProperty("Score: ")).concat(score.asString()));
  }

  /**
   * Calculates size of cell to show and adds a listener if scene is resizing.
   *
   * @return calculated rectangle size with listener
   */
  private NumberBinding calculateRectSize() {
    return Bindings.min(
        canvas
            .getScene()
            .heightProperty()
            .subtract(scoreLabel.heightProperty())
            .divide(gameData.field().getHeight()),
        canvas.getScene().widthProperty().divide(gameData.field().getWidth()));
  }

  public Game getGame() {
    return game;
  }

  /**
   * Specifies game behaviour on user's actions.
   *
   * @param keyEvent information about key
   */
  private void onKeyPressed(KeyEvent keyEvent) {
    if (GameSettings.INSTANCE.getKeys().containsKey(keyEvent.getCode())) {
      switch (GameSettings.INSTANCE.getSnakeKey(keyEvent.getCode())) {
        case LEFT -> game.addDirection(Direction.LEFT);
        case RIGHT -> game.addDirection(Direction.RIGHT);
        case DOWN -> game.addDirection(Direction.DOWN);
        case UP -> game.addDirection(Direction.UP);
        case MENU -> {
          FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.SETTINGS);
          SettingsPresenter settingsPresenter = loader.getController();
          settingsPresenter.setPrevScene(stage.getScene());
          settingsPresenter.init();
          pauseGame();
          stage.setScene(loader.getRoot());
        }
      }
    }
  }

  /**
   * Pauses game and redefines reaction on key events: now user has to press any button for game
   * continuing
   */
  private void pauseGame() {
    game.pause();
    canvas
        .getScene()
        .setOnKeyPressed(
            keyEvent -> {
              game.play();
              canvas.getScene().setOnKeyPressed(this::onKeyPressed);
              canvas.getScene().getOnKeyPressed().handle(keyEvent);
            });
  }

  /** Stops the game correctly. */
  public void stopAll() {
    if (game != null) {
      game.stop();
    }
  }

  /** Called when game is ended. Loads game end scene. */
  public void onGameEnd() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.GAME_END);
    loader.<EndScreenPresenter>getController().updateScore(game.getGameState());
    stage.setScene(loader.getRoot());
  }
}
