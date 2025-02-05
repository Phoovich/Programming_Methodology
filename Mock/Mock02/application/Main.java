package application;

import logic.game.GameManager;
import render.MapManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static GameManager gameManager;
    private static MapManager mapManager;
    private static Scanner scanner;
    private static ArrayList<Integer> scoreList;

    public static void main(String[] args) {
        initVariables();
        printStartMenu();
        boolean isEnded = false;
        while (!isEnded) {
            printMenuChoice();
            char input = getMenuInput();
            switch (input) {
                case '1':
                    startGame();
                    break;
                case '2':
                    printScoreList();
                    break;
                case '3':
                    isEnded = true;
            }
        }
        System.out.println("Program Terminated");
    }

    private static void initVariables() {
        scanner = new Scanner(System.in);
        scoreList = new ArrayList<>();
    }

    private static void waitInput() {
        System.out.println("Type ANYTHING to Continue");
        System.out.print(">>> ");
        scanner.nextLine();
    }

    private static void startGame() {
        gameManager = GameManager.resetInstance();
        mapManager = MapManager.resetInstance();
        System.out.println("Game Started");
        char input;

        while (!gameManager.getGameEnded()) {
            printTopUI();
            mapManager.renderMap();
            printBottomUI();
            input = getGameInput();
            gameManager.doNextStep(input);
        }
        printGameEnded();
    }

    private static char getGameInput() {
        char input = 0;
        do {
            System.out.print(">>> ");
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                input = line.strip().charAt(0);
            }
        } while (input != 'W' && input != 'A' && input != 'S' && input != 'D'
                && input != 'w' && input != 'a' && input != 's' && input != 'd');
        return input;
    }

    private static void printTopUI() {
        System.out.println("==========================");
        System.out.println("SCORE: " + gameManager.getGameScore());
        System.out.println("Dig Power: " + gameManager.getDigPower());
        System.out.println("Battery Left: " + gameManager.getBatteryLeft());
    }

    private static void printBottomUI() {
        System.out.println("==========================");
        MapManager mm = mapManager;
        int playerX = mm.getPlayerX();
        int playerY = mm.getPlayerY();
        String action;

        System.out.print("W: ");
        if (mm.checkEmptyTile(playerX, playerY - 1)) {
            action = "Move UP";
        } else if (mm.checkIsStone(playerX, playerY - 1)) {
            action = "Dig " + mm.getStoneName(mm.getStoneAt(playerX, playerY - 1));
        } else {
            action = "Do Nothing";
        }
        System.out.println(action);

        System.out.print("A: ");
        if (mm.checkEmptyTile(playerX - 1, playerY)) {
            action = "Move LEFT";
        } else if (mm.checkIsStone(playerX - 1, playerY)) {
            action = "Dig " + mm.getStoneName(mm.getStoneAt(playerX - 1, playerY));
        } else {
            action = "Do Nothing";
        }
        System.out.println(action);

        System.out.print("S: ");
        if (mm.checkEmptyTile(playerX, playerY + 1)) {
            action = "Move DOWN";
        } else if (mm.checkIsStone(playerX, playerY + 1)) {
            action = "Dig " + mm.getStoneName(mm.getStoneAt(playerX, playerY + 1));
        } else {
            action = "Do Nothing";
        }
        System.out.println(action);

        System.out.print("D: ");
        if (mm.checkEmptyTile(playerX + 1, playerY)) {
            action = "Move RIGHT";
        } else if (mm.checkIsStone(playerX + 1, playerY)) {
            action = "Dig " + mm.getStoneName(mm.getStoneAt(playerX + 1, playerY));
        } else {
            action = "Do Nothing";
        }
        System.out.println(action);
    }

    private static void printGameEnded() {
        printTopUI();
        mapManager.renderMap();
        int score = gameManager.getGameScore();
        boolean isHighScore = true;
        System.out.print("\nBattery out!! Game Ended with the score " + score);
        for (int oldScore : scoreList) {
            if (score <= oldScore) {
                isHighScore = false;
                break;
            }
        }
        if (isHighScore) {
            System.out.println(" (NEW HIGH SCORE!!)\n");
        } else {
            System.out.println("\n");
        }
        scoreList.add(score);
        waitInput();
    }

    private static void printStartMenu() {
        System.out.println("\n==========================");
        System.out.println("     MINING SIMULATOR");
        System.out.println("==========================\n");
        waitInput();
    }

    private static void printMenuChoice() {
        System.out.println("\n==========================\n");
        System.out.println("1) Start New Game");
        System.out.println("2) Score History");
        System.out.println("3) Exit");
        System.out.println("\nType the corresponding number to select your choice.");
    }

    private static char getMenuInput() {
        char input = 0;
        do {
            System.out.print(">>> ");
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                input = line.strip().charAt(0);
            }
        } while (input != '1' && input != '2' && input != '3');
        return input;
    }

    private static void printScoreList() {
        System.out.println("\n==========================\n");
        if (scoreList.isEmpty()) {
            System.out.println("You don't have any game recorded yet.");
            return;
        }
        int maxNum = 0;
        int maxScore = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            int score = scoreList.get(i);
            System.out.print((i + 1) + ". ");
            System.out.println(score);
            if (score > maxScore) {
                maxScore = score;
                maxNum = i + 1;
            }
        }
        System.out.println("Your Highest Score is " + maxScore + " at Play No." + maxNum + "\n");
        waitInput();
    }

}
