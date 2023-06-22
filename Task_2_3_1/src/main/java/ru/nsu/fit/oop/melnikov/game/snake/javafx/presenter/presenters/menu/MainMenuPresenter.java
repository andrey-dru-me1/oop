package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings.SettingsPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;
import ru.nsu.fit.oop.melnikov.game.snake.model.GameData;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.FieldGenerator;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakePoint;

import java.util.ArrayList;
import java.util.List;

public class MainMenuPresenter extends FXMLPresenter {
  public void onNewGameClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.SELECT_MAP);
    stage.setScene(loader.getRoot());
  }

  public void onExitClick() {
    stage.close();
  }

  /** Initializes and runs settings screen. */
  public void onSettingsClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.SETTINGS);
    SettingsPresenter settingsPresenter = loader.getController();
    settingsPresenter.init();
    settingsPresenter.setPrevScene(stage.getScene());
    stage.setScene(loader.getRoot());
  }

    public void onGenerateClick() {
      Field field = FieldGenerator.generate(80, 50, 10);
      Snake snake = null;
      for (Cell[] cells : field.getCells()) {
        for (Cell cell : cells) {
          if(!cell.contains(Wall.class)) {
            snake = new Snake(field, List.of(new SnakePoint(cell)));
          }
        }
      }
      GameData gameData = new GameData(field, List.of(snake));
      loadersRepository.<GameScreenPresenter>getPresenter(FXMLScreen.GAME_SCREEN).initialize(gameData);
    }
}
