package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("There is no one arguments find!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Map<String, String> map = new HashMap<>();
        map = Arrays.stream(args).collect(Collectors
                .toMap(k -> k.substring(1, k.indexOf("=")),
                        v -> v.substring(v.indexOf("=") + 1, v.length())));
        values.putAll(map);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        Arrays.stream(args).forEach(s -> {
            String[] st = s.split("=");
            if (st.length < 2) {
                throw new IllegalArgumentException("There is no one arguments find!");
            }
        });
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        ArgsName jvm1 = ArgsName.of(new String[] {});
        jvm1.get("Xmx");
    }
}