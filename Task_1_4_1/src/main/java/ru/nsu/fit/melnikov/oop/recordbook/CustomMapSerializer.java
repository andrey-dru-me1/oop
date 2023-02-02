package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class CustomMapSerializer extends JsonSerializer<Map<Subject, Semester.Grade>> {

    @Override
    public void serialize(Map<Subject, Semester.Grade> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        value.forEach((a, b) -> {
            try {
                gen.writeStartObject();
                gen.writeFieldName("key");
                gen.writeObject(a);
                gen.writeFieldName("value");
                gen.writeObject(b);
                gen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gen.writeEndArray();
    }
}
