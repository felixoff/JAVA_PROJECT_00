package org.felix.dictionary;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.felix.dictionary.handlers.AddCardHandler;
import org.felix.dictionary.handlers.AddMoneyHandler;
import org.felix.dictionary.handlers.CheckBalanceHandler;
import org.felix.dictionary.handlers.ShowCardsHandler;
import org.felix.dictionary.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StartServer {
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/addCard", new AddCardHandler());
        server.createContext("/getCards", new ShowCardsHandler());
        server.createContext("/addMoney", new AddMoneyHandler());
        server.createContext("/check", new CheckBalanceHandler());
        server.setExecutor(null);
        server.start();
    }
}