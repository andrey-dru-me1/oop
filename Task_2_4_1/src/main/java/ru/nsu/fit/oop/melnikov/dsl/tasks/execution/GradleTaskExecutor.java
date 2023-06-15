package ru.nsu.fit.oop.melnikov.dsl.tasks.execution;

import java.io.File;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

public class GradleTaskExecutor {

  private GradleTaskExecutor() {}

  public static boolean execute(String gitName, String taskName) {
    try (ProjectConnection connection =
        GradleConnector.newConnector()
            .forProjectDirectory(new File("repos/" + gitName))
            .connect()) {
      BuildLauncher build = connection.newBuild();
      build.forTasks(taskName + ":compileJava");
      try {
        build.run();
      } catch (BuildException ignored) {
        return false;
      }
      return true;
    }
  }
}
