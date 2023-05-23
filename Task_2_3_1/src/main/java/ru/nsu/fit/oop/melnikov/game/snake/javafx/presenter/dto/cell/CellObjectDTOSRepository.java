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

  /**
   * Changes textures for all the cell objects.
   *
   * @param texturePack name of a new texture pack to change to
   */
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

/**
* Returns first occurrence of cell object dto whose condition is satisfied by cellObject.
 * @param cell cell to check
 * @param cellObject cell object to check
 * @return cell object dto whose condition is satisfied by cell object
 * @param <T> implementation of cell object
*/
  public <T extends CellObject> CellObjectDTO get(Cell cell, T cellObject) {
    for (CellObjectDTO dto : dtos) {
      if (dto.checkForCoincidence(cell, cellObject)) {
        return dto;
      }
    }
    return defaultDTO;
  }
}
