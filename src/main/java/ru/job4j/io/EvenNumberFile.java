package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder st = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                st.append((char) read);
            }
            String[] numbers = st.toString().split(System.lineSeparator());

            for (String number : numbers) {
                if (Integer.parseInt(number) % 2 == 0) {
                    System.out.println(number + " является четным");
                } else {
                    System.out.println(number + " является нечетным");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }
