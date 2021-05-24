package org.felix.myserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.felix.myserver.database.InitTables;
import org.felix.myserver.model.Users;

import java.io.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        InitTables init = new InitTables();
        init.createTables();
        StartServer server = new StartServer();
        server.start();
    }
}
