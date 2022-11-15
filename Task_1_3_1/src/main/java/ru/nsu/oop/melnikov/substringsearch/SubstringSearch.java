package ru.nsu.oop.melnikov.substringsearch;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class with some substring searching algorithms.
 */
public class SubstringSearch {

    /**
     * Searches substring in reader stream.
     *
     * @param reader    stream of bytes to search in
     * @param substring substring to search
     * @return index of byte where substring begins
     * @throws IOException when there is no such substring in reader stream
     */
    public static int search(BufferedReader reader, String substring) throws IOException {
        return searchKMP(reader, substring);
    }

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

    /**
     * Algorithm of substring search using Z-function.
     *
     * @param reader    stream of bytes to search in
     * @param substring substring to search
     * @return index of byte where substring begins
     * @throws IOException when there is no such substring in reader stream
     */
    public static int searchZ(BufferedReader reader, String substring) throws IOException {

        char[] buf = new char[1024];    //buffer size is set to 1024 bytes
        Arrays.fill(buf, (char) 0);
        if (reader.read(buf, 0, substring.length()) == -1) {
            throw new EOFException();
        }
        int offset = 0;
        while (reader.read(buf, substring.length(), 1024 - substring.length()) != -1) {

            //Z-function implementation
            Integer[] z = new Integer[1024];
            Arrays.fill(z, 0);
            int left = 0;
            int right = 0;
            for (int i = 1; i < 1024 && buf[i] != 0; i++) {
                if (i <= right) {
                    z[i] = Math.min(right - i + 1, z[i - left]);
                }
                while (
                        i + z[i] < 1024
                                && substring.charAt(z[i]) == buf[i + z[i]]
                ) {
                    z[i]++;
                    if (z[i] == substring.length()) {
                        return offset + i;
                    }
                }
                if (i + z[i] - 1 > right) {
                    left = i;
                    right = i + z[i] - 1;
                }
            }

            System.arraycopy(buf, 1024 - substring.length(), buf, 0, substring.length());
            offset += 1024 - substring.length();

        }

        throw new EOFException();

    }

}