package ru.nsu.fit.oop.melnikov.dsl.model;

import java.util.Collection;

public record Group(Integer number, Collection<Student> students) {}
