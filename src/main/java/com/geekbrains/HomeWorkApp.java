package com.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    public static int SIZE = 3;
    public static char DOT_EMPTY = '.';
    public static char DOT_X = 'X';
    public static char DOT_O = 'O';
    public static char[][] map = new char[SIZE][SIZE];
    public static Random rand = new Random();
    public static Scanner sc = new Scanner(System.in);

    public static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i == 0 ? "  " : i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanStep() {
        int x, y;
        do {
            System.out.println("Делай свой шаг, кожанный мешок!!!");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isValid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    public static void aiStep() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isValid(x, y));
        System.out.println("Машина походила: " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++)
            if ((map[i][0] == symbol && map[i][1] == symbol && map[i][2] == symbol) ||
                    (map[0][i] == symbol && map[1][i] == symbol && map[2][i] == symbol))
                return true;
        return (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) ||
                (map[2][0] == symbol && map[1][1] == symbol && map[0][2] == symbol);
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {

            humanStep();
            printMap();

            if (checkWin(DOT_X)) {
                System.out.println("Human win");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiStep();
            printMap();

            if (checkWin(DOT_O)) {
                System.out.println("AI win");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Game Over");
    }
}
