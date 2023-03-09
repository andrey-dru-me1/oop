package ru.nsu.fit.oop.melnikov.logistics;

import java.util.Set;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.kitchen.Cook;
import ru.nsu.fit.oop.melnikov.warehouse.WareHouse;

public class Logistics {

  private final int[] orders;
  private final WareHouse wareHouse;

  public Logistics(@NotNull Set<Cook> cooks, WareHouse wareHouse) {
    this.wareHouse = wareHouse;
    this.orders = new int[1];

    for (Cook cook : cooks) {
      new Thread(() -> cook.work(this::waitForOrders, this::putPizza)).start();
    }

  }

  private void putPizza() {
    synchronized (wareHouse) {
      try {
        wareHouse.putPizza();
      } catch (IndexOutOfBoundsException e) {
        try {
          wareHouse.wait();
        } catch (InterruptedException ex) {
          throw new RuntimeException(ex);
        }
      }
    }
  }

  private void waitForOrders() {
    while (true) {
      synchronized (orders) {
        if (orders[0] > 0) {
          orders[0]--;
          System.out.println("Cook received the order");
          return;
        } else {
          try {
            orders.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
  }

  public void orderNewPizza() {
    synchronized (orders) {
      System.out.println("An order has received");
      orders[0]++;
      orders.notify();
    }
  }

}
