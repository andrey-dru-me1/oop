package ru.nsu.fit.oop.melnikov.dsl.table;

import java.util.Collection;
import java.util.Map;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.model.Task;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.GradeTable;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.GroupGradeTable;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.StudentGrades;
import ru.nsu.fit.oop.melnikov.dsl.table.model.grades.TasksStatus;

public class PrettyGradeTablePrinter extends AbstractPrettyPrinter {

  private static final Integer STRING_LENGTH = 70;

  private PrettyGradeTablePrinter() {
    super();
  }

  public static void print(GradeTable gradeTable, Collection<Task> tasks) {
    StringBuilder stringGradeTable = new StringBuilder();
    for (Map.Entry<Group, GroupGradeTable> groupGradeTableEntry : gradeTable.entrySet()) {
      Group group = groupGradeTableEntry.getKey();
      GroupGradeTable groupGradeTable = groupGradeTableEntry.getValue();

      stringGradeTable.append(buildGroupNumberString(group.number()));
      stringGradeTable.append(buildTableHeader(tasks));
      for (Map.Entry<Student, StudentGrades> studentGradesEntry : groupGradeTable.entrySet()) {
        Student student = studentGradesEntry.getKey();
        StudentGrades studentGrades = studentGradesEntry.getValue();

        stringGradeTable.append(buildTableRow(student, studentGrades, tasks));
      }
      stringGradeTable.append("\n\n");
    }
    System.out.println(stringGradeTable);
  }

  private static StringBuilder buildGroupNumberString(Integer groupNumber) {
    int delimiterLength = (STRING_LENGTH - groupNumber.toString().length() - 2) / 2;
    StringBuilder groupNumberString = new StringBuilder();
    groupNumberString
        .append("=".repeat(STRING_LENGTH))
        .append('\n')
        .append("=".repeat(delimiterLength))
        .append(' ')
        .append(groupNumber)
        .append(' ')
        .append("=".repeat(delimiterLength))
        .append('\n')
        .append("=".repeat(STRING_LENGTH))
        .append("\n\n");
    return groupNumberString;
  }

  private static StringBuilder buildTableHeader(Iterable<Task> tasks) {
    StringBuilder tableHeaderString = new StringBuilder();
    tableHeaderString.append('|').append(" ".repeat(NAME_COL_LEN)).append('|');
    for (Task task : tasks) {
      tableHeaderString.append(' ').append(task.name()).append(" |");
    }
    tableHeaderString.append('\n');
    return tableHeaderString;
  }

  private static StringBuilder buildTableRow(
      Student student, StudentGrades studentGrades, Iterable<Task> tasks) {

    StringBuilder tableRowString = new StringBuilder();

    tableRowString
        .append("| ")
        .append(normalizeString(student.name(), NAME_COL_LEN - 2))
        .append(" |");

    int buildSum = 0;
    int styleErrorsSum = 0;
    int docSum = 0;

    for (Task task : tasks) {
      String taskResult = "";

      if (studentGrades.containsKey(task)) {
        TasksStatus tasksStatus = studentGrades.get(task);
        taskResult += Boolean.TRUE.equals(tasksStatus.build()) ? " + " : " - ";
        Integer styleErrorsCount = tasksStatus.style();
        taskResult += normalizeString(styleErrorsCount < 0 ? " " : styleErrorsCount.toString(), 4);
        taskResult += Boolean.TRUE.equals(tasksStatus.doc()) ? " + " : " - ";

        buildSum += Boolean.TRUE.equals(tasksStatus.build()) ? 1 : 0;
        styleErrorsSum += tasksStatus.style();
        docSum += Boolean.TRUE.equals(tasksStatus.build()) ? 1 : 0;
      }

      tableRowString
          .append(' ')
          .append(normalizeString(taskResult, task.name().length()))
          .append(" |");
    }
    tableRowString
        .append(" ")
        .append(normalizeString(String.valueOf(buildSum), 2))
        .append(" ")
        .append(normalizeString(String.valueOf(styleErrorsSum), 4))
        .append(" ")
        .append(normalizeString(String.valueOf(docSum), 2))
        .append(" |\n");

    return tableRowString;
  }
}
