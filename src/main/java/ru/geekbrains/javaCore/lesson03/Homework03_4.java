package ru.geekbrains.javaCore.lesson03;

import java.util.Random;
import java.util.Scanner;
import java.awt.Point;

// 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока, и пытаться выиграть сам.

public class Homework03_4 {

    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
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
        for (int y = 0; y < SIZE; y++) {
            System.out.print((y + 1) + " ");
            for (int x = 0; x < SIZE; x++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[x][y] == DOT_EMPTY;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    // необходима блокировка диагональных линий?
    public static Point blockDiagonals(char symb, int x_offset, int y_offset) {
        int diag1Score, diag2Score;
        diag1Score = 0;
        diag2Score = 0;
        Point diag1Point, diag2Point;
        diag1Point = new Point();
        diag2Point = new Point();
        for (int i = 0, i2 = DOTS_TO_WIN-1; i < DOTS_TO_WIN; i++, i2--) {
            // оцениваем первую диагональ
            if (map[x_offset + i][y_offset + i] == symb)
                diag1Score++;
            else if (map[x_offset + i][y_offset + i] == DOT_EMPTY)
                diag1Point = new Point(x_offset + i, y_offset + i);
            else diag1Score--;
            // оцениваем вторую диагональ
            if (map[x_offset + i2][y_offset + i] == symb)
                diag2Score ++;
            else if (map[x_offset + i2][y_offset + i] == DOT_EMPTY)
                diag2Point = new Point(x_offset + i2, y_offset + i);
            else diag2Score--;
        }
        if (diag1Score == DOTS_TO_WIN-1) {
            //System.out.println("diag1Point = " + diag1Point.toString());
            return diag1Point;
        }
        if (diag2Score == DOTS_TO_WIN-1) {
            //System.out.println("diag2Point = " + diag2Point.toString());
            return diag2Point;
        }
        return null;
    }

    // необходима блокировка гозизонтальных и вертикальных линий?
    public static Point blockLines(char symb, int x_offset, int y_offset) {
        int horizontalScore, verticalScore;
        Point hrzntlPoint, vrtclPoint;
        for (int x = 0; x < DOTS_TO_WIN; x++) {
            verticalScore = 0;
            horizontalScore = 0;
            vrtclPoint = new Point();
            hrzntlPoint = new Point();
            for (int y = 0; y < DOTS_TO_WIN; y++) {
                // оцениваем вертикаль
                if (map[x_offset + x][y_offset + y] == symb)
                    verticalScore++;
                else if (map[x_offset + x][y_offset + y] == DOT_EMPTY)
                    vrtclPoint = new Point(x_offset + x, y_offset + y);
                else verticalScore--;
                // оцениваем горизонталь
                if (map[x_offset + y][y_offset + x] == symb)
                    horizontalScore++;
                else if (map[x_offset + y][y_offset + x] == DOT_EMPTY)
                    hrzntlPoint = new Point(x_offset + y, y_offset + x);
                else horizontalScore--;
            }
            if (verticalScore == DOTS_TO_WIN-1) {
                //System.out.println("vrtclPoint = " + vrtclPoint.toString());
                return vrtclPoint;
            }
            if (horizontalScore == DOTS_TO_WIN-1) {
                //System.out.println("hrzntlPoint = " + hrzntlPoint.toString());
                return hrzntlPoint;
            }
        }
        return null;
    }

    // проверяем, необходима ли блокировка "победного" хода человека?
    public static Point blockWin(char symb) {
        Point blockPoint;
        for (int x_offset = 0; x_offset <= SIZE - DOTS_TO_WIN; x_offset++)
            for (int y_offset = 0; y_offset <= SIZE - DOTS_TO_WIN; y_offset++) {
                // необходима блокировка диагональных линий?
                blockPoint = blockDiagonals(symb, x_offset, y_offset);
                if (blockPoint != null)
                    return blockPoint;
                // необходима блокировка гозизонтальных и вертикальных линий?
                blockPoint = blockLines(symb, x_offset, y_offset);
                if (blockPoint != null)
                    return blockPoint;
            }
        return null;
    }

    // оцениваем ход компьютера
    public static int scoreTurn(char symb, int x_turn, int y_turn) {
        int diag1Score, diag2Score;
        int diag1Size, diag2Size;
        int horizontalScore, verticalScore;
        diag1Score = 0;
        diag2Score = 0;
        diag1Size = 0;
        diag2Size = 0;
        verticalScore = 0;
        horizontalScore = 0;
        for (int i = 0, x1 = x_turn-(DOTS_TO_WIN-1), x2 = x_turn+(DOTS_TO_WIN-1), y = y_turn-(DOTS_TO_WIN-1); i < DOTS_TO_WIN+DOTS_TO_WIN; i++, x1++, x2--, y++) {
            if (0 <= y && y < SIZE && 0 <= x1 && x1 < SIZE) {
                // оцениваем возможность заполнить первую диагональ
                if (map[x1][y] == symb) diag1Score++;
                else if (map[x1][y] == DOT_EMPTY) ;
                else diag1Score--;
                diag1Size++;
            }
            if (0 <= y && y < SIZE && 0 <= x2 && x2 < SIZE) {
                // оцениваем возможность заполнить вторую диагональ
                if (map[x2][y] == symb) diag2Score++;
                else if (map[x2][y] == DOT_EMPTY) ;
                else diag2Score--;
                diag2Size++;
            }
            if (0 <= y && y < SIZE) {
                // оцениваем возможность заполнить вертикальную линию
                if (map[x_turn][y] == symb) verticalScore++;
                else if (map[x_turn][y] == DOT_EMPTY) ;
                else verticalScore--;
            }
            if (0 <= x1 && x1 < SIZE) {
                // оцениваем возможность заполнить горизонтальную линию
                if (map[x1][y_turn] == symb) horizontalScore++;
                else if (map[x1][y_turn] == DOT_EMPTY) ;
                else horizontalScore--;
            }
        }
        diag1Score = (diag1Size >= DOTS_TO_WIN)?Math.abs(diag1Score)+1:0;
        diag2Score = (diag2Size >= DOTS_TO_WIN)?Math.abs(diag2Score)+1:0;
        verticalScore = Math.abs(verticalScore);
        horizontalScore = Math.abs(horizontalScore);

        //System.out.println("[x="+x_turn+",y="+y_turn+"] Оценка = "+diag1Score+" "+diag2Score+" "+verticalScore+" "+horizontalScore);
        return diag1Score + diag2Score + verticalScore + horizontalScore;
    }

    public static void aiTurn() {
        int x, y;
        Point blockWinPoint;
        int turnScore, bestTurnScore = 0;
        Point bestTurnPoint = new Point();
        // проверяем, необходима ли блокировка "победного" хода человека?
        blockWinPoint = blockWin(DOT_X);
        if (blockWinPoint != null) {
            x = blockWinPoint.x;
            y = blockWinPoint.y;
        }
        else {
            // выбираем один лучший из нескольких ходов
            for (int attempt = 0; attempt <  Math.pow(DOTS_TO_WIN,2); attempt++) {
                do {
                    x = rand.nextInt(SIZE);
                    y = rand.nextInt(SIZE);
                } while (!isCellValid(x, y));
                turnScore = scoreTurn(DOT_O, x, y);
                if (turnScore >= bestTurnScore) {
                    bestTurnScore = turnScore;
                    bestTurnPoint = new Point(x, y);
                }
            }
            x = bestTurnPoint.x;
            y = bestTurnPoint.y;
        }
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[x][y] = DOT_O;
    }

    // проверяем диагональные линии
    public static boolean checkDiagonals(char symb, int x_offset, int y_offset) {
        boolean diag1, diag2;
        diag1 = true;
        diag2 = true;
        for (int i = 0, i2 = DOTS_TO_WIN-1; i < DOTS_TO_WIN; i++, i2--) {
            diag1 &= (map[x_offset + i][y_offset + i] == symb);
            diag2 &= (map[x_offset + i2][y_offset + i] == symb);
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

