package org.felix.myserver.database;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.model.User;
import org.felix.myserver.model.UserForBalance;
import org.felix.myserver.model.UserForCards;
import org.felix.myserver.model.Users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrud implements BaseOper<User> {
    private static String jdbcURL = "jdbc:h2:mem:base;DB_CLOSE_DELAY=-1";
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
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void createClientsTable() throws SQLException {
        String createTableSQL = "create table clients (\r\n" + "  id  INT primary key auto_increment,\r\n" +
                "  client VARCHAR(50) NOT NULL\r\n" + "  );";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void createCardsTable() throws SQLException {
        String createTableSQL = "create table cards (\r\n" + "  id  INT primary key auto_increment,\r\n" +
                "  card_number      DECIMAL(16) NOT NULL,\r\n" + "  bill_id DECIMAL(20) NOT NULL,\r\n" +
                "  FOREIGN KEY (bill_id) REFERENCES BILLS (bill)\r\n" + "  );";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public void insertClient(String client) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO clients" +
                "  (client) VALUES " +
                " (?);";

        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, client);

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void insertBill(String bill, int balance, String client_id) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO bills" +
                "  (bill, balance, client_id) VALUES " +
                " (?,?,?);";
        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
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
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void alterBill() {
        String ALTER_SQL = "ALTER TABLE BILLS " + "ADD UNIQUE (BILL);";

        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }

    public void alterCard() {
        String ALTER_SQL = "ALTER TABLE CARDS " + "ADD UNIQUE (card_number);";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }

    public void dropClients() {
        String ALTER_SQL = "DROP TABLE CLIENTS;";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }

    public void dropBills() {
        String ALTER_SQL = "DROP TABLE BILLS;";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }

    public void dropCards() {
        String ALTER_SQL = "DROP TABLE CARDS;";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(ALTER_SQL)) {

            //      System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }


    @Override
    public void createUser() throws SQLException {
        String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
                "  name varchar(20),\r\n" + "  bill varchar(20),\r\n" + "  card varchar(20),\r\n" +
                "  money int(20)\r\n" + "  );";

        //      System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        String UPDATE_USERS_SQL = "update users set name = ? where id = ?;";
        //   System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, 1);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    @Override
    public void deleteUser(User user) {
        String deleteTableSQL = "delete from users where id = 1";
        //      System.out.println(deleteTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(deleteTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    @Override
    public void readUser(User user) {
        String QUERY = "select id,name,bill,card,money from users where id =?";
        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

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
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }


    @Override
    public String insertCard(User user) {
        String INSERT_CARDS_SQL = "INSERT INTO cards (card_number, bill_id) VALUES " +
                " (?,?);";

        //   System.out.println(INSERT_CARDS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARDS_SQL)) {
            BusinessLogic business = new BusinessLogic();
            String s = business.getAlphaNumericString(16);
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, user.getBill());
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("IncorrectBody");
                return "Incorrect body";
            }
            //     ResultSet rs = preparedStatement.getGeneratedKeys();
            //       while (rs.next())
            //   {
            //       rs.getLong()
            // }
        } catch (SQLException | FileNotFoundException e) {

            // print SQL exception information
            printSQLException((SQLException) e);
        }
        return "Add card complete!";
    }

    @Override
    public String watchCards() {
        String QUERY = "select id,card_number,bill_id from cards";//вынести в начало за метод и логи написать ошибки

        try (Connection connection = getConnection();

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
            printSQLException((SQLException) e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
        return "";
    }

    @Override
    public String checkBalance(User user) {
        String QUERY = "select id,bill,balance,client_id from bills where bill = ?";
        //      ResultSet rs = null;
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            //    System.out.println(preparedStatement);
            preparedStatement.setString(1, user.getBill());
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            if (!rs.next()) {
                return "Enter correct bill";
            }
            //     rs.next();
            int id = rs.getInt("id");
            String name = rs.getString("client_id");
            String bill = rs.getString("bill");
            int balance = rs.getInt("balance");
            UserForBalance tmp = new UserForBalance(id, name, bill, balance);
            Gson gson = new Gson();
            String s = gson.toJson(tmp);
            return (s);
        } catch (SQLException e) {
            printSQLException((SQLException) e);
        }
        return "";
    }

    @Override
    public void insertUser(User user) {
        String INSERT_USERS_SQL = "INSERT INTO users" +
                "  (id, name, bill, card , money) VALUES " +
                " (?, ?, ?, ?, ?);";

        //    System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
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
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public String updateBalance(User user) {
        String UPDATE_USERS_SQL = "update bills set balance = (select balance from bills where bill = ?) + ? where bill = ?";
        //     System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getBill());
            preparedStatement.setInt(2, user.getBalance());
            preparedStatement.setString(3, user.getBill());

            // Step 3: Execute the query or update query
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {

            // print SQL exception information
            //   printSQLException(e);
            System.out.println("IncorrectBody");
            return "Incorrect body";
        }
        return "Add money complete!";
    }

    public String perevod(Users users) {
        String UPDATE_USERS_SQL1 = "update bills set balance = (select balance from bills where bill = ?) - ? where bill = ?";
        String UPDATE_USERS_SQL2 = "update bills set balance = (select balance from bills where bill = ?) + ? where bill = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_USERS_SQL1);
             PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_USERS_SQL2)) {
            preparedStatement1.setString(1, users.getBill_from());
            preparedStatement1.setInt(2, users.getBalance());
            preparedStatement1.setString(3, users.getBill_from());
            preparedStatement2.setString(1, users.getBill_to());
            preparedStatement2.setInt(2, users.getBalance());
            preparedStatement2.setString(3, users.getBill_to());
            BusinessLogic business = new BusinessLogic();
            String input_bill = users.getBill_from();
            User user = new User();
            user.setBill(input_bill);
            String from_bus = business.checkBalance(user);
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(from_bus, User.class);
            if (user.getBalance() - users.getBalance() < 0){
                throw new SQLException();
            }
            int j = preparedStatement2.executeUpdate();
            int i = preparedStatement1.executeUpdate();
            if (i == 0 || j == 0) {
                throw new SQLException();
            }
//        } catch (SQLException e) {
//
//            // print SQL exception information
//            //   printSQLException(e);
//            System.out.println("IncorrectBody");
//            return "Incorrect body";
//        }
//        return "Add money complete!";
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("IncorrectBody");
            return "Incorrect body";
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Transaction complete!";
    }
}
