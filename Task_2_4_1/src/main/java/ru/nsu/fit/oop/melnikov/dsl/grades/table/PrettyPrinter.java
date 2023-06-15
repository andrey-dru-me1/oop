package ru.nsu.fit.oop.melnikov.dsl.grades.table;

import java.util.Collection;
import java.util.Map;

import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.GradeTable;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.GroupGradeTable;
import ru.nsu.fit.oop.melnikov.dsl.grades.table.model.StudentGrades;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.model.Task;

public class PrettyPrinter {

  private static final Integer STRING_LENGTH = 70;

  private PrettyPrinter() {}

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
        .append(groupNumber)
        .append("=".repeat(delimiterLength))
        .append('\n')
        .append("=".repeat(STRING_LENGTH))
        .append("\n\n");
    return groupNumberString;
  }

  private static StringBuilder buildTableHeader(Iterable<Task> tasks) {
    StringBuilder tableHeaderString = new StringBuilder();
    tableHeaderString.append('|').append(" ".repeat(15)).append('|');
    for (Task task : tasks) {
      tableHeaderString.append(' ').append(task.name()).append(" |");
    }
    tableHeaderString.append('\n');
    return tableHeaderString;
  }

  private static StringBuilder buildTableRow(Student student, StudentGrades studentGrades, Iterable<Task> tasks) {
    StringBuilder tableRowString = new StringBuilder();
    tableRowString.append("| ").append(normalizeString(student.name(), 13)).append(" |");
    for (Task task : tasks) {
      String taskResult = " ";
      if(studentGrades.containsKey(task)) {
        taskResult = Boolean.TRUE.equals(studentGrades.get(task)) ? "+" : "-";
      }
      tableRowString.append(' ').append(normalizeString(taskResult, task.name().length())).append(" |");
    }
    tableRowString.append('\n');
    return tableRowString;
  }

  private static String normalizeString(String string, Integer length) {
    if(string.length() >= length) {
      return string.substring(0, length);
    }
    return string + " ".repeat(length - string.length());
  }

}
