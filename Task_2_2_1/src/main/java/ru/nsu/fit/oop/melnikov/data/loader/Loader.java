package ru.nsu.fit.oop.melnikov.data.loader;

import java.io.File;
import java.io.IOException;import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

/**
 * Creates pizzeria from some input data.
 */
public interface Loader {

  /**
   * Creates pizzeria from some file.
   *
   * @param file file that contains all the pizzeria data
   * @return newly created pizzeria
   * @throws IOException if there is some error with input file
   */
  Pizzeria extractPizzeriaFromFile(File file) throws IOException;
}
