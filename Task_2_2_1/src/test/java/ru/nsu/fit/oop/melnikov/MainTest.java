package ru.nsu.fit.oop.melnikov;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void test() {
    System.setIn(new ByteArrayInputStream("order pepperoni\norder cheese".getBytes()));
    Assertions.assertDoesNotThrow(() -> Main.main(new String[0]));
  }

  @Test
  void testExit() {
    System.setIn(new ByteArrayInputStream("order pepperoni\nexit".getBytes()));
    Assertions.assertDoesNotThrow(() -> Main.main(new String[0]));
  }
}
