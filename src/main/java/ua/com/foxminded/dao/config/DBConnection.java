package ua.com.foxminded.dao.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private Connection connection;
    FileInputStream fis;
    Properties properties = new Properties();

    public Connection getConnection()  {
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            properties.load(fis);
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            fis.close();
            connection = DriverManager.getConnection(url, login, password);
        } catch (IOException|SQLException | RuntimeException e) {
            System.out.println("connection doesn`t exists");
        }
            return connection;
    }

        public void close() throws SQLException {
        if (connection!=null) {
            connection.close();
        }
    }

}
