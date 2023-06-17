package ru.nsu.fit.oop.melnikov.dsl;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import java.io.IOException;
import java.util.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import ru.nsu.fit.oop.melnikov.dsl.configs.GroupsConfig;
import ru.nsu.fit.oop.melnikov.dsl.configs.ScheduleConfig;
import ru.nsu.fit.oop.melnikov.dsl.configs.TasksConfig;
import ru.nsu.fit.oop.melnikov.dsl.git.ReposDownloader;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Lesson;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.model.Task;
import ru.nsu.fit.oop.melnikov.dsl.table.PrettyAttendancePrinter;
import ru.nsu.fit.oop.melnikov.dsl.table.PrettyGradeTablePrinter;
import ru.nsu.fit.oop.melnikov.dsl.table.model.attendance.AttendanceTable;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.GradeTable;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.GroupGradeTable;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.StudentGrades;
import ru.nsu.fit.oop.melnikov.dsl.tasks.execution.GradleTaskExecutor;

public class EntryPoint {

  private static final String DSL_CONFIG_PATH = "dsl_configs";
  private static final String GROUPS_CONFIG_FILENAME = "groups";
  private static final String TASKS_CONFIG_FILENAME = "tasks";
  private static final String SCHEDULE_CONFIG_FILENAME = "schedule";
  private static final String GROOVY_POSTFIX = ".groovy";

  public static void main(String[] args) throws GitAPIException, IOException, CheckstyleException {
    Collection<Group> groups =
        GroupsConfig.parse(DSL_CONFIG_PATH + '/' + GROUPS_CONFIG_FILENAME + GROOVY_POSTFIX);

    for (Group group : groups) {
      ReposDownloader.downloadAndBindAll(group.students());
    }

    Collection<Task> tasks =
        TasksConfig.parse(DSL_CONFIG_PATH + '/' + TASKS_CONFIG_FILENAME + GROOVY_POSTFIX);

    GradeTable gradeTable = generateGradeTable(groups, tasks);

    Collection<Lesson> lessons =
        ScheduleConfig.parse(DSL_CONFIG_PATH + '/' + SCHEDULE_CONFIG_FILENAME + GROOVY_POSTFIX);

    AttendanceTable attendanceTable = generateAttendanceTable(groups, lessons);

    PrettyGradeTablePrinter.print(gradeTable, tasks);

    PrettyAttendancePrinter.print(attendanceTable, lessons);
  }

  private static AttendanceTable generateAttendanceTable(
      Collection<Group> groups, Collection<Lesson> lessons) throws GitAPIException {
    AttendanceTable attendanceTable = new AttendanceTable();

    for (Group group : groups) {
      for (Student student : group.students()) {
        attendanceTable.put(student, new ArrayList<>());
      }
    }

    for (Lesson lesson : lessons) {
      Date since = lesson.date();
      Date until;

      Calendar c = Calendar.getInstance();
      c.setTime(since);
      c.add(Calendar.DATE, 7);

      until = c.getTime();

      RevFilter between = CommitTimeRevFilter.between(since, until);

      for (Group group : groups) {

        for (Student student : group.students()) {
          Iterable<RevCommit> commits = student.getGit().log().setRevFilter(between).call();

          if (commits.iterator().hasNext()) {
            attendanceTable.get(student).add(lesson);
          }
        }
      }
    }
    return attendanceTable;
  }

  private static GradeTable generateGradeTable(Collection<Group> groups, Collection<Task> tasks)
      throws CheckstyleException {
    GradeTable gradeTable = new GradeTable();
    for (Group group : groups) {
      GroupGradeTable groupGradeTable = new GroupGradeTable();
      gradeTable.put(group, groupGradeTable);
      for (Student student : group.students()) {
        StudentGrades studentGrades = new StudentGrades();
        groupGradeTable.put(student, studentGrades);
        for (Task task : tasks) {
          studentGrades.put(task, GradleTaskExecutor.execute(student.gitName(), task.name()));
        }
      }
    }
    return gradeTable;
  }
}
