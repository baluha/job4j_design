package ru.job4j.question;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = current
                .stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        int change = 0;
        int dell = 0;
        for (User prev: previous) {
            if (!prev.getName()
                    .equals(map.get(prev.getId()))
                    && map.get(prev.getId()) != null) {
                change++;
            } else if (map.get(prev.getId()) == null) {
                dell++;
            }
        }
        Set<User> rsl = new HashSet<>();
        rsl.addAll(previous);
        rsl.addAll(current);
        Set<Integer> lst = rsl.stream()
                .map(User::getId)
                .collect(Collectors.toCollection(HashSet::new));
        int add = lst.size() - previous.size();
        return new Info(add, change, dell);
    }

    }