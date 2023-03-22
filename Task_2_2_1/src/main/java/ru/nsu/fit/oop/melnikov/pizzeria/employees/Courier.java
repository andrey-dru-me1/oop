package ru.nsu.fit.oop.melnikov.pizzeria.employees;

import static java.lang.Thread.currentThread;import static java.lang.Thread.sleep;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

public record Courier(int trunkSize, String name) {

  public void work(Warehouse warehouse) {

    try {

      while (!Thread.interrupted()) {

        Order order = warehouse.takeOrder();
        order.updateStatus("is taken by courier " + this.name);

        // Delivering
        sleep(1000);

        order.delivered();

      }

    } catch (InterruptedException e) {
      currentThread().interrupt();
    }
  }
}
