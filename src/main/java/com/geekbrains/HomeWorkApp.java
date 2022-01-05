package com.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    public static int SIZE = 5;
    public static int DOT_TO_WIN = 4;
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

    public static boolean checkWin(char dot) {
        int hor, ver;
        int diagMain, diagReverse;
        for (int i = 0; i < SIZE; i++) {
            hor = 0;
            ver = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == dot) {
                    hor++;
                } else if (map[i][j] != dot && hor < DOT_TO_WIN) {
                    hor = 0;
                }
                if (map[j][i] == dot) {
                    ver++;
                }   else if (map[j][i] != dot && ver < DOT_TO_WIN) {
                    ver = 0;
                }
            }
            if (hor >= DOT_TO_WIN || ver >= DOT_TO_WIN) {
                return true;
            }
        }

        for (int j = 0; j < SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = j + i;
                if (k < SIZE) {
                    if (map[i][k] == dot) {
                        diagMain++;
                    } else if (map[i][k] != dot && diagMain < DOT_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOT_TO_WIN) {
                    return true;
                }
            }
        }
        for (int j = 1; j < SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = j + i;
                if (k < SIZE) {
                    if (map[k][j] == dot) {
                        diagMain++;
                    } else if (map[k][j] != dot && diagMain < DOT_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOT_TO_WIN) {
                    return true;
                }
            }
        }
        for (int j = 0; j < SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = (SIZE - 1) - i;
                int l = j + i;
                if (k >= 0 && l < SIZE) {
                    if (map[l][k] == dot) {
                        diagReverse++;
                    } else if (map[l][k] != dot && diagReverse < DOT_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOT_TO_WIN) {
                    return true;
                }
            }
        }
        for (int j = 1; j < SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = (SIZE - 1) - j - i;
                if (k >= 0) {
                    if (map[i][k] == dot) {
                        diagReverse++;
                    } else if (map[i][k] != dot && diagReverse < DOT_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOT_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
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
