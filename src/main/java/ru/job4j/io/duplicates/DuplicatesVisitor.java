package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, Path> map = new HashMap<>();

    @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
         FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
         if (map.containsKey(fileProperty)) {
             System.out.println(file);
         }
         map.put(fileProperty, file);
         return super.visitFile(file, attrs);
        }
    }

