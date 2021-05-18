package org.felix.dictionary.business;

import org.felix.dictionary.database.UserCrud;
import org.felix.dictionary.model.User;
import org.felix.dictionary.utils.UserComparator;
import org.felix.dictionary.utils.Parser;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessLogic {
    private List<User> users;
    private static UserCrud base;
    public BusinessLogic() throws FileNotFoundException {
        Parser p = new Parser();
        users = p.parse();
        base = new UserCrud();
    }

    public BusinessLogic(String s) throws FileNotFoundException {
        Parser p = new Parser(s);
        users = p.parse();
        base = new UserCrud();
    }
    public static void createTable() throws SQLException {
        base.createUser();
    }
    public static void insertInTable(User user) throws SQLException {
        base.insertUser(user);
    }
    public static void insertInCard(User user) throws SQLException {
        base.insertCard(user);
    }
    public static void showCards() throws SQLException {
        base.watchCards();
    }
    public static void checkBalance(User user) throws SQLException {
        base.checkBalance(user);
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

    public  List<User> getUsers() {
        return users;
    }

}
