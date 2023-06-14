package ru.nsu.fit.oop.melnikov.dsl;

import java.io.IOException;
import java.util.Collection;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.fit.oop.melnikov.dsl.configs.GroupsConfig;
import ru.nsu.fit.oop.melnikov.dsl.git.ReposDownloader;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.tasks.execution.GradleTaskExecutor;

public class EntryPoint {

  public static void main(String[] args) throws GitAPIException, IOException {
//    Collection<Group> groups = GroupsConfig.parse("dsl_configs/groups.groovy");
//    for (Group group : groups) {
//      ReposDownloader.downloadAll(group.students().stream().map(Student::gitName).toList());
//    }
    GradleTaskExecutor.execute();
  }
}
