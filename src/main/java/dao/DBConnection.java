package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private  String url;
    private  String login;
    private String password;
    private Connection connection;

    FileInputStream fis;
    Properties properties = new Properties();

    public Connection getConnection() {
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            properties.load(fis);
            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");

        } catch (IOException e) {
            System.out.println("cannot find properties");
        }
         finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connection successful");
            return connection;
        } catch (RuntimeException | SQLException e) {
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
