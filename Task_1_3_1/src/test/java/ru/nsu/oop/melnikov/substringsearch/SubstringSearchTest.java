package ru.nsu.oop.melnikov.substringsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

class SubstringSearchTest {

    @Test
    void test() throws IOException {
        Assertions.assertEquals(
                9,
                SubstringSearch.search(
                        new BufferedReader(new FileReader("src/test/resources/input.txt")), "pie"
                )
        );
        Assertions.assertThrowsExactly(
                EOFException.class,
                () -> SubstringSearch.search(
                        new BufferedReader(new FileReader("src/test/resources/input.txt")),
                        "Apple"
                )
        );
        Assertions.assertEquals(
                17,
                SubstringSearch.search(
                        new BufferedReader(new FileReader("src/test/resources/input.txt")), "pispit"
                )
        );
        Assertions.assertEquals(
                17,
                SubstringSearch.searchZ(
                        new BufferedReader(new FileReader("src/test/resources/input.txt")), "pispit"
                )
        );
        Assertions.assertEquals(
                6556,
                SubstringSearch.searchZ(
                        new BufferedReader(new FileReader("src/test/resources/input.txt")), "preadv() function"
                )
        );
        Assertions.assertEquals(
                6556,
                SubstringSearch.search(
                        new BufferedReader(new FileReader("src/test/resources/input.txt")), "preadv() function"
                )
        );
    }

}