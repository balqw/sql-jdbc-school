package ua.com.foxminded.domain;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import com.ibatis.common.jdbc.ScriptRunner;
import ua.com.foxminded.dao.config.DBConnection;

public class InitialisationDB {

    DBConnection dbConnection = new DBConnection();


    public void creat() {
        String sqlProperties = "src/main/resources/init.sql";


        try (Connection connection = dbConnection.getConnection()){

            ScriptRunner sr = new ScriptRunner(connection,false,false);
            Reader reader = new BufferedReader(
                    new FileReader(sqlProperties));
            sr.runScript(reader);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("DB connection failed");
        }
    }
}