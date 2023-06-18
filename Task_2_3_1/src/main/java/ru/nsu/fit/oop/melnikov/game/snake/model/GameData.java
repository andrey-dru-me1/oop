package ru.nsu.fit.oop.melnikov.game.snake.model;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public record GameData(Field field, Snake snake) {}
