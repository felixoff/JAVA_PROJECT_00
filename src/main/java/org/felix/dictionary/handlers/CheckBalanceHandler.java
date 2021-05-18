package org.felix.dictionary.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.dictionary.business.BusinessLogic;
import org.felix.dictionary.model.User;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.SQLException;

public class CheckBalanceHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestURI(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            int b;
            StringBuilder buffer = new StringBuilder(512);
            while ((b = br.read()) != -1)
            {
                buffer.append((char)b);
            }
            System.out.println(buffer);
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(buffer.toString(), User.class);
            BusinessLogic business = new BusinessLogic();
            try {
                business.checkBalance(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            br.close();
            isr.close();
        try {
            JSONParser parser = new JSONParser();
            org.json.simple.JSONArray data =
                    (org.json.simple.JSONArray) parser.parse(
                            new FileReader("stream_emp4.json"));

            String responce = data.toJSONString();
            //       System.out.println(responce);
            System.out.println("HelloResp");
            exchange.sendResponseHeaders(200,responce.length());
            OutputStream os = exchange.getResponseBody();
            os.write(responce.getBytes());
            os.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        br.close();
        isr.close();
    }
}
