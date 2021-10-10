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
            int[] splitNumbers = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                splitNumbers[i] = Integer.parseInt(numbers[i]);
                if (splitNumbers[i] % 2 == 0) {
                    System.out.println(splitNumbers[i] + " является четным");
                } else {
                    System.out.println(splitNumbers[i] + " является нечетным");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }
