package game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in); // Сканер

    public static void main(String[] args) throws IOException {
        startAndQuit();
    }

    static void startAndQuit() throws IOException {
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

    public static void gameStart() throws IOException {
        System.out.println("Вы нажали S");
        drawGallows();
        closeWord();
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
""", """
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
        return words.get(new Random().nextInt(words.size()));
    } // Отвечает за получение слова из файла

    public static void closeWord() throws IOException {
        String words = getWords(); // Загаданное слово
        System.out.println(words); // Вывод загаданого слова, нужно будет удалить
        String[] chars = words.split(""); // Преобразование строки в массив букв

        int charsLength = chars.length; // Длина массива
        String myString = new String(new char[charsLength]).replace("\0", "❌"); // замена всех букв в строке на X
        System.out.println("Загаданое слово: " + myString); // Вывод спрятаной строки

        System.out.print("Введите букву: ");
        char letter = scanner.next().charAt(0);

        int startIndex = 0; // Начальный индекс строки
        String strGuessLetters = ""; // Начальная строка для открывания букв
        for (int i = 0; i < chars.length; i++) {
            int indexLetter = words.indexOf(letter, startIndex);
            if (indexLetter == -1) {
                break;
            } else {
                if (startIndex == 0) {
                    strGuessLetters = myString.substring(0, indexLetter) + Character.toUpperCase(letter) + myString.substring(indexLetter + 1);
                } else {
                    strGuessLetters = strGuessLetters.substring(0, indexLetter) + Character.toUpperCase(letter) + strGuessLetters.substring(indexLetter + 1);
                }
                startIndex = indexLetter + 1;
            }
        }// Цикл отвечает за замену символов ✖ на отгаданую букву
        System.out.println("Загаданое слово: " + strGuessLetters);
    } // Отвечает за скрытие слова и показа отгаданых букв

}
