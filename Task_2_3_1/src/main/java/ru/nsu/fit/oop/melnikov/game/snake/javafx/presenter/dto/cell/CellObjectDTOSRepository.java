package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.objects.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

public class CellObjectDTOSRepository {

  private List<CellObjectDTO> dtos;
  private CellObjectDTO defaultDTO;

  public CellObjectDTOSRepository(String texturePack) {
    onTextureChange(texturePack);
  }

  public void onTextureChange(String texturePack) {
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
