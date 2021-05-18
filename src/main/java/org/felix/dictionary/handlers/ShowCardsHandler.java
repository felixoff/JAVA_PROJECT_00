package org.felix.dictionary.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.felix.dictionary.business.BusinessLogic;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class ShowCardsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        BusinessLogic business = new BusinessLogic();
        try {
            business.showCards();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //   InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),"utf-8");
        //                try {
//                    JSONObject jsonObj = new JSONObject(buffer.toString());
//                    System.out.println(jsonObj);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
        //    isr.close();
//                BusinessLogic business = new BusinessLogic();
//                try {
//                    business.insertInCard(new User("5;Ринат;DT5;41345858333333335;50505".split(";")));
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
        try {
            JSONParser parser = new JSONParser();
            org.json.simple.JSONArray data =
                    (org.json.simple.JSONArray) parser.parse(
                            new FileReader("stream_emp2.json"));
            String json1 = data.toJSONString();
            String responce = json1;
            System.out.println("HelloResp");
            exchange.sendResponseHeaders(200,responce.length());
            OutputStream os = exchange.getResponseBody();
            os.write(responce.getBytes());
            os.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
