package ru.nsu.fit.oop.melnikov.dsl.table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import ru.nsu.fit.oop.melnikov.dsl.model.Lesson;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.table.model.attendance.AttendanceTable;

public class PrettyAttendancePrinter extends AbstractPrettyPrinter {

  private PrettyAttendancePrinter() {
    super();
  }

  public static void print(AttendanceTable attendanceTable, Collection<Lesson> lessons) {
    StringBuilder stringAttendanceTable = new StringBuilder();

    stringAttendanceTable.append(buildTableHeader(lessons));

    for (Map.Entry<Student, Collection<Lesson>> attendanceTableEntry : attendanceTable.entrySet()) {
      Student student = attendanceTableEntry.getKey();
      Collection<Lesson> visitedLessons = attendanceTableEntry.getValue();
      stringAttendanceTable.append(buildTableRow(student, visitedLessons, lessons));
    }

    System.out.println(stringAttendanceTable);
  }

  private static StringBuilder buildTableHeader(Iterable<Lesson> lessons) {

    DateFormat df = new SimpleDateFormat("dd.MM");

    StringBuilder stringTableHeader = new StringBuilder();
    stringTableHeader.append('|').append(" ".repeat(15)).append('|');
    for (Lesson lesson : lessons) {
      stringTableHeader.append(df.format(lesson.date())).append("|");
    }
    stringTableHeader.append('\n');
    return stringTableHeader;
  }

  private static StringBuilder buildTableRow(
      Student student, Collection<Lesson> visitedLessons, Iterable<Lesson> lessons) {
    StringBuilder stringTableRow = new StringBuilder();
    stringTableRow.append("| ").append(normalizeString(student.name(), 13)).append(" |");
    for (Lesson lesson : lessons) {
      String cell = visitedLessons.contains(lesson) ? " + " : " ";
      stringTableRow
          .append(' ')
          .append(normalizeString(cell, 3))
          .append(" |");
    }
    stringTableRow.append('\n');
    return stringTableRow;
  }
}
