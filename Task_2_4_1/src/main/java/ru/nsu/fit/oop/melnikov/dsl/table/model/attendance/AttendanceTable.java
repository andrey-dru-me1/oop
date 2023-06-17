package ru.nsu.fit.oop.melnikov.dsl.table.model.attendance;

import java.util.HashMap;
import ru.nsu.fit.oop.melnikov.dsl.model.Lesson;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;
import ru.nsu.fit.oop.melnikov.dsl.utils.Point;

public class AttendanceTable extends HashMap<Point<Student, Lesson>, Boolean> {}
