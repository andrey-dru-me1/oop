package ru.nsu.fit.oop.melnikov.dsl.tasks.execution;

import java.io.File;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

public class GradleTaskExecutor {

  public static void execute() {
    try (ProjectConnection connection =
        GradleConnector.newConnector()
            .forProjectDirectory(new File("repos/evangelionexpert"))
            .connect()) {
      BuildLauncher build = connection.newBuild();
      build.forTasks("task_1_3_1:build");
      build.setStandardOutput(System.out);
      build.run();
    }
  }
}
