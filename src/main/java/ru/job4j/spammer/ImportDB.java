package ru.job4j.spammer;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(u -> {
                String[] spLine = u.split(";");
                if (spLine.length != 2 || spLine[0].isBlank() || spLine[1].isBlank()) {
                    throw new IllegalArgumentException("Name or email not found!");
                } else {
                    users.add(new User(u.substring(0, u.indexOf(";")), u.substring(u.indexOf(";") + 1, u.length() - 1)));
                }
            });

        }
        return users;
    }

    public void save(List<User> users) throws Exception {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (Statement statement = cnt.createStatement()) {
                statement.execute("drop table users;");
                statement.execute(String.format("create table if not exists users(%s, %s, %s);",
                        "id serial primary key",
                        "name varchar(255)",
                        "email varchar(255)"));
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }


    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./appForSpammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}