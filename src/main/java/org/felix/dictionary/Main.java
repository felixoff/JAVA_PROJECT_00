package org.felix.dictionary;

import com.sun.net.httpserver.HttpServer;
import org.felix.dictionary.handlers.AddCartHandler;
import org.felix.dictionary.handlers.ShowCardsHandler;
import org.felix.dictionary.handlers.CheckBalanceHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.sql.SQLException;

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
        server.createContext("/post", new AddCartHandler());
        server.createContext("/get", new ShowCardsHandler());
        server.createContext("/check", new CheckBalanceHandler());
        server.setExecutor(null);
        server.start();
    }
}
