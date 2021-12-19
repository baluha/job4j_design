package ru.job4j.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String f = "name,age";
        List<String> filter = Arrays.asList(f.split(","));
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary_special"
        );
        List<String> st = new ArrayList<>();
        try (Scanner scanner = new Scanner(data)) {
            while (scanner.hasNextLine()) {
                st.add(scanner.next());
            }
        }
        for (String s: st) {
            System.out.println(s);
            System.out.println("--------");
        }
        List<String> values = Arrays.asList(st.get(0).split(";"));
        for (String v: values) {
            System.out.println(v);
            System.out.println("--------");
        }
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < filter.size(); i++) {
            if (values.contains(filter.get(i))) {
                indexes.add(values.indexOf(filter.get(i)));
            }
        }
        for (Integer i: indexes) {
            System.out.println(i);
            System.out.println("--------");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < st.size(); i++) {
            List<String> tmp = Arrays.asList(st.get(i).split(";"));
            for (int j = 0; j < indexes.size(); j++) {
                if (j < indexes.size() - 1) {
                    stringBuilder.append(tmp.get(indexes.get(j))).append(";");
                } else {
                    stringBuilder.append(tmp.get(indexes.get(j))).append(System.lineSeparator());
                }
            }
        }
        System.out.println(String.join(
                System.lineSeparator(),
                st.get(0),
                stringBuilder.toString()
        ));
    }
}
