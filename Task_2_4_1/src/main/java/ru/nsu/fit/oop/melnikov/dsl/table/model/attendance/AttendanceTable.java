package ru.nsu.fit.oop.melnikov.dsl.table.model.attendance;

import java.util.Collection;
import java.util.HashMap;
import ru.nsu.fit.oop.melnikov.dsl.model.Lesson;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;

public class AttendanceTable extends HashMap<Student, Collection<Lesson>> {}
