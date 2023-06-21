package ru.nsu.fit.oop.melnikov.dsl.tasks.execution.jacoco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.File;
import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JacocoParser {
  private static final XmlMapper xmlMapper = new XmlMapper();
  @JacksonXmlProperty(localName = "counter")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Counter> counters;

  public static JacocoParser parse(File jacocoTestReportXml) throws IOException {
    return xmlMapper.readValue(jacocoTestReportXml, JacocoParser.class);
  }

  public List<Counter> getCounters() {
    return counters;
  }

  public static class Counter {
    @JacksonXmlProperty(localName = "type")
    private TestType type;
    @JacksonXmlProperty(localName = "missed")
    private Integer missed;
    @JacksonXmlProperty(localName = "covered")
    private Integer covered;

    public TestType getType() {
      return type;
    }

    public Integer getMissed() {
      return missed;
    }

    public Integer getCovered() {
      return covered;
    }

    public enum TestType {
      INSTRUCTION,
      BRANCH,
      LINE,
      COMPLEXITY,
      METHOD,
      CLASS;
    }
  }
}
