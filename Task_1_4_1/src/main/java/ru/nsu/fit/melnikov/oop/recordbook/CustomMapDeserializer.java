package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomMapDeserializer extends JsonDeserializer<Map<Subject, Semester.Grade>> {

    @Override
    public Map<Subject, Semester.Grade> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        Map<Subject, Semester.Grade> result = new HashMap<>();
        JsonNode node = p.getCodec().readTree(p);
        for (JsonNode grade : node) {
            ObjectMapper mapper = new ObjectMapper();
            result.put(mapper.readValue(grade.get("key").toString(), Subject.class), mapper.readValue(grade.get("value").toString(), Semester.Grade.class));
        }
        return result;
    }
}
