package ru.nsu.fit.oop.melnikov.logistics;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.kitchen.Cook;

public class Logistics {

//  private final WareHouse wareHouse;
//  private final Queue<Thread> cooksWaitingForOrder;
//  private final Queue<Thread> couriersWaitingForPizzas;
//  private volatile int ordersInQueue;
//  private final Queue<Thread> waitForWareHouseQueue;
//
//  public Logistics(Set<Courier> couriers, Set<Cook> cooks, WareHouse wareHouse) {
//    this.wareHouse = wareHouse;
//    ordersInQueue = 0;
//    cooksWaitingForOrder = new ArrayDeque<>();
//    couriersWaitingForPizzas = new ArrayDeque<>();
//    waitForWareHouseQueue = new ArrayDeque<>();
//
//    for(Cook cook : cooks) {
//      new Thread(() -> cook.work(
//          () -> {
//            synchronized (this) {
//              if (ordersInQueue > 0) {
//                ordersInQueue--;
//                return true;
//              }
//              return false;
//            }
//          },
//          () -> cooksWaitingForOrder.add(Thread.currentThread()),
//          () -> cooksWaitingForOrder.remove(Thread.currentThread()),
//          wareHouse::putPizza,
//          () -> waitForWareHouseQueue.add(Thread.currentThread()),
//          () -> {
//            waitForWareHouseQueue.remove(Thread.currentThread());
//            couriersWaitingForPizzas.peek().notify();
//          }
//      )).start();
//    }
//  }

  private final Queue<Thread> freeCooks;
  private volatile int orders;

  public Logistics(@NotNull Set<Cook> cooks) {
    freeCooks = new ArrayDeque<>();
    orders = 0;
    for (Cook cook : cooks) {
      new Thread(() -> cook.work(() -> {
        while (true) {
          synchronized (this) {
            if (orders > 0) {
              orders--;
              freeCooks.remove(Thread.currentThread());
              System.out.println("Cook received the order");
              return;
            } else {
              try {
                freeCooks.add(Thread.currentThread());
                wait();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
          }
        }
      })).start();
    }
  }

  public synchronized void orderNewPizza() {
    System.out.println("An order has received");
    if (freeCooks.isEmpty()) {
      System.out.println("The order is sent to queue");
      orders++;
    } else {
      freeCooks.peek().notify();
    }
  }

}
