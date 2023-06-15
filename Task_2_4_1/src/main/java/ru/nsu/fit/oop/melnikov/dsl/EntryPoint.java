package ru.nsu.fit.oop.melnikov.dsl;

import java.io.IOException;
import java.util.Collection;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.fit.oop.melnikov.dsl.configs.GroupsConfig;
import ru.nsu.fit.oop.melnikov.dsl.configs.ScheduleConfig;
import ru.nsu.fit.oop.melnikov.dsl.configs.TasksConfig;
import ru.nsu.fit.oop.melnikov.dsl.git.ReposDownloader;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.PrettyPrinter;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.GradeTable;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.GroupGradeTable;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.StudentGrades;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.model.Task;
import ru.nsu.fit.oop.melnikov.dsl.tasks.execution.GradleTaskExecutor;

public class EntryPoint {

  private static final String DSL_CONFIG_PATH = "dsl_configs";
  private static final String GROUPS_CONFIG_FILENAME = "groups";
  private static final String TASKS_CONFIG_FILENAME = "tasks";
  private static final String SCHEDULE_CONFIG_FILENAME = "schedule";
  private static final String GROOVY_POSTFIX = ".groovy";

  public static void main(String[] args) throws GitAPIException, IOException, CheckstyleException {
    //    Collection<Group> groups =
    //        GroupsConfig.parse(DSL_CONFIG_PATH + '/' + GROUPS_CONFIG_FILENAME + GROOVY_POSTFIX);
    //    for (Group group : groups) {
    //      ReposDownloader.downloadAll(group.students().stream().map(Student::gitName).toList());
    //    }
    //
    //    Collection<Task> tasks =
    //        TasksConfig.parse(DSL_CONFIG_PATH + '/' + TASKS_CONFIG_FILENAME + GROOVY_POSTFIX);
    //
    //    GradeTable gradeTable = new GradeTable();
    //    for (Group group : groups) {
    //      GroupGradeTable groupGradeTable = new GroupGradeTable();
    //      gradeTable.put(group, groupGradeTable);
    //      for (Student student : group.students()) {
    //        StudentGrades studentGrades = new StudentGrades();
    //        groupGradeTable.put(student, studentGrades);
    //        for (Task task : tasks) {
    //          studentGrades.put(task, GradleTaskExecutor.execute(student.gitName(), task.name()));
    //        }
    //      }
    //    }
    //
    //    PrettyPrinter.print(gradeTable, tasks);

    System.out.println(ScheduleConfig.parse(DSL_CONFIG_PATH + '/' + SCHEDULE_CONFIG_FILENAME + GROOVY_POSTFIX));
  }
}
