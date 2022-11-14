package ru.nsu.oop.melnikov.substringsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

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
    }

    @Test
    void testMain() throws IOException {
        String params = "src/test/resources/input.txt pie";
        System.setIn(new ByteArrayInputStream(params.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        SubstringSearch.main(new String[]{});

        Assertions.assertEquals("9", outputStream.toString().trim());
    }

}