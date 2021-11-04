package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -dir.jar ROOT_FOLDER.");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.getName().endsWith(args[1])) {
                System.out.println(String.format("File name : %s, file size : %s", subfile.getName(), subfile.length()));
            }

        }
    }
}