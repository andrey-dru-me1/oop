package ru.nsu.oop.melnikov.substringsearch;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public class ZSearch {

    static final int BUFF_SIZE = 1024;

    /**
     * Algorithm of substring search using Z-function.
     *
     * @param reader    stream of bytes to search in
     * @param substring substring to search
     * @return index of byte where substring begins
     * @throws IOException when there is no such substring in reader stream
     */
    public static int searchZ(BufferedReader reader, String substring) throws IOException {

        char[] buf = new char[BUFF_SIZE];    //buffer size is set to BUFF_SIZE bytes
        Arrays.fill(buf, (char) 0);
        if (reader.read(buf, 0, substring.length()) == -1) {
            throw new EOFException();
        }
        int offset = 0;
        while (reader.read(buf, substring.length(), BUFF_SIZE - substring.length()) != -1) {

            //Z-function implementation
            Integer[] z = new Integer[BUFF_SIZE];
            Arrays.fill(z, 0);
            int left = 0;
            int right = 0;
            for (int i = 1; i < BUFF_SIZE && buf[i] != 0; i++) {
                if (i <= right) {
                    z[i] = Math.min(right - i + 1, z[i - left]);
                }
                while (
                        i + z[i] < BUFF_SIZE
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

            System.arraycopy(buf, BUFF_SIZE - substring.length(), buf, 0, substring.length());
            offset += BUFF_SIZE - substring.length();

        }

        throw new EOFException();

    }

}
