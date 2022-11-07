package ru.nsu.oop.melnikov.substringsearch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        String substring = scanner.next();
        Reader reader;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists");
            return;
        }

        scanner = new Scanner(reader);

        System.out.println(scanner.findAll(substring).map(MatchResult::start).toList());

        scanner.close();
        reader.close();
    }

}