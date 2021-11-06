package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dup = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\Test"), dup);
        List<Path> lst = dup.getDuplicates();
        lst.forEach(System.out::println);
    }
}