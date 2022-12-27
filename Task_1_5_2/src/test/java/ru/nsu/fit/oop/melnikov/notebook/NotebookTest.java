package ru.nsu.fit.oop.melnikov.notebook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

class NotebookTest {

    @Test
    void test() {

        new File("notebook.json").delete();

        Main.main(new String[]{"notebook", "-add", "lol", "kek"});
        Main.main(new String[]{"notebook", "-add", "Name", "Description"});
        Main.main(new String[]{"notebook", "-rm", "Name"});

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Main.main(new String[]{"notebook", "-show"});

        Assertions.assertEquals("Notebook:\nlol: kek", outputStream.toString().trim());

        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Main.main(new String[]{"notebook", "-show", "10.10.2022 00:00", "10.10.2022 23:59"});

        Assertions.assertEquals("Notebook:", outputStream.toString().trim());
    }

}