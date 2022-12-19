package ru.nsu.oop.melnikov.substringsearch;

import java.io.BufferedReader;
import java.io.IOException;

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

    private static int searchKMP(BufferedReader reader, String substring) throws IOException {
        return KMPSearch.searchKMP(reader, substring);
    }

    public static int searchZ(BufferedReader reader, String substring) throws IOException {
        return ZSearch.searchZ(reader, substring);
    }

}