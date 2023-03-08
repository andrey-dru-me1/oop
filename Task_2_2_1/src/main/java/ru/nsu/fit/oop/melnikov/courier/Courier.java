package ru.nsu.fit.oop.melnikov.courier;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.function.Function;

public class Courier {

  private final int trunkSize;

  public Courier(int trunkSize) {
    this.trunkSize = trunkSize;
  }

  public void work(Function<Integer, Integer> takePizzasFromWareHouse, Runnable delivered) {

    while (!currentThread().isInterrupted()) {

      // Fill all the trunk with pizzas
      int takenPizzasAmount = 0;
      while (takenPizzasAmount != trunkSize) {

        // Try to take pizzas from warehouse
        takenPizzasAmount += takePizzasFromWareHouse.apply(trunkSize - takenPizzasAmount);
        try {
          // Wait for cooks to bring pizzas
          wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

      }

      // Delivering...
      try {
        sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      delivered.run();

    }

  }

}
