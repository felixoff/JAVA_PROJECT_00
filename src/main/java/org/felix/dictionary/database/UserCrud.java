package org.felix.dictionary.database;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import org.felix.dictionary.model.User;
public class UserCrud implements BaseOper<User>{
    private static String jdbcURL = "jdbc:h2:~/best";
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

    @Override
    public void createUser() throws SQLException{
           String createTableSQL = "create table users (\r\n" + "  id  int(3) primary key,\r\n" +
                "  name varchar(20),\r\n" + "  bill varchar(20),\r\n" + "  card varchar(20),\r\n" +
                "  money int(20)\r\n" +"  );";

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

    @Override
    public void updateUser(User user) throws SQLException{
        String UPDATE_USERS_SQL = "update users set name = ? where id = ?;";
        System.out.println(UPDATE_USERS_SQL);
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
    public void deleteUser(User user) throws SQLException{
        String deleteTableSQL = "delete from users where id = 1";
        System.out.println(deleteTableSQL);
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
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String bill = rs.getString("bill");
                    String card = rs.getString("card");
                    int money = rs.getInt("money");
                    System.out.println(id + "," + name + "," + bill + "," + card + "," + money);
                }
            } catch (SQLException e) {
                H2JDBCUtils.printSQLException(e);
            }
            // Step 4: try-with-resource statement will auto close the connection.
        }
    static String getAlphaNumericString(int n)
    {
        String AlphaNumericString ="0123456789";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void insertCard(User user) throws SQLException {
        String INSERT_CARDS_SQL = "INSERT INTO cards" +
                "  (card_number, bill_id) VALUES " +
                " (?, ?);";

        System.out.println(INSERT_CARDS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARDS_SQL)) {

            String s = getAlphaNumericString(16);
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, user.getBill());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }

    }

    @Override
    public void insertUser(User user) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO users" +
                "  (id, name, bill, card , money) VALUES " +
                " (?, ?, ?, ?, ?);";

        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getBill());
            preparedStatement.setString(4, user.getCard());
            preparedStatement.setInt(5, user.getBalance());

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
