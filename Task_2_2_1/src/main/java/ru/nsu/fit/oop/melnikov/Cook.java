package ru.nsu.fit.oop.melnikov;

import static java.lang.Thread.sleep;
public record Cook(int experience, String name) {

  public void work(OrderQueue orders, Warehouse warehouse) {

    try {
      while (!Thread.interrupted()) {

        Order order = orders.take();
        order.updateStatus("is started cooking by cook " + this.name);

        //Cooking
        sleep(5000 / experience);
        order.updateStatus("is cooked!");

        warehouse.putOrder(order);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
