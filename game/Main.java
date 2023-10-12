package game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Нажмите клавишу S чтобы начать новую игру.\n" +
                "Чтобы выйти нажмите клавишу Q.");

        Scanner scanner = new Scanner(System.in); // Сканер

        char key; // Переменная для хранения клавиши входа/входа

        do {
            key = scanner.next().charAt(0);

            if (key == 'S') {
                System.out.println("Вы нажали " + key);
            } else if (key == 'Q') {
                System.out.println("Вы нажали " + key);
            } else {
                System.out.println("Вы перепутали клавишу.\n" +
                        "Попробуйте ещё раз.");
            }
        } while (key != 'S' && key != 'Q');

    }

    public static String getWords() throws IOException {

        List<String> words = Files.readAllLines(Paths.get("game/words.txt"), StandardCharsets.UTF_8);
        int index = new Random().nextInt(words.size());
        return words.get(index);
    } // Отвечает за получение и обработку файла
}
