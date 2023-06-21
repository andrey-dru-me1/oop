package ru.nsu.fit.oop.melnikov.game.snake.model;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

import java.util.List;

public record GameData(Field field, List<Snake> snakes) {}
