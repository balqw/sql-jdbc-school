package ua.com.foxminded.config;

import org.h2.Driver;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static final String PROPERTY_SOURCE = "src/main/resources/application.properties";
    private static final String ERR_MESSAGE = "Couldn't create connection, cause: %s";
    private final Properties properties = new Properties();
    private final String URL;
    private final String LOGIN;
    private final String PASSWORD;

    public DBConnection(String dbPrefix) {
        URL = dbPrefix + ".url";
        LOGIN = dbPrefix + ".login";
        PASSWORD = dbPrefix + ".password";
    }

    public Connection getConnection() {
        try (FileInputStream fis = new FileInputStream(PROPERTY_SOURCE)) {

            properties.load(fis);
            String url = properties.getProperty(URL);
            String login = properties.getProperty(LOGIN);
            String password = properties.getProperty(PASSWORD);
            Driver driver = new Driver();
            return DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            throw new RuntimeException(String.format(ERR_MESSAGE, e.getLocalizedMessage()));
        }
    }
}
