package ru.nsu.oop.melnikov.substringsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

class MainTest {

    @Test
    void testMain() throws IOException {
        String params = "src/test/resources/input.txt pie";
        System.setIn(new ByteArrayInputStream(params.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Main.main(new String[]{});

        Assertions.assertEquals("9", outputStream.toString().trim());
    }
}