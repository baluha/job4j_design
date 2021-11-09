package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("There is no one arguments found!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Map<String, String> map = new HashMap<>();
        map = Arrays.stream(args).collect(Collectors
                .toMap(k -> {
                    if (k.indexOf("-") != 0) {
                        throw new IllegalArgumentException("There is no one arguments found!");
                    }
                    return k.substring(1, k.indexOf("="));
                },
                        v -> v.substring(v.lastIndexOf("=") + 1, v.length())));
        values.putAll(map);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        Arrays.stream(args).forEach(s -> {
            String[] st = s.split("=");
            if (st.length < 2) {
                throw new IllegalArgumentException("There is no one arguments found!");
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



        String[] st = new String[] {"=UTF-8", "-Xmx=512"};
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(st).forEach(s -> {
            stringBuilder.append(Arrays.toString(s.split("=")));
        });
        System.out.println(stringBuilder.toString());
    }
}