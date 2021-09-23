package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


    public static void main(String[] args) {

        User user1 = new User("Ivan", 2,
                new GregorianCalendar(1992, Calendar.MAY, 24));
        User user2 = new User("Ivan", 2,
                new GregorianCalendar(1992, Calendar.MAY, 24));

        Map<User, Object> map = new HashMap<>();

        map.put(user1, new Object());
        map.put(user2, new Object());
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey().hashCode());
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}

