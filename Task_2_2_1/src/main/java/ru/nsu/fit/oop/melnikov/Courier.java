package ru.nsu.fit.oop.melnikov;

import static java.lang.Thread.currentThread;import static java.lang.Thread.sleep;

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
