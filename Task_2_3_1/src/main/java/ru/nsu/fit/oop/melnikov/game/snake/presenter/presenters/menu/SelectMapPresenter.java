package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.MapName;

public class SelectMapPresenter extends FXMLPresenter {

  @FXML public ListView<HBox> listView;

  @FXML
  private void initialize() {
    Collection<String> mapFilenames = MapName.getMapFilenames();
    List<HBox> listItems = new ArrayList<>(mapFilenames.size());
    for (String mapFilename : mapFilenames) {
      HBox listItem = new HBox();
      listItem.getChildren().add(new Label(mapFilename));
      Button button = new Button("Choose");
      listItem.getChildren().add(button);
      button.setOnAction(
          t -> {
            FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.GAME_SCREEN);
            stage.setScene(loader.getRoot());
            loader.<GameScreenPresenter>getController().initialize(mapFilename);
          });
      listItems.add(listItem);
    }
    listView.setItems(FXCollections.observableList(listItems));
  }
}
