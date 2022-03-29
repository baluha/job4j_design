package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectingDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("./app.properties");
        config.load();
        String driver = config.value("jdbc.driver");
        String url = config.value("jdbc.url");
        String login = config.value("jdbc.username");
        String password = config.value("jdbc.password");
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
