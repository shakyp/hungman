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
    static int errorCount = 0;
    static int correctCount = 0;
    static String strGuessLetters;
    static String words;
    static String[] chars;

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
                case ('Q') -> System.exit(0);
                default -> System.out.println("""
                        Вы перепутали клавишу.
                        Попробуйте ещё раз.""");
            }
        } while (key != 'S' & key != 'Q');
    } // Начало нового раунда/выход из игры

    public static void gameStart() throws IOException {
        System.out.println("Вы нажали S");

        errorCount = 0; // Обнуление счётчика ошибок в начале новой игры

        wordToX(); // Скрывает слово

        do {
            drawGallows();
            closeWord();
        } while (errorCount < 7);

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

        if (errorCount < 7) {
            System.out.println(gallows[errorCount]);
        } // Вывод состояния виселецы в зависимости от колличества ошибок

    } // Состояния виселицы

    public static String getWords() throws IOException {

        List<String> words = Files.readAllLines(Paths.get("game/words.txt"), StandardCharsets.UTF_8);
        return words.get(new Random().nextInt(words.size()));
    } // Отвечает за получение слова из файла (возможно надо заменить list на массив)

    public static void wordToX() throws IOException {

        words = getWords();
        System.out.println(words); // Вывод загаданого слова, нужно будет удалить
        chars = words.split(""); // Преобразование строки в массив букв
        int charsLength = chars.length; // Длина массива
        strGuessLetters = new String(new char[charsLength]).replace("\0", "❌"); // замена всех букв в строке на X
    } // Отвечает за скрытие слова

    public static void closeWord() throws IOException {

        char letter = ' ';

        System.out.println("Колличество ошибок: " + errorCount);
        System.out.println("Загаданое слово: " + strGuessLetters);

        if (errorCount == 6) {
            System.out.println("Вы проиграли =(");
            startAndQuit();
        } else if (correctCount == chars.length) {
            System.out.println("Вы выиграли!");
            startAndQuit();
        }else {
            System.out.print("Введите букву: ");
            letter = scanner.next().charAt(0);
        }

        int startIndex = 0; // Начальный индекс строки
        for (int i = 0; i < chars.length; i++) {
            int indexLetter = words.indexOf(letter, startIndex);
            if (indexLetter == -1) {
                if (i == 0) {
                    errorCount++;
                }
                break;
            } else {
                strGuessLetters = strGuessLetters.substring(0, indexLetter) + Character.toUpperCase(letter) + strGuessLetters.substring(indexLetter + 1);
                correctCount++;
                startIndex = indexLetter + 1;
            }
        }// Цикл отвечает за замену символов ✖ на отгаданую букву

    } // Отвечает за показ отгаданых букв в слове

}
