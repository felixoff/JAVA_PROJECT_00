package org.felix.myserver;

import org.felix.myserver.database.InitTables;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        InitTables init = new InitTables();
        init.createTables();
        StartServer server = new StartServer();
        server.start();
    }
}
