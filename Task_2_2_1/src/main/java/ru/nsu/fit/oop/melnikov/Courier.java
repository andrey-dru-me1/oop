package ru.nsu.fit.oop.melnikov;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Courier {

  private static final Logger logger = LoggerFactory.getLogger(Courier.class);
  private final int trunkSize;

  @JsonCreator
  public Courier(@JsonProperty("trunkSize") int trunkSize) {
    this.trunkSize = trunkSize;
  }

  @SuppressWarnings("BusyWait")
  public void work(Consumer<Integer> giveMePizzas) {

    while (!currentThread().isInterrupted()) {

      giveMePizzas.accept(trunkSize);

      logger.info("On the way");

      // Delivering pizzas
      for (int i = 0; i < trunkSize; i++) {

        try {
          sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        logger.info("Delivered");
      }

      try {
        sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      logger.info("Back");

    }

  }

}
