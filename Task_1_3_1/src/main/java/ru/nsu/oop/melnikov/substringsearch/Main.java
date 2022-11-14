package ru.nsu.oop.melnikov.substringsearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        String substring = scanner.next();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists");
            return;
        }

        System.out.println(SubstringSearch.search(reader, substring));

        reader.close();
    }
}
