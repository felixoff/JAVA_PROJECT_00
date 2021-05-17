package org.felix.dictionary.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2CreateExample {
    private static final String createTableSQL = "create table cities (\r\n" + "  id  int(3) primary key,\r\n" +
            "  name varchar(20),\r\n" + "  region varchar(20),\r\n" + "  district varchar(20),\r\n" +
            "  population int(20),\r\n" + "  foundation int(20)\r\n" +"  );";

    public static void main(String[] argv) throws SQLException {
        H2CreateExample createTableExample = new H2CreateExample();
        createTableExample.createTable();
    }

    public void createTable() throws SQLException {

        System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }
}


