package ru.nsu.fit.oop.melnikov;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.customer.Customer;
import ru.nsu.fit.oop.melnikov.data.loader.ResourceJsonFileLoader;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

class CustomerTest {

  void test() {

    try {
      Pizzeria pizzeria = new ResourceJsonFileLoader().extractPizzeriaFromFilename("test-pizzeria.json");
      pizzeria.start();

      Set<Thread> customers = new HashSet<>();
      for (int i = 0; i < 3; i++) {
        Customer customer = new Customer();
        Thread thread = new Thread(() -> customer.orderPizza(pizzeria, "just Pizza"));
        thread.start();
        customers.add(thread);
      }

      for (Thread thread : customers) {
        try {
          thread.join();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testWithAssert() {
    Assertions.assertDoesNotThrow(this::test);
  }

}
