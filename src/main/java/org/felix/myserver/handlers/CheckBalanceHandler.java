package org.felix.myserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class CheckBalanceHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] params = exchange.getRequestURI().getQuery().split("=");
        String input_bill = params[1];
        BusinessLogic business = new BusinessLogic();
        User user = new User();
        user.setBill(input_bill);
        String s = null;
        try {
            s = business.checkBalance(user);
        }
        catch ( SQLException throwables) {
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
