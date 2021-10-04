package ru.job4j.test;

import ru.job4j.map.SimpleMap;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("abc", "abc");
        map.put("abc", "abc");
        map.put("a", "ac");
        map.put("b", "ac");
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        String a = "b";
        System.out.println();
    }
}
