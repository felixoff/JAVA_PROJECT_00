package org.felix.dictionary.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2InsertExample {
    private static final String INSERT_USERS_SQL = "INSERT INTO cities" +
            "  (id, name, region, district , population, foundation) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    public static void main(String[] argv) throws SQLException {
        H2InsertExample createTableExample = new H2InsertExample();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Tony");
            preparedStatement.setString(3, "tony@gmail.com");
            preparedStatement.setString(4, "US");
            preparedStatement.setInt(5, 878787);
            preparedStatement.setInt(6, 78877);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
}
