package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects.*;

public class CellObjectDTOSRepository {

  private final List<CellObjectDTO> dtos;
  private final CellObjectDTO defaultDTO;

  public CellObjectDTOSRepository(String texturePack) {
    String texturePackPath = "/textures/" + texturePack;
    defaultDTO = new EmptyCellDTO(texturePackPath);
    dtos =
        List.of(
            new WallDTO(texturePackPath),
            new SnakeHeadDTO(texturePackPath),
            new SnakeNodeDTO(texturePackPath),
            new AppleDTO(texturePackPath),
            new OddEmptyCellDTO(texturePackPath),
            defaultDTO);
  }

  public <T extends CellObject> CellObjectDTO get(Cell cell, T cellObject) {
    for (CellObjectDTO dto : dtos) {
      if (dto.checkForCoincidence(cell, cellObject)) {
        return dto;
      }
    }
    return defaultDTO;
  }
}
