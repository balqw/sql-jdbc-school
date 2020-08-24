package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryDB {
    private final String url = "jdbc:postgresql://localhost/school_db";
    private final String login = "postgres";
    private final String password = "223311";
    private Connection connection;

    public Connection getConnection(){

        try {
             connection = DriverManager.getConnection(url,login,password);
            System.out.println("Connection successful");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;

    }

    public void close() throws SQLException {
        if (connection!=null) {
            connection.close();
        }
    }


}
