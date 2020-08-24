package dao;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ibatis.common.jdbc.ScriptRunner;

public class CreatorDB {


    private final String sqlProperties = "src/main/resources/properties.sql";
    private final String url = "jdbc:postgresql://localhost/school_db";
    private final String login = "postgres";
    private final String password = "223311";

    public void creat() throws SQLException {

        Connection connection = DriverManager.getConnection(url, login, password);

        try {
            ScriptRunner sr = new ScriptRunner(connection,false,false);
            Reader reader = new BufferedReader(
                    new FileReader(sqlProperties));
            sr.runScript(reader);

        } catch (FileNotFoundException e) {
            System.out.println("File with properties doesnt exists");
        } catch (IOException e) {
            System.out.println("File with properties doesnt exists");
        } finally {
            connection.close();
        }
    }
}