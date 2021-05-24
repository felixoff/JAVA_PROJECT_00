package org.felix.myserver;

import com.sun.net.httpserver.HttpServer;
import org.felix.myserver.handlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class StartServer {
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/addCard", new AddCardHandler());
        server.createContext("/getCards", new ShowCardsHandler());
        server.createContext("/addMoney", new AddMoneyHandler());
        server.createContext("/check", new CheckBalanceHandler());
        server.createContext("/perevod", new MakePerevodHandler());
        server.setExecutor(null);
        server.start();
    }
}