package ru.geekbrains.javaCore.lesson03;

import java.util.Random;
import java.util.Scanner;

// 1. Полностью разобраться с кодом;
// 2. Переделать проверку победы, чтобы она не была реализована просто набором условий.
// 3. Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4 в линию.

public class Homework03 {

    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
        sc.close();
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    // проверяем диагональные линии
    public static boolean checkDiagonals(char symb, int x_offset, int y_offset) {
        boolean diag1, diag2;
        diag1 = true;
        diag2 = true;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            diag1 &= (map[x_offset + i][y_offset + i] == symb);
            diag2 &= (map[x_offset + DOTS_TO_WIN-1 - i][y_offset + i] == symb);
        }
        return diag1 || diag2;
    }

    // проверяем гозизонтальные и вертикальные линии
    public static boolean checkLines(char symb, int x_offset, int y_offset) {
        boolean horizontal, vertical;
        for (int x = 0; x < DOTS_TO_WIN; x++) {
            vertical = true;
            horizontal = true;
            for (int y = 0; y < DOTS_TO_WIN; y++) {
                vertical &= (map[x_offset + x][y_offset + y] == symb);
                horizontal &= (map[x_offset + y][y_offset + x] == symb);
            }
            if (vertical || horizontal) return true;
        }
        return false;
    }

    public static boolean checkWin(char symb) {
        //return checkDiagonals(symb, map) || checkLines(symb, map);
        for (int x_offset = 0; x_offset <= SIZE - DOTS_TO_WIN; x_offset++)
            for (int y_offset = 0; y_offset <= SIZE - DOTS_TO_WIN; y_offset++)
                if (checkDiagonals(symb, x_offset, y_offset) || checkLines(symb, x_offset, y_offset)) return true;
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}
