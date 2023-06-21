package ru.nsu.fit.oop.melnikov.dsl.tasks.execution.jacoco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

class JacocoParserTest {

  @Test
  void test() {
    File jacocoReportXmlFile = new File("../Task_1_1_1/build/reports/jacoco/test/jacocoTestReport.xml");
    AtomicReference<JacocoParser> parser = new AtomicReference<>();
    Assertions.assertDoesNotThrow(() -> parser.set(JacocoParser.parse(jacocoReportXmlFile)));
    Assertions.assertNotNull(parser.get());
  }
}
