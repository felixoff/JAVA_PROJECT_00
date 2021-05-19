package org.felix.dictionary.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.dictionary.business.BusinessLogic;
import org.felix.dictionary.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String responce = s;
            System.out.println("HelloResp");
            exchange.sendResponseHeaders(200,responce.length());
            OutputStream os = exchange.getResponseBody();
            os.write(responce.getBytes());
            os.close();
    }
}
