package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) throws FileNotFoundException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File path : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zipOutputStream.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid Arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        Path root = Path.of(argsName.get("d"));
        if (!Files.exists(root)) {
           throw new IllegalArgumentException("folder does not exist");
        }
        String extent = argsName.get("e");
        File target = new File(argsName.get("o"));
        List<Path> sourcesTmp = Search.search(root, path ->
                !path.toFile().getName().endsWith(extent));
        List<File> sources = sourcesTmp.stream().map(s -> new File(s.toString())).collect(Collectors.toList());
        packFiles(sources, target);

    }
}