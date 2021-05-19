package org.felix.dictionary.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.felix.dictionary.business.BusinessLogic;
import org.felix.dictionary.jackson.JacksonStreamingWriteExample;
import org.felix.dictionary.model.User;
import org.felix.dictionary.model.UserForBalance;
import org.felix.dictionary.model.UserForCards;

public class UserCrud implements BaseOper<User> {
    private static String jdbcURL = "jdbc:h2:~/best1";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void createBillsTable() throws SQLException {
        String createTableSQL = "create table bills (\r\n" + "  id  INT primary key auto_increment,\r\n" +
                "  bill      DECIMAL(20) NOT NULL,\r\n" + "  balance INT NOT NULL,\r\n" +
                "  client_id VARCHAR(50) NOT NULL,\r\n" +
                "  FOREIGN KEY (client_id) REFERENCES CLIENTS (client)\r\n" + "  );";
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
    public void createClientsTable() throws SQLException {
        String createTableSQL = "create table clients (\r\n" + "  id  INT primary key auto_increment,\r\n" +
                "  client VARCHAR(50) NOT NULL\r\n" + "  );";
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

    public void createCardsTable() throws SQLException {
        String createTableSQL = "create table cards (\r\n" + "  id  INT primary key auto_increment,\r\n" +
                "  card_number      DECIMAL(16) NOT NULL,\r\n" + "  bill_id DECIMAL(20) NOT NULL,\r\n" +
                "  FOREIGN KEY (bill_id) REFERENCES BILLS (bill)\r\n" + "  );";
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

    public void insertClient(String client ) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO clients" +
                "  (client) VALUES " +
                " (?);";

        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, client);

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void insertBill(String bill , int balance, String client_id) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO bills" +
                "  (bill, balance, client_id) VALUES " +
                " (?,?,?);";
        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, bill);
            preparedStatement.setInt(2, balance);
            preparedStatement.setString(3, client_id);

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void alterBill()
    {
        String ALTER_SQL = "ALTER TABLE BILLS " + "ADD UNIQUE (BILL);";

        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void alterCard()
    {
        String ALTER_SQL = "ALTER TABLE CARDS " + "ADD UNIQUE (card_number);";
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void dropClients()
    {
        String ALTER_SQL = "DROP TABLE CLIENTS;";
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void dropBills()
    {
        String ALTER_SQL = "DROP TABLE BILLS;";
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void dropCards()
    {
        String ALTER_SQL = "DROP TABLE CARDS;";
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }







    @Override
    public void createUser() throws SQLException {
        String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
                "  name varchar(20),\r\n" + "  bill varchar(20),\r\n" + "  card varchar(20),\r\n" +
                "  money int(20)\r\n" + "  );";

  //      System.out.println(createTableSQL);
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

    @Override
    public void updateUser(User user) throws SQLException {
        String UPDATE_USERS_SQL = "update users set name = ? where id = ?;";
     //   System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, 1);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        String deleteTableSQL = "delete from users where id = 1";
  //      System.out.println(deleteTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(deleteTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

    @Override
    public void readUser(User user) throws SQLException {
        String QUERY = "select id,name,bill,card,money from users where id =?";
        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, 1);
       //     System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String bill = rs.getString("bill");
                String card = rs.getString("card");
                int money = rs.getInt("money");
          //      System.out.println(id + "," + name + "," + bill + "," + card + "," + money);
            }
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }


    @Override
    public void insertCard(User user) throws SQLException {
        String INSERT_CARDS_SQL = "INSERT INTO cards" +
                "  (card_number, bill_id) VALUES " +
                " (?, ?);";

     //   System.out.println(INSERT_CARDS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARDS_SQL)) {
            BusinessLogic business = new BusinessLogic();
            String s = business.getAlphaNumericString(16);
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, user.getBill());

        //    System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException((SQLException) e);
        }

    }

    @Override
    public String watchCards() {
        String QUERY = "select id,card_number,bill_id from cards";

        try (Connection connection = H2JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
     //       System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            List<UserForCards> users = new ArrayList<>();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String card_number = rs.getString("card_number");

                //       System.out.println("{\n  "+id + "," + card_number + "," + bill_id+'\n'+"}");
                UserForCards user = new UserForCards(id, card_number);
                users.add(user);
            }
            Gson gson = new Gson();
            String s = gson.toJson(users);
            return (s);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException((SQLException) e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
        return "";
    }

    @Override
    public String checkBalance(User user) throws SQLException {
        String QUERY = "select id,bill,balance,client_id from bills where bill = ?";
        //      ResultSet rs = null;
        try (Connection connection = H2JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
        //    System.out.println(preparedStatement);
            preparedStatement.setString(1, user.getBill());
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            rs.next();
            int id = rs.getInt("id");
            String name = rs.getString("client_id");
            String bill = rs.getString("bill");
            int balance = rs.getInt("balance");
            UserForBalance tmp = new UserForBalance(id, name, bill, balance);
            Gson gson = new Gson();
            String s = gson.toJson(tmp);
            return (s);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException((SQLException) e);
        }
        return "";
    }
        // Step 4: try-with-resource statement will auto close the connection.
    @Override
    public void insertUser(User user) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO users" +
                "  (id, name, bill, card , money) VALUES " +
                " (?, ?, ?, ?, ?);";

    //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getBill());
            preparedStatement.setString(4, user.getCard());
            preparedStatement.setInt(5, user.getBalance());

      //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void updateBalance(User user) {
        String UPDATE_USERS_SQL = "update bills set balance = (select balance from bills where bill = ?) + ? where bill = ?";
       //     System.out.println(UPDATE_USERS_SQL);
            // Step 1: Establishing a Connection
            try (Connection connection = H2JDBCUtils.getConnection();
                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
                preparedStatement.setString(1, user.getBill());
                preparedStatement.setInt(2, user.getBalance());
                preparedStatement.setString(3, user.getBill());

                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();
            } catch (SQLException e) {

                // print SQL exception information
                H2JDBCUtils.printSQLException(e);
            }
    }
}
