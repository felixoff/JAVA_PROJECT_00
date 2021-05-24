package org.felix.myserver;

import org.felix.myserver.business.BusinessLogic;
import org.felix.myserver.database.InitTables;
import org.felix.myserver.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegrTests {
    public IntegrTests() throws MalformedURLException {
    }

    @Test
    public void TestAll() throws IOException, SQLException {
        StartServer server = new StartServer();
        server.start();
        InitTables init = new InitTables();
        init.createTables();
        checkAddCardIntegr();
        checkAddMoneyIntegr();
        checkGetBalanceIntegr();
        checkGetCardsIntegr();
    }

    @Test
    public void checkGetCardsIntegr() throws IOException, SQLException {
//        StartServer server = new StartServer();
//        server.start();
//        InitTables init = new InitTables();
//        init.createTables();
        String url = "http://localhost:8080/getCards";
        String expected = makeGETURL(url);
        BusinessLogic business = new BusinessLogic();
        String actual = business.showCards();
        System.out.println(expected.toString());
        System.out.println(actual);
        Assert.assertEquals(expected.toString(), actual);
    }

    @Test
    public void checkGetBalanceIntegr() throws IOException, SQLException {
//        StartServer server = new StartServer();
//        server.start();
//        InitTables init = new InitTables();
//        init.createTables();
        String url = "http://localhost:8080/check?bill=11111111111111111111";
        String expected = makeGETURL(url);
        User user = new User();
        user.setBill("11111111111111111111");
        BusinessLogic business = new BusinessLogic();
        String actual = business.checkBalance(user);
        System.out.println(expected.toString());
        System.out.println(actual);
        Assert.assertEquals(expected.toString(), actual);
    }

    public String makeGETURL(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine = null;
        StringBuffer expected = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            expected.append(inputLine);
        }
        in.close();
        return (expected.toString());
    }

    public void makePOSTURL(String adress) throws IOException {
        URL url = new URL(adress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json; utf-8");

        String jsonstring = "{\n" +
                "   \"id\":1,\n" +
                "   \"name\":\"\",\n" +
                "   \"bill\":\"11111111111111111111\",\n" +
                "   \"balance\":555\n" +
                "}";
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonstring.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder responce = new StringBuilder();
            String responceLine = null;
            while ((responceLine = br.readLine()) != null) {
                responce.append(responceLine.trim());
            }
            System.out.println(responce.toString());
        }
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
    public void checkAddCardIntegr() throws IOException, SQLException {

//        StartServer server = new StartServer();
//        server.start();
//        InitTables init = new InitTables();
//        init.createTables();
        int expected = makePattern();

        String adress = "http://localhost:8080/addCard";
        makePOSTURL(adress);
        expected++;
        int actual = makePattern();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkAddMoneyIntegr() throws IOException, SQLException {

//        StartServer server = new StartServer();
//        server.start();
//        InitTables init = new InitTables();
//        init.createTables();
        String adress = "http://localhost:8080/addMoney";
        makePOSTURL(adress);
        User user = new User();
        user.setBill("11111111111111111111");
        BusinessLogic business = new BusinessLogic();
        String expected = "{\"id\":1,\"name\":\"petr\",\"bill\":\"11111111111111111111\",\"balance\":5555}";
        System.out.println(expected);
        String actual = business.checkBalance(user);
        System.out.println(actual);
        Assert.assertEquals(expected, actual);
    }
}



