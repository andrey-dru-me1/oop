package ru.nsu.oop.melnikov.substringsearch;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;

public class KMPSearch {

    /**
     * Knut-Morris-Pratt algorithm of substring search.
     *
     * @param reader    stream of bytes to search in
     * @param substring substring to search
     * @return index of byte where substring begins
     * @throws IOException when there is no such substring in reader stream
     */
    public static int searchKMP(BufferedReader reader, String substring) throws IOException {

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
