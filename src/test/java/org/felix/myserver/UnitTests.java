package org.felix.myserver;

import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.database.InitTables;
import org.felix.myserver.model.User;
import org.junit.Assert;
import org.junit.Test;//пакетЮпитер
import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.BeforeEach;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.junit.Assert.assertEquals;


public class UnitTests {

//    @BeforeAll
//    public void before() throws IOException, SQLException {
//        InitTables init = new InitTables();
//        init.createTables();
//    }

    @Test
    public void TestAll() throws IOException, SQLException {
        InitTables init = new InitTables();
        init.createTables();
        checkGetCards();
        checkAddCard();
        checkGetBalance();
        checkAddMoney();
    }

    @Test
    public void checkGetCards() throws IOException, SQLException {
//        InitTables init = new InitTables();
//        init.createTables();
        String expected = "[{\"id\":1,\"card\":\"5555555555555555\"},{\"id\":2,\"card\":\"5555555555555595\"}]";
        BusinessLogic business = new BusinessLogic();
        String actual = business.showCards();
        Assert.assertEquals(expected.toString(), actual);
    }

    @Test
    public void checkGetBalance() throws IOException, SQLException {
//        InitTables init = new InitTables();
//        init.createTables();
        String expected = "{\"id\":1,\"name\":\"petr\",\"bill\":\"11111111111111111111\",\"balance\":5000}";
        User user = new User();
        user.setBill("11111111111111111111");
        BusinessLogic business = new BusinessLogic();
        String actual = business.checkBalance(user);
        System.out.println(expected.toString());
        System.out.println(actual);
        Assert.assertEquals(expected.toString(), actual);
    }

    int makePattern() throws FileNotFoundException, SQLException {
        BusinessLogic business = new BusinessLogic();
        String s = business.showCards();
        final Pattern colNumb = Pattern.compile("\\d{16}");
        Matcher metcher = colNumb.matcher(s);
        int pos = 0;
        int actual = 0;
        while (metcher.find(pos)) {
            pos = metcher.end();
            actual++;
        }
        return actual;
    }

    @Test
    public void checkAddCard() throws IOException, SQLException {

//        InitTables init = new InitTables();
//        init.createTables();
        User user = new User();
        user.setBill("11111111111111111111");
        BusinessLogic business = new BusinessLogic();
        int expected = makePattern();
        expected++;
        business.insertInCard(user);
        int actual = makePattern();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkAddMoney() throws IOException, SQLException {
//        InitTables init = new InitTables();
//        init.createTables();
        User user = new User();
        user.setBill("11111111111111111111");
        user.setBalance(555);
        BusinessLogic business = new BusinessLogic();
        String expected = "{\"id\":1,\"name\":\"petr\",\"bill\":\"11111111111111111111\",\"balance\":5555}";
        System.out.println(expected);
        business.updateBalance(user);
        String actual = business.checkBalance(user);
        System.out.println(actual);
        Assert.assertEquals(expected, actual);
    }

}



