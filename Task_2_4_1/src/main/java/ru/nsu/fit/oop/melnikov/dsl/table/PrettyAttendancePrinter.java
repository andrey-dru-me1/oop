package ru.nsu.fit.oop.melnikov.dsl.table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import ru.nsu.fit.oop.melnikov.dsl.model.Lesson;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.table.model.attendance.AttendanceTable;

public class PrettyAttendancePrinter extends AbstractPrettyPrinter {
  private int resultColumnLength;

  private PrettyAttendancePrinter() {
    super();
  }

  public static void print(AttendanceTable attendanceTable, Collection<Lesson> lessons) {
    StringBuilder stringAttendanceTable = new StringBuilder();

    PrettyAttendancePrinter printer = new PrettyAttendancePrinter();

    stringAttendanceTable.append(printer.buildTableHeader(lessons));

    for (Map.Entry<Student, Collection<Lesson>> attendanceTableEntry : attendanceTable.entrySet()) {
      Student student = attendanceTableEntry.getKey();
      Collection<Lesson> visitedLessons = attendanceTableEntry.getValue();
      stringAttendanceTable.append(printer.buildTableRow(student, visitedLessons, lessons));
    }

    System.out.println(stringAttendanceTable);
  }

  private StringBuilder buildTableHeader(Collection<Lesson> lessons) {

    DateFormat df = new SimpleDateFormat("dd.MM");

    StringBuilder stringTableHeader = new StringBuilder();
    stringTableHeader.append('|').append(" ".repeat(NAME_COL_LEN)).append('|');
    for (Lesson lesson : lessons) {
      stringTableHeader.append(df.format(lesson.date())).append("|");
    }
    String cell = " Result (" + lessons.size() + ") ";
    resultColumnLength = cell.length();
    stringTableHeader.append(cell).append("|\n");
    return stringTableHeader;
  }

  private StringBuilder buildTableRow(
      Student student, Collection<Lesson> visitedLessons, Collection<Lesson> lessons) {
    StringBuilder stringTableRow = new StringBuilder();
    stringTableRow
        .append("| ")
        .append(normalizeString(student.name(), NAME_COL_LEN - 2))
        .append(" |");
    for (Lesson lesson : lessons) {
      String cell = visitedLessons.contains(lesson) ? " + " : " ";
      stringTableRow.append(' ').append(normalizeString(cell, 3)).append(" |");
    }
    stringTableRow
        .append(' ')
        .append(normalizeString(String.valueOf(visitedLessons.size()), resultColumnLength - 1))
        .append("|\n");
    return stringTableRow;
  }
}
