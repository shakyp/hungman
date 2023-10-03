package game;

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
}
