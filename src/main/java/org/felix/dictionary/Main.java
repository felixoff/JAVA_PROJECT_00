package org.felix.dictionary;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.felix.dictionary.business.BusinessLogic;
import org.felix.dictionary.jackson.JacksonStreamingReadExample;
import org.felix.dictionary.model.User;
import org.felix.dictionary.utils.Parser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    //    public static void clearScreen()
//    {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }

//    CREATE TABLE CLIENTS
//            (
//                    id     INT primary key auto_increment,
//                    client VARCHAR(50) NOT NULL
//);
//
//    CREATE TABLE BILLS
//            (
//                    id        INT primary key auto_increment,
//                    bill      DECIMAL(20) NOT NULL,
//    balance   INT         NOT NULL,
//    client_id VARCHAR(50) NOT NULL,
//    FOREIGN KEY (client_id) REFERENCES CLIENTS (client)
//            );
//
//    CREATE TABLE CARDS
//            (
//                    id          INT primary key auto_increment,
//                    card_number DECIMAL(16) NOT NULL,
//    bill_id     DECIMAL(20) NOT NULL,
//    FOREIGN KEY (bill_id) REFERENCES BILLS (BILL)
//            );
//
//    ALTER TABLE BILLS
//    ADD UNIQUE (BILL);
//    ALTER TABLE CARDS
//    ADD UNIQUE (card_number);
//
//    INSERT INTO CLIENTS (client)
//    values ('petr');
//
//    INSERT INTO BILLS (bill, balance, client_id)
//    values (11111111111111111111, 3, 'petr');
//
//    INSERT INTO CARDS (card_number, bill_id)
//    values (1111111111111111, 11111111111111111111);

    public static void main(String[] args) throws IOException, SQLException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null);
        server.start();
    }
        static class MyHandler implements HttpHandler {


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
                try(FileWriter writer = new FileWriter("stream_emp.json", false))
                {
                    // запись всей строки
                    writer.write(buffer.toString());
                    // запись по символам
                    writer.append('\n');
                    writer.flush();
                    JacksonStreamingReadExample jacks = new JacksonStreamingReadExample();
                    User user = jacks.parsing();
                    BusinessLogic business = new BusinessLogic();
                    business.insertInCard(user);
                }
                catch(IOException | SQLException ex){

                    System.out.println(ex.getMessage());
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
//                String responce = "My perfect responce";
//                System.out.println("Hello");
//                exchange.sendResponseHeaders(200,responce.length());
//                OutputStream os = exchange.getResponseBody();
//                os.write(responce.getBytes());
//                os.close();
            }
        }
}
