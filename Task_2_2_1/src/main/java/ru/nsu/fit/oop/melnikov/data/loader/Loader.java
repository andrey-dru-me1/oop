package ru.nsu.fit.oop.melnikov.data.loader;

import java.io.File;
import java.io.IOException;import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;

public interface Loader {

  Pizzeria extractPizzeriaFromFile(File file) throws IOException;
}
