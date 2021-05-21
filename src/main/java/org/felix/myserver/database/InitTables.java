package org.felix.myserver.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitTables {
    private String createFromSQL;

    public void createTables() throws SQLException, IOException {
        ReadFile readfile = new ReadFile();
        String creator = readfile.readFile("src/main/resources/initTable.sql", StandardCharsets.UTF_8);
        createFromSQL = creator;
        System.out.println(createFromSQL);
        try (Connection connection = UserCrud.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(createFromSQL);
            System.out.println("its ok");
        } catch (SQLException e) {
            UserCrud.printSQLException((SQLException) e);
        }
    }
}
