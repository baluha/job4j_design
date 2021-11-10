package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Test {
    public static String unavailable(String source) {
        StringBuilder st = new StringBuilder();
        List<String> lst = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))
        ) {
           in.lines().forEach(l -> {
               String[] str = l.split(" ");
               if ((str[0].equals("400") || str[0].equals("500")) && lst.size() == 0) {
                   lst.add(str[1]);
               } else if ((str[0].equals("200") || str[0].equals("300")) && lst.size() == 1) {
                   lst.add(str[1]);
                   st.append(lst.get(0)).append(";").append(lst.get(1)).append(";").append(System.lineSeparator());
                   lst.clear();
               }
           });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st.toString();
    }

    public static void main(String[] args) {
        String a = unavailable("./data/dateLog.txt");
        System.out.println(a);
    }
}