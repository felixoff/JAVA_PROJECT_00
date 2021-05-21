package org.felix.myserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.myserver.business.BusinessLogic;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class ShowCardsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        BusinessLogic business = new BusinessLogic();
        String s = null;
        try {
            s = business.showCards();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String responce = s;
        System.out.println("HelloResp");
        exchange.sendResponseHeaders(200, responce.length());
        OutputStream os = exchange.getResponseBody();
        os.write(responce.getBytes());
        os.close();
    }
}
