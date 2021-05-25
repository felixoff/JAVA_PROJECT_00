package org.felix.myserver.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.felix.myserver.model.User;

import java.io.File;
import java.io.IOException;

public class JacksonStreamingReadExample {

    public User parsing() throws JsonParseException, IOException {

        //create JsonParser object
        JsonParser jsonParser = new JsonFactory().createParser(new File("stream_emp.json"));

        //loop through the tokens
        User user = new User();

        parseJSON(jsonParser, user);

        jsonParser.close();
        //print employee object
        return (user);
    }

    private static void parseJSON(JsonParser jsonParser, User user) throws JsonParseException, IOException {

        //loop through the JsonTokens
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String name = jsonParser.getCurrentName();
            if ("id".equals(name)) {
                jsonParser.nextToken();
                user.setId(jsonParser.getIntValue());
            } else if ("client".equals(name)) {
                jsonParser.nextToken();
                user.setName(jsonParser.getText());
            } else if ("bill".equals(name)) {
                jsonParser.nextToken();
                user.setBill(jsonParser.getText());
            } else if ("card_number".equals(name)) {
                jsonParser.nextToken();
                user.setCard(jsonParser.getText());
            } else if ("balance".equals(name)) {
                jsonParser.nextToken();
                user.setBalance(jsonParser.getIntValue());
            }
        }
    }
}
