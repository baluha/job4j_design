package ru.job4j.io;

import java.io.*;
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
            bufferedReader.close();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (String st: log) {
                out.write(st);
            }
                } catch (Exception e) {
            e.printStackTrace();
        }
    }
}