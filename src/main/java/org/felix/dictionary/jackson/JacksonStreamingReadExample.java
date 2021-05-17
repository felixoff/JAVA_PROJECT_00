package org.felix.dictionary.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import  org.felix.dictionary.model.User;

public class JacksonStreamingReadExample {

    public User parsing() throws JsonParseException, IOException {

        //create JsonParser object
        JsonParser jsonParser = new JsonFactory().createParser(new File("stream_emp.json"));

        //loop through the tokens
        User user = new User("6;Олег;DT6;41345858333333336;506".split(";"));

        parseJSON(jsonParser, user);

        jsonParser.close();
        //print employee object
        System.out.println("Employee Object\n\n" + user);
        return(user);
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
