package ru.job4j.io.filefinder;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Searcher {
    public static void main(String[] args) throws IOException {

        ArgsName argsName = ArgsName.of(args);
        Path root = Path.of(argsName.get("d"));
        writeFile(pathList(root, argsName), argsName);
    }

    public static List<Path> pathList(Path root, ArgsName argsName) throws IOException {
        List<Path> listPath = new ArrayList<>();
        String param = argsName.get("t");
        if ("mask".equals(param)) {
            if (argsName.get("n").startsWith("*")) {
                String mask = argsName.get("n").substring(argsName.get("n").indexOf("*") + 1);
                listPath = Search.search(root, s -> s.toFile().getName().endsWith(mask));
            } else if (argsName.get("n").endsWith("*")) {
                String mask = argsName.get("n").substring(0, argsName.get("n").indexOf("*"));
                listPath = Search.search(root, s -> s.toFile().getName().startsWith(mask));
            }
        }
        if ("name".equals(param)) {
            listPath = Search.search(root, s -> s.toFile().getName().equals(argsName.get("n")));
        }
        if ("regex".equals(param)) {
                Pattern pattern = Pattern.compile(argsName.get("n"));
                listPath = Search.search(root, s -> pattern.matcher(s.toFile().getName()).find());
            }

        return listPath;
    }

    public static void writeFile(List<Path> paths, ArgsName argsName) {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsName.get("o"))))) {
            for (Path path: paths) {
                out.write(String.valueOf(path));
                out.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

