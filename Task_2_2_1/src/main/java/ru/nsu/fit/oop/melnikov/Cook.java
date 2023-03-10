package ru.nsu.fit.oop.melnikov;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;import org.slf4j.Logger;import org.slf4j.LoggerFactory;

public class Cook {

  private static final Logger logger = LoggerFactory.getLogger(Cook.class);
  private final int experience;


  @JsonCreator
  public Cook(@JsonProperty("experience") int experience) {
    this.experience = experience;
  }

  @SuppressWarnings("BusyWait")
  public void work(Runnable iAmFree, Runnable stayAtWareHouse) {

    while (!currentThread().isInterrupted()) {

      iAmFree.run();

      // Cooking...
//      System.out.println("Cook has started cooking");
      logger.info("Start cooking");
      try {
        sleep(5000 / experience);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

//      System.out.println("Pizza has cooked");
      logger.info("Finish cooking");

      stayAtWareHouse.run();

//      System.out.println("Pizza is in warehouse");
      logger.info("Put in warehouse");

    }

  }

}
