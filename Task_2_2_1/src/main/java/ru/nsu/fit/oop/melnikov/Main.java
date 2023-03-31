package ru.nsu.fit.oop.melnikov;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.fit.oop.melnikov.customer.Customer;
import ru.nsu.fit.oop.melnikov.data.loader.ResourceJsonFileLoader;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

/**
 * Presents interaction with pizzeria: - put "order pepperoni" in System.in if you want to order; -
 * put "exit" in System.in if you want to close pizzeria.
 */
public class Main {

  public static void main(String[] args)
      throws IOException, URISyntaxException {

    Logger log = LoggerFactory.getLogger("Main");

    Pizzeria pizzeria;
    pizzeria = new ResourceJsonFileLoader().extractPizzeriaFromFilename("pizzeria.json");

    Scanner scanner = new Scanner(System.in);

    Collection<Thread> customerThreads = new HashSet<>();

    boolean isLoop = true;
    AtomicReference<Optional<Thread>> closeThread = new AtomicReference<>(Optional.empty());

    while (isLoop && scanner.hasNext()) {

      String command = scanner.next();

      switch (command) {
        case "start", "open" -> pizzeria.start();
        case "order" -> {
          String name = scanner.next();
          Customer customer = new Customer();
          Thread thread = new Thread(() -> customer.orderPizza(pizzeria, name));
          thread.start();
          customerThreads.add(thread);
        }
        case "exit", "evacuate" -> {
          closeThread.get().ifPresent(Thread::interrupt);
          pizzeria.evacuate();
          if (command.equals("exit")) {
            for (Thread customerThread : customerThreads) {
              customerThread.interrupt();
            }
            customerThreads.clear();
            isLoop = false;
          }
        }
        case "stop", "close" -> {
          if (closeThread.get().isEmpty()) {
            closeThread.set(Optional.of(new Thread(() -> {
              pizzeria.close();
              closeThread.set(Optional.empty());
              customerThreads.clear();
            })));
            closeThread.get().ifPresent(Thread::start);
          } else {
            log.warn("Pizzeria is already closing");
          }
        }
        default -> log.error("Unknown command");
      }
    }
  }
}
