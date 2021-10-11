package ru.job4j.question;

import ru.job4j.map.SimpleMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        Set<User> rsl = new HashSet<>();
        rsl.addAll(previous);
        rsl.addAll(current);
        Set<Integer> lst = rsl.stream().map(User::getId).collect(Collectors.toCollection(HashSet::new));
        System.out.println(rsl);
    }
}
