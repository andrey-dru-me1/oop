package ru.nsu.fit.oop.melnikov.courier;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.function.Consumer;

public class Courier {

  private final int trunkSize;

  public Courier(int trunkSize) {
    this.trunkSize = trunkSize;
  }

  public void work(Consumer<Integer> giveMePizzas) {

    while (!currentThread().isInterrupted()) {

      giveMePizzas.accept(trunkSize);

      System.out.println("Courier is on the way");

      // Delivering pizzas
      for (int i = 0; i < trunkSize; i++) {

        try {
          sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        System.out.println("Pizza's delivered!");
      }

      try {
        sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println("Courier's back!");

    }

  }

}
