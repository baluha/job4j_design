package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!map.containsKey(fileProperty)) {
            List<Path> lst = new ArrayList<>();
            lst.add(file);
             map.put(fileProperty, lst);
         } else {
            List<Path> lstContains = new ArrayList<>(map.get(fileProperty));
            lstContains.add(file);
            map.put(fileProperty, lstContains);
         }
         return super.visitFile(file, attrs);
        }

        public List<Path> getDuplicates() {
            List<Path> dup = new ArrayList<>();
            map.values()
                .stream()
                .filter(l -> l.size() > 1)
                .forEach(dup::addAll);
            return dup;
        }
    }

