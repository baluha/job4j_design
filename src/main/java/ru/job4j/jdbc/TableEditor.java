package ru.job4j.jdbc;


import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        String url = properties.getProperty("jdbc.url");
        String login = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void maker(String sql) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = String.format("create table if not exists %s(%s)", tableName,
                "id serial primary key");
        maker(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s", tableName);
        maker(sql);
        System.out.println("Table was deleted");
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = (String.format("alter table %s add %s %s null", tableName, columnName, type));
        maker(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        maker(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
        maker(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader("app.properties")) {
            properties.load(fileReader);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.dropTable("Student");
            tableEditor.createTable("Student");
            System.out.println(getTableScheme(tableEditor.connection, "Student"));
            tableEditor.addColumn("Student", "name", "varchar(255)");
            System.out.println(getTableScheme(tableEditor.connection, "Student"));
            tableEditor.renameColumn("Student", "name", "FullName");
            System.out.println(getTableScheme(tableEditor.connection, "Student"));
            tableEditor.dropColumn("Student", "FullName");
            System.out.println(getTableScheme(tableEditor.connection, "Student"));
        }
    }
}