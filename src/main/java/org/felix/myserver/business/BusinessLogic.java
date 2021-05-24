package org.felix.myserver.business;

import org.felix.myserver.database.UserCrud;
import org.felix.myserver.model.User;
import org.felix.myserver.model.Users;
import org.felix.myserver.utils.UserComparator;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BusinessLogic {
    private List<User> users;
    private static UserCrud base;

    public BusinessLogic() throws FileNotFoundException {
        base = new UserCrud();
    }

    public BusinessLogic(String s) throws FileNotFoundException {
        base = new UserCrud();
    }

    public static String getAlphaNumericString(int n) {
        String AlphaNumericString = "0123456789";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static void createTable() throws SQLException {
        base.createUser();
    }

    public static void insertInTable(User user) throws SQLException {
        base.insertUser(user);
    }

    public static String insertInCard(User user) throws SQLException {
        return base.insertCard(user);
    }

    public static String updateBalance(User user) throws SQLException {
        return base.updateBalance(user);
    }

    public static String perevod(Users users) throws SQLException {
        return base.perevod(users);
    }

    public static String showCards() throws SQLException {
        return base.watchCards();
    }

    public static String checkBalance(User user) throws SQLException {
        return base.checkBalance(user);
    }

    public static void deleteInTable(User user) throws SQLException {
        base.deleteUser(user);
    }

    public static void updateInTable(User user) throws SQLException {
        base.updateUser(user);
    }

    public static void readFromTable(User user) throws SQLException {
        base.readUser(user);
    }

    public static void display(List<User> users) {
        System.out.println("display");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static void sorter(List<User> users) {
        System.out.println("sorter 2.1");
        Collections.sort(users, new UserComparator());
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static void maxPop(List<User> cities) {
        System.out.println("maxPop");
    }

    public static void kolNameInReg(List<User> cities) {
        System.out.println("kolNameInReg");

    }

    public List<User> getUsers() {
        return users;
    }

}
