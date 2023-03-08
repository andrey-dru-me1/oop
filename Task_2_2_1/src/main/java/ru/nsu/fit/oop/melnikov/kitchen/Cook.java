package ru.nsu.fit.oop.melnikov.kitchen;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Cook {

  private final int experience;

  public Cook(int experience) {
    this.experience = experience;
  }

  public void work(Runnable iAmFree) {

    while (!currentThread().isInterrupted()) {

      iAmFree.run();

      // Cooking...
      System.out.println("Cook has started cooking");
      try {
        sleep(5000 / experience);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println("Pizza is ready!");

    }

  }

//  public void work(
//      Callable<Boolean> getOrder,
//      Runnable putCookInQueue,
//      Runnable removeCookFromQueue,
//      Runnable putPizzaInWareHouse,
//      Runnable waitForWareHouse,
//      Runnable removeFromWareHouseQueue
//  ) {
//    while(!currentThread().isInterrupted()) {
//
//      // Getting order or waiting for order
//      putCookInQueue.run();
//      while(true) {
//        try {
//          if (getOrder.call())
//            break;
//          wait();
//        } catch (Exception e) {
//          throw new RuntimeException(e);
//        }
//      }
//      removeCookFromQueue.run();
//
//      // Cooking...
//      try {
//        sleep(5000/experience);
//      } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//      }
//
//      // Waiting for place in warehouse and put pizza in there
//      waitForWareHouse.run();
//      while(true) {
//        try {
//          putPizzaInWareHouse.run();
//          break;
//        } catch(IndexOutOfBoundsException ignore) {
//
//          // Waiting for place in warehouse
//          try {
//            wait();
//          } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//          }
//
//        }
//      }
//      removeFromWareHouseQueue.run();
//
//    }
//  }

}
