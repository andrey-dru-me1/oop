package ru.nsu.fit.oop.melnikov.dsl.tasks.execution;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import ru.nsu.fit.oop.melnikov.dsl.GlobalConstants;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.TasksStatus;

public class GradleTaskExecutor {

  private GradleTaskExecutor() {}

  public static TasksStatus execute(String gitName, String taskName) throws CheckstyleException {
    File repoDir = new File(GlobalConstants.REPOS_DIR_PATH + '/' + gitName);
    try (ProjectConnection connection =
        GradleConnector.newConnector().forProjectDirectory(repoDir).connect()) {
      BuildLauncher build = connection.newBuild();

      File taskDir = null;
      List<Integer> numbers =
          Arrays.stream(taskName.replaceAll("\\D", " ").trim().split(" "))
              .map(Integer::parseInt)
              .toList();
      for (File file : Objects.requireNonNull(repoDir.listFiles())) {
        Pattern pattern =
            Pattern.compile(
                "[Tt]ask[-_]" + numbers.get(0) + "[-_]" + numbers.get(1) + "[-_]" + numbers.get(2));
        if(pattern.matcher(file.getName()).matches()) {
          taskDir = file;
        }
      }

      if(taskDir == null) {
        return new TasksStatus(false, -1, false);
      }

      boolean buildResult = true;
      int styleResult = checkStyle(getJavaFiles(taskDir).stream().toList());
      boolean docResult = true;

      build.forTasks(taskDir.getName() + ":compileJava");
      try {
        build.run();
      } catch (BuildException ignored) {
        buildResult = false;
      }

      return new TasksStatus(buildResult, styleResult, docResult);
    }
  }

  private static Integer checkStyle(List<File> files) throws CheckstyleException {
    File configFile = new File("../.github/google_checks.xml");
    Configuration configuration =
        ConfigurationLoader.loadConfiguration(
            configFile.getAbsolutePath(),
            new PropertiesExpander(new Properties()),
            ConfigurationLoader.IgnoredModulesOptions.OMIT);

    AuditListenerImpl listener = new AuditListenerImpl();

    Checker checker = new Checker();
    checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
    checker.configure(configuration);
    checker.addListener(listener);
    checker.process(files);
    checker.destroy();
    return listener.getErrorCount();
  }

  private static Collection<File> getJavaFiles(File dir) {
    return FileUtils.listFiles(dir, new String[] {"java"}, true);
  }
}
