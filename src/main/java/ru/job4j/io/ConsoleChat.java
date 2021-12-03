package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> dialogue = new ArrayList<>();
        List<String> answers = readPhrases();
        String userAnswer;
        Scanner scanner = new Scanner(System.in);
        boolean runnable = true;
        boolean botLunch = true;
        while (runnable) {
            userAnswer = scanner.nextLine();
            int randomAnswer = (int) (Math.random() * readPhrases().size());
            if (OUT.equals(userAnswer)) {
                runnable = false;
                dialogue.add(userAnswer);
                break;
            }
            if (STOP.equals(userAnswer)) {
               botLunch = false;
               dialogue.add(userAnswer);
            }
            if (CONTINUE.equals(userAnswer)) {
               botLunch = true;
               dialogue.add(userAnswer);
            }
            if (botLunch && !CONTINUE.equals(userAnswer)) {
                dialogue.add(userAnswer);
                System.out.println(answers.get(randomAnswer));
                dialogue.add(answers.get(randomAnswer));
            }
            if (!botLunch && !STOP.equals(userAnswer)) {
                dialogue.add(userAnswer);
            }
        }
        saveLog(dialogue);
    }

    private List<String> readPhrases() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"),
                true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("blabla.txt", "C:\\projects\\AnswersBot.txt");
        cc.run();
    }
}