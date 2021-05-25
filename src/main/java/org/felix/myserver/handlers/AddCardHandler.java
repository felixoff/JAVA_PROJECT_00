package org.felix.myserver.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class AddCardHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String type = exchange.getRequestMethod();
        switch (type) {
            case "POST":
                ObjectMapper mapper = new ObjectMapper();
                User user = mapper.readValue(exchange.getRequestBody(), User.class);
                System.out.println(user.toString());
                BusinessLogic business = new BusinessLogic();
                String responce = null;
                try {
                    responce = business.insertInCard(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //    responce = "Add card complete!";
           //     System.out.println("Add card complete!");
                exchange.sendResponseHeaders(200, responce.length());
                OutputStream os = exchange.getResponseBody();
                os.write(responce.getBytes());
                os.close();
                break;
        }
    }
}
