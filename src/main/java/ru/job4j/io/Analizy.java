package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder st = new StringBuilder();
        List<String> lst = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
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
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.write(st.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/serverAvailabil.txt", "unavailable.csv");
        analizy.unavailable("./data/serverAvailabil2.txt", "unavailable2.csv");
    }
}