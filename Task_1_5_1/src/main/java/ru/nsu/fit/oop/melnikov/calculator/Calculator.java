package ru.nsu.fit.oop.melnikov.calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private static final Stack<Operations> operations = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Double result = null;
        String buf;
        boolean loop = true;
        while (loop) {
            buf = scanner.next();
            switch (buf) {
                case "+" -> operations.push(Operations.PLUS);
                case "-" -> operations.push(Operations.MINUS);
                case "*" -> operations.push(Operations.MULTIPLY);
                case "/" -> operations.push(Operations.DIVIDE);
                case "pow" -> operations.push(Operations.POW);
                case "sin" -> operations.push(Operations.SIN);
                case "cos" -> operations.push(Operations.COS);
                case "log" -> operations.push(Operations.LOG);
                case "sqrt" -> operations.push(Operations.SQRT);
                default -> {
                    result = Double.parseDouble(buf);
                    loop = false;
                }
            }
        }
        Double number = null;
        while (!operations.empty()) {
            Operations operation = operations.pop();
            switch (operation) {
                case PLUS, MINUS, DIVIDE, MULTIPLY, POW -> number = Double.parseDouble(scanner.next());
            }
            result = switch (operation) {
                case PLUS -> result + number;
                case MINUS -> result - number;
                case DIVIDE -> result / number;
                case MULTIPLY -> result * number;
                case POW -> Math.pow(result, number);
                case COS -> Math.cos(result);
                case LOG -> Math.log(result);
                case SIN -> Math.sin(result);
                case SQRT -> Math.sqrt(result);
            };
        }
        scanner.close();
        System.out.println(result);

    }

    private enum Operations {
        PLUS, MINUS, MULTIPLY, DIVIDE, SIN, COS, LOG, POW, SQRT
    }

}