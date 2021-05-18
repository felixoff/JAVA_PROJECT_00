package org.felix.dictionary.database;

import org.felix.dictionary.jackson.JacksonStreamingWriteExample;
import org.felix.dictionary.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2SelectExample {
    private static final String QUERY = "select id,card_number,bill_id from cards";

    public static void main(String[] args) {

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String card_number = rs.getString("card_number");
                String bill_id = rs.getString("bill_id");
          //      System.out.println("{\n  "+id + "," + card_number + "," + bill_id+'\n'+"}");
                User user = new User(id,"",bill_id,card_number,0);
                users.add(user);
            }
            JacksonStreamingWriteExample convert = new JacksonStreamingWriteExample();
            convert.convertToJSON(users);
        } catch (SQLException | IOException e) {
            H2JDBCUtils.printSQLException((SQLException) e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
}
