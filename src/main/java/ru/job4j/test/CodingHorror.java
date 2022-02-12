package ru.job4j.test;

public class CodingHorror {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i < 5) {
                if (i % 3 == 0) {
                    System.out.println("Fizz " + i);
                }
            } else  {
                if (i % 3 == 0 && i % 5 != 0) {
                    System.out.println("Fizz " + i);
                }
                if (i % 5 == 0 && i % 3 != 0) {
                    System.out.println("Buzz " + i);
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    System.out.println("FizzBuzz " + i);
                }
            }
        }
    }
}
