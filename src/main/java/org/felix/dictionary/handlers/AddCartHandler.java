package org.felix.dictionary.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.dictionary.business.BusinessLogic;
import org.felix.dictionary.model.User;

import java.io.*;
import java.sql.SQLException;

public class AddCartHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),"utf-8");
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
//            try(FileWriter writer = new FileWriter("stream_emp.json", false))
//            {
//                // запись всей строки
//                writer.write(buffer.toString());
//                // запись по символам
//                writer.append('\n');
//                writer.flush();
//
//                //         JacksonStreamingReadExample jacks = new JacksonStreamingReadExample();
//                //      User user = jacks.parsing();
                BusinessLogic business = new BusinessLogic();
            try {
                business.insertInCard(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//                try {
//                    JSONObject jsonObj = new JSONObject(buffer.toString());
//                    System.out.println(jsonObj);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            br.close();
            isr.close();
//                BusinessLogic business = new BusinessLogic();
//                try {
//                    business.insertInCard(new User("5;Ринат;DT5;41345858333333335;50505".split(";")));
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
            String responce = "My perfect responce";
            System.out.println("Hello");
            exchange.sendResponseHeaders(200,responce.length());
            OutputStream os = exchange.getResponseBody();
            os.write(responce.getBytes());
            os.close();
        }
}
