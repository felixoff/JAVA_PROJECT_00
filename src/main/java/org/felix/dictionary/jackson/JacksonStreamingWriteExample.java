package org.felix.dictionary.jackson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import  org.felix.dictionary.model.User;

public class JacksonStreamingWriteExample {
    public static void main(String[] args) throws IOException {
        User user = new User("6;Олег;DT6;41345858333333336;506".split(";"));

        JsonGenerator jsonGenerator = new JsonFactory()
                .createGenerator(new FileOutputStream("stream_emp.json"));
        //for pretty printing
        jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());

        jsonGenerator.writeStartObject(); // start root object
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeStringField("bill", user.getBill());
        jsonGenerator.writeStringField("card", user.getCard());
        jsonGenerator.writeNumberField("money", user.getBalance());
        jsonGenerator.writeEndObject(); //closing root object

        jsonGenerator.flush();
        jsonGenerator.close();
    }
}

