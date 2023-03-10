package ru.nsu.fit.oop.melnikov;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Logistics {

  private final int[] orders;
  private final WareHouse wareHouse;

  public Logistics(@NotNull Set<Cook> cooks, Set<Courier> couriers, WareHouse wareHouse) {
    this.wareHouse = wareHouse;
    this.orders = new int[1];

    for (Cook cook : cooks) {
      new Thread(() -> cook.work(this::waitForOrders, this::putPizza)).start();
    }

    for (Courier courier : couriers) {
      new Thread(() -> courier.work(this::getPizzas)).start();
    }

  }

  private void getPizzas(int max) {
    int pizzasAmount = 0;
    while (pizzasAmount < max) {
      synchronized (wareHouse) {
        try {
          pizzasAmount += wareHouse.takePizzas(max);
          wareHouse.notifyAll();
        } catch (IndexOutOfBoundsException e) {
          try {
            wareHouse.wait();
          } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
    }
  }

  private void putPizza() {
    synchronized (wareHouse) {
      try {
        wareHouse.putPizza();
        wareHouse.notifyAll();
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
      orders[0]++;
      orders.notify();
    }
  }

}
