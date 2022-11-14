package ru.nsu.oop.melnikov.substringsearch;

import java.io.*;
import java.util.Scanner;

public class SubstringSearch {

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

        System.out.println(search(reader, substring));

        reader.close();
    }

    /**
     * Knut-Morris-Pratt algorithm of substring search
     *
     * @param reader    stream of bytes to search in
     * @param substring substring to search
     * @return index of byte where substring begins
     * @throws IOException when there is no such substring in reader stream
     */
    public static int search(BufferedReader reader, String substring) throws IOException {

        int[] prefixFunction = new int[substring.length()];
        prefixFunction[0] = 0;
        int k = 0;
        for (int i = 1; i < substring.length(); i++) {
            if (substring.charAt(i) == substring.charAt(k)) {
                prefixFunction[i] = ++k;
            } else {
                k = prefixFunction[k];
            }
        }

        int filePos = 0;
        k = 0;
        for (int readResult = reader.read(); k < substring.length(); readResult = reader.read()) {
            if (readResult == -1) {
                throw new EOFException();
            }
            while (k > 0 && (char) readResult != substring.charAt(k)) {
                k = prefixFunction[k - 1];
            }
            if ((char) readResult == substring.charAt(k)) {
                k++;
            }
            filePos++;
        }
        filePos -= substring.length();
        return filePos;
    }

}