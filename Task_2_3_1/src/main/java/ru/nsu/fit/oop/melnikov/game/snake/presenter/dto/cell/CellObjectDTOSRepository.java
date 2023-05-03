package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell;

import java.util.Map;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects.AppleDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects.EmptyCellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects.SnakeNodeDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects.WallDTO;

public class CellObjectDTOSRepository {

  private final Map<Class<? extends CellObject>, CellObjectDTO> map;

  public CellObjectDTOSRepository(String texturePack) {
    String texturePackPath = "/textures/" + texturePack;
    map = Map.of(
            Wall.class, new WallDTO(texturePackPath),
            EmptyCell.class, new EmptyCellDTO(texturePackPath),
            SnakeNode.class, new SnakeNodeDTO(texturePackPath),
            Apple.class, new AppleDTO(texturePackPath));
  }

  public <T extends CellObject> CellObjectDTO get(T cellObject) {
    return map.get(cellObject.getClass());
  }
}
