package ru.nsu.fit.oop.melnikov.data.loader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Cook;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Courier;

class JsonLoaderTest {

  @Test
  void test() {
    try {
      Pizzeria pizzeria =
          new ResourceJsonFileLoader().extractPizzeriaFromFilename("test-pizzeria.json");

      Assertions.assertEquals("Mama Mia", pizzeria.getName());

      Collection<Cook> expectedCooks = new HashSet<>();
      expectedCooks.add(new Cook(1, "John"));
      expectedCooks.add(new Cook(5, "Max"));

      Collection<Courier> expectedCouriers = new HashSet<>();
      expectedCouriers.add(new Courier(1, "James"));
      expectedCouriers.add(new Courier(2, "Julia"));

      Assertions.assertIterableEquals(expectedCooks, pizzeria.getCooks());
      Assertions.assertIterableEquals(expectedCouriers, pizzeria.getCouriers());

      Assertions.assertEquals(3, pizzeria.getWarehouse().getCapacity());

    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
