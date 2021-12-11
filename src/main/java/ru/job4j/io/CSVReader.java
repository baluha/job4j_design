package ru.job4j.io;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.*;


public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception, IllegalArgumentException {
        if (argsName.getLength() < 4) {
           throw new IllegalArgumentException("Not enough arguments, it must be four!");
        }
        Path data = Path.of(argsName.get("path"));
        if (!new File(String.valueOf(data)).exists()) {
           throw new IllegalArgumentException("File does not exist!");
        }
        String delimiter = argsName.get("delimiter");
        if (!(";").equals(delimiter)) {
            throw new IllegalArgumentException("Delimiter must be ';'!");
        }
        List<String> filter = Arrays.asList(argsName.get("filter").split(","));
        String out = argsName.get("out");

        List<String> st = new ArrayList<>();
        try (Scanner scanner = new Scanner(data)) {
            while (scanner.hasNextLine()) {
                st.add(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> indexes = getIndexes(filter, st, delimiter);
        String properties = getProperties(st, indexes, delimiter);
        saveLog(Collections.singletonList(properties), out);
    }

    private static void saveLog(List<String> log, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(String.valueOf(path), Charset.forName("WINDOWS-1251"),
                true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getIndexes(List<String> filter, List<String> st, String delimiter) {
        List<String> values = Arrays.asList(st.get(0).split(delimiter));
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < filter.size(); i++) {
            if (values.contains(filter.get(i))) {
                indexes.add(values.indexOf(filter.get(i)));
            }
        }
        return indexes;
    }

    private static String getProperties(List<String> st, List<Integer> indexes, String delimiter) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < st.size(); i++) {
            List<String> tmp = Arrays.asList(st.get(i).split(delimiter));
            for (int j = 0; j < indexes.size(); j++) {
                if (j < indexes.size() - 1) {
                    stringBuilder.append(tmp.get(indexes.get(j))).append(";");
                } else if (i == st.size() - 1) {
                    stringBuilder.append(tmp.get(indexes.get(j)));
                } else {
                    stringBuilder.append(tmp.get(indexes.get(j))).append(System.lineSeparator());
                }
            }
        }
        return String.join(
                System.lineSeparator(),
                stringBuilder.toString()
        );
    }
}