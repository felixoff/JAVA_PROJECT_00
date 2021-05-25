package org.felix.myserver.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.felix.myserver.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonStreamingWriteExample {
    public static void convertToJSON(List<User> users) throws IOException {
        File f = new File("stream_emp2.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(f, users);
        //   JsonGenerator jsonGenerator = new JsonFactory()
        //           .createGenerator(new FileOutputStream("stream_emp2.json"));
        //for pretty printing
        //    jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
        //   jsonGenerator.writeStartArray();// start root object
        //   jsonGenerator.writeArrayFieldStart("phoneNumbers");
        //   for (User user:users) {
        //  mapper.writeValue(new File("stream_emp2.json"), user);

        //   jsonGenerator.writeObjectFieldStart("cards");
        //   jsonGenerator.writeNumberField("id", user.getId());
        //  jsonGenerator.writeStringField("card", user.getCard());
        //  jsonGenerator.writeEndObject();
        //    }
        //    jsonGenerator.writeEndArray();
        //jsonGenerator.writeEndArray(); //closing root object

        //   jsonGenerator.flush();
        //   jsonGenerator.close();
    }

    public static void convertToJSONElem(User user) throws IOException {
        File f = new File("stream_emp4.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(f, user);
    }
}

