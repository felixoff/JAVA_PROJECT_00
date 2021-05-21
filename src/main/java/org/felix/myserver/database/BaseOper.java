package org.felix.myserver.database;

import java.sql.SQLException;

public interface BaseOper<User> {
    public void createUser() throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    void readUser(User user) throws SQLException;

    void insertUser(User user) throws SQLException;

    String insertCard(User user) throws SQLException;

    String watchCards();

    String checkBalance(User user) throws SQLException;

    void updateBalance(User user);
}
