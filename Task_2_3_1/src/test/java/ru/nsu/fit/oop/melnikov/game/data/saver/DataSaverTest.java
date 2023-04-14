package ru.nsu.fit.oop.melnikov.game.data.saver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.model.ModelInit;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;

class DataSaverTest extends ModelInit {

  public DataSaverTest() throws SnakeInSnakeException {
    super();
  }

  @Test
  void test() throws IOException {

    String toCompare = """
        7 7
        #######
        #     #
        #     #
        #     #
        #     #
        #     #
        #######
        3
        1 1
        2 1
        3 1
        """;

    Assertions.assertDoesNotThrow(() -> DataSaver.save(field, snake, "test.txt"));

    File file = new File("test.txt");
    byte[] fileValue = new byte[(int) file.length()];
    try (FileInputStream fis = new FileInputStream(file)) {
      fis.read(fileValue);
    }
    String stringFileValue = new String(fileValue, StandardCharsets.UTF_8);
    Assertions.assertEquals(toCompare.trim(), stringFileValue.trim());
  }

}