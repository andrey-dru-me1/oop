package ru.nsu.fit.oop.melnikov.data.loader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

/**
 * Creates new pizzeria from json file taking place in project resources.
 */
public class ResourceJsonFileLoader extends JsonLoader {

  /**
   * Creates pizza from file specified by filename.
   *
   * @param jsonFilename name of json file
   * @return newly created pizzeria
   * @throws IOException if there is some error during file reading
   * @throws URISyntaxException if there is some error during file getting
   */
  public Pizzeria extractPizzeriaFromFilename(String jsonFilename)
      throws IOException, URISyntaxException {
    return super.extractPizzeriaFromFile(this.getFileFromResources(jsonFilename));
  }

  /**
   * @param filename name of file
   * @return file with filename
   * @throws URISyntaxException if there is some error during file getting
   */
  private @NotNull File getFileFromResources(String filename) throws URISyntaxException {

    URL url = Objects.requireNonNull(getClass().getClassLoader().getResource(filename));

    return new File(url.toURI());
  }
}
