package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(ResultFile.multi(9).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String multi(int size) {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                st.append((i + 1) * (j + 1)).append(" ");
            }
            st.append(System.lineSeparator());
        }
        return st.toString();
    }
}