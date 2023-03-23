package ru.nsu.fit.oop.melnikov.data.loader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

public class ResourceJsonFileLoader extends JsonLoader {

  public Pizzeria extractPizzeriaFromFilename(String jsonFilename)
      throws IOException, URISyntaxException {
    return super.extractPizzeriaFromFile(this.getFileFromResources(jsonFilename));
  }

  private File getFileFromResources(String filename) throws URISyntaxException {

    URL url = Objects.requireNonNull(getClass().getClassLoader().getResource(filename));

    return new File(url.toURI());
  }
}
