package game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in); // Сканер

    public static void main(String[] args) {
        startAndQuit();
    }

    static void startAndQuit() {
        System.out.println("""
                Введите букву "S" чтобы начать новую игру.
                Чтобы выйти введите букву "Q".
                (учитывайте регистр)""");

        char key; // Переменная для хранения клавиши входа/входа

        do {
            switch (key = scanner.next().charAt(0)) {
                case ('S') -> gameStart();
                case ('Q') -> System.out.println("Вы нажали Q");
                default -> System.out.println("""
                        Вы перепутали клавишу.
                        Попробуйте ещё раз.""");
            }
        } while (key != 'S' & key != 'Q');
    } // Начало нового раунда/выход из игры

    public static void gameStart() {
        System.out.println("Вы нажали S");
        drawGallows();
    } // Начало игры

    public static void drawGallows() {
        String[] gallows = new String[]{
                """
    +---+
    |   |
    |
    |
    |
    |
    +---+
""",
                """
    +---+
    |   |
    |   O
    |
    |
    |
    +---+
""",
                """
    +---+
    |   |
    |   O
    |   |
    |   |
    |
    +---+
""",
                """
    +---+
    |   |
    |   O
    |  \\|
    |   |
    |
    +---+
""",
                """
    +---+
    |   |
    |   O
    |  \\|/
    |   |
    |
    +---+
""",
                """
    +---+
    |   |
    |   O
    |  \\|/
    |   |
    |  /
    +---+
""",            """
    +---+
    |   |
    |   O
    |  \\|/
    |   |
    |  / \\
    +---+
"""
        };
        System.out.println(gallows[0]);
    } // Рисование виселицы

    public static String getWords() throws IOException {

        List<String> words = Files.readAllLines(Paths.get("game/words.txt"), StandardCharsets.UTF_8);
        int index = new Random().nextInt(words.size());
        return words.get(index);
    } // Отвечает за получение и обработку файла
}
