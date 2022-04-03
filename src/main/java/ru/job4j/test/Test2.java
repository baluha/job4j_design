package ru.job4j.test;

import java.net.Socket;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestampFromLDT = Timestamp.valueOf(localDateTime);
        Timestamp time = timestampFromLDT;
        System.out.println(timestampFromLDT);
    }
}
