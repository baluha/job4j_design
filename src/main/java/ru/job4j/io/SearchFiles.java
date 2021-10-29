package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> pred;
    List<Path> lst = new ArrayList<>();

    public SearchFiles(Predicate pred) {
        this.pred = pred;
    }
    public List<Path> getPaths() {
        return lst;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
        if (pred.test(file)) {
            lst.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
