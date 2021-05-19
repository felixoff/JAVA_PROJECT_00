package org.felix.dictionary;

import com.sun.net.httpserver.HttpServer;
import org.felix.dictionary.database.UserCrud;
import org.felix.dictionary.handlers.AddMoneyHandler;
import org.felix.dictionary.handlers.AddCardHandler;
import org.felix.dictionary.handlers.CheckBalanceHandler;
import org.felix.dictionary.handlers.ShowCardsHandler;
import org.felix.dictionary.model.UserForCards;

import java.io.*;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Main {
//    ALTER TABLE BILLS
//    ADD UNIQUE (BILL);
//    ALTER TABLE CARDS
//    ADD UNIQUE (card_number);
    public static void main(String[] args) throws IOException, SQLException {
        UserCrud database = new UserCrud();
        database.createClientsTable();
       database.createBillsTable();
        database.createCardsTable();
        database.insertClient("petr");
        database.insertBill("11111111111111111111",5,"petr");
        database.alterBill();
        database.alterCard();
        StartServer server = new StartServer();
       server.start();
        database.dropClients();
        database.dropBills();
        database.dropCards();
    }
}
