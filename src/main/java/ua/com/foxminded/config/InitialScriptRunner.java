package ua.com.foxminded.config;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.foxminded.config.DBConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class InitialScriptRunner {

    private final DBConnection dbConnection;

    public InitialScriptRunner(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public void creat() {
        String sqlProperties = "src/main/resources/init.sql";

        try (Connection connection = dbConnection.getConnection()) {

            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(
                    new FileReader(sqlProperties));
            sr.runScript(reader);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("DB connection failed");
        }
    }
}