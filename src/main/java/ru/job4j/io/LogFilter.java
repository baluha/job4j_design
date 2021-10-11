package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lst = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"))) {
            lst = bufferedReader.lines()
                    .filter(st -> "404".equals(st.split(" ")[st.split(" ").length - 2]))
                    .map(st -> st + System.lineSeparator())
                    .collect(Collectors.toList());
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}