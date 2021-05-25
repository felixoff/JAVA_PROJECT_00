package org.felix.myserver.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.model.Users;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class MakePerevodHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Users users = mapper.readValue(exchange.getRequestBody(), Users.class);
        System.out.println(users.toString());
        BusinessLogic business = new BusinessLogic();//вынести наверх
        String responce = null;
        try {
            responce = business.perevod(users);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //               br.close();
        //             isr.close();
        //   String responce = "Add money complete!";
        //   String responce = "finish";
        //       System.out.println("Add money complete!");
        exchange.sendResponseHeaders(200, responce.length());
        OutputStream os = exchange.getResponseBody();
        os.write(responce.getBytes());
        os.close();
    }
}
