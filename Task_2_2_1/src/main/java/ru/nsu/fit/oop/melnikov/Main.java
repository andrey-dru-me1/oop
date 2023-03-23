package ru.nsu.fit.oop.melnikov;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.fit.oop.melnikov.data.loader.ResourceJsonFileLoader;
import ru.nsu.fit.oop.melnikov.pizzeria.Customer;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

public class Main {

  public static void main(String[] args) {

    Logger log = LoggerFactory.getLogger("Main");

    Pizzeria pizzeria;
    try {
      pizzeria = new ResourceJsonFileLoader().extractPizzeriaFromFilename("pizzeria.json");
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
    pizzeria.start();

    Scanner scanner = new Scanner(System.in);

    Collection<Thread> customerThreads = new HashSet<>();

    boolean isLoop = true;
    while (isLoop) {

      String command = scanner.next();

      switch (command) {
        case "order" -> {
          String name = scanner.next();
          Customer customer = new Customer();
          Thread thread = new Thread(() -> customer.orderPizza(pizzeria, name));
          thread.start();
          customerThreads.add(thread);
        }
        case "exit" -> {
          isLoop = false;
          pizzeria.close();
          for (Thread customerThread : customerThreads) {
            customerThread.interrupt();
          }
        }
        default -> log.error("Unknown command");
      }
    }
  }
}
