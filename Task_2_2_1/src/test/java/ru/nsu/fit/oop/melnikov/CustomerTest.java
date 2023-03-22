package ru.nsu.fit.oop.melnikov;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;import java.util.Set;import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  void test() {

    Set<Cook> cooks = new HashSet<>();
    Set<Courier> couriers = new HashSet<>();

    cooks.add(new Cook(1, "John"));
    couriers.add(new Courier(1, "James"));

    Warehouse warehouse = new Warehouse(1);

    Pizzeria pizzeria = new Pizzeria("Mamma mia", cooks, couriers, warehouse);
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
  }

}
