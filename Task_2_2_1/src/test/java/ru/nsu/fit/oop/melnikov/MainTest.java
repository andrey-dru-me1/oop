package ru.nsu.fit.oop.melnikov;

import java.io.ByteArrayInputStream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MainTest {

  @ParameterizedTest
  @ValueSource(strings = {"order pepperoni\norder cheese", "start\nclose", "start\nevacuate",
      "unknown command", "order pepperoni\nexit"})
  void test(@NotNull String input) {
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Assertions.assertDoesNotThrow(() -> Main.main(new String[0]));
  }
}
