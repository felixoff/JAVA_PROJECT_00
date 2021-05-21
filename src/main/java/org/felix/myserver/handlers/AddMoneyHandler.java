package org.felix.myserver.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class AddMoneyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
//        String type = exchange.getRequestMethod();
//                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
//                BufferedReader br = new BufferedReader(isr);
//                int b;
//                StringBuilder buffer = new StringBuilder(512);
//                while ((b = br.read()) != -1) {
//                    buffer.append((char) b);
//                }
//                System.out.println(buffer);
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(exchange.getRequestBody(), User.class);
        System.out.println(user.toString());
        BusinessLogic business = new BusinessLogic();
        try {
            business.updateBalance(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //               br.close();
        //             isr.close();
     //   String responce = "Add money complete!";
        String responce = "finish";
 //       System.out.println("Add money complete!");
        exchange.sendResponseHeaders(200, responce.length());
        OutputStream os = exchange.getResponseBody();
        os.write(responce.getBytes());
        os.close();
    }
}
