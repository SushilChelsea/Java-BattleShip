package org.sushil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        char[][] oceanMap = new char[10][10];
        createOceamMap(oceanMap);

        Scanner input = new Scanner(System.in);

        // User placing ships on the map
        placeUserShips(oceanMap, input);


        printMap(oceanMap); // printing the updated map

        // Now Computer turn to place ship
        compShips(oceanMap);
        printMap(oceanMap); // printing the updated map

        System.out.println();
        System.out.println("Your Turn");
        System.out.print("Enter X coordinate: ");
        int playerGuessXcoordinate = input.nextInt();
        System.out.print("Enter Y coordinate: ");
        int playerGuessYcoordinate = input.nextInt();

        int userShips = 5;
        int compShips = 5;

        ArrayList<Integer> markedXCoordinate= new ArrayList<Integer>();
        ArrayList<Integer> markedYCoordinate= new ArrayList<Integer>();

        while (userShips != 0 && compShips != 0) {
            while (playerGuessXcoordinate > 9 || playerGuessYcoordinate > 9) {
                System.out.println("Please Enter co-ordinate between 0-9.");
                System.out.print("Enter X coordinate : ");
                playerGuessXcoordinate = input.nextInt();
                System.out.print("Enter Y coordinate : ");
                playerGuessYcoordinate = input.nextInt();
            }

            int playerXvalue = playerGuessXcoordinate;
            int playerYvalue = playerGuessYcoordinate;


            if (oceanMap[playerGuessXcoordinate][playerGuessYcoordinate] == '*') {

                oceanMap[playerGuessXcoordinate][playerGuessYcoordinate] = '!';
                System.out.println("Boom! You sunk the ship!");
                compShips--;

            } else if (oceanMap[playerGuessXcoordinate][playerGuessYcoordinate] == '@') {
//                System.out.println(oceanMap[playerGuessXcoordinate][playerGuessYcoordinate]);
                oceanMap[playerGuessXcoordinate][playerGuessYcoordinate] = 'x';
                System.out.println("Oh no, you sunk your own ship :(");
                userShips--;
            } else {
//                System.out.println(oceanMap[playerGuessXcoordinate][playerGuessYcoordinate]);
                oceanMap[playerGuessXcoordinate][playerGuessYcoordinate] = '-';
                System.out.println("Sorry, you missed ");
                System.out.println();
            }
            markedXCoordinate.add(playerGuessXcoordinate);
            markedYCoordinate.add(playerGuessYcoordinate);

            // Now Computer Guess turn
            Random rand = new Random();
            int compXGuess = rand.nextInt(9);
            int compYGuess = rand.nextInt(9);
            System.out.println();
            System.out.println("Computer Guess X : " + compXGuess);
            System.out.println("Computer Guess Y : " + compYGuess);
            System.out.println("COMPUTER'S TURN");
            if (oceanMap[compXGuess][compYGuess] == '@') {
                System.out.println("The Computer sunk one of your ships!");
                oceanMap[compXGuess][compYGuess] = 'x';
                userShips--;
            } else if (oceanMap[compXGuess][compYGuess] == '*') {
                System.out.println("The Computer sunk one of its own ships");
                oceanMap[compXGuess][compYGuess] = '!';
                compShips--;
            } else {
                System.out.println("Computer missed");
            }
            System.out.println();
            printMap(oceanMap);
            System.out.println();
            System.out.println("Your ships: " + userShips + " | " + "Computer ships: " + compShips);
            System.out.println();

            if (userShips == 0 || compShips == 0) {
                break;
            }
            System.out.print("Enter X coordinate : ");
            playerGuessXcoordinate = input.nextInt();
            System.out.print("Enter Y coordinate : ");
            playerGuessYcoordinate = input.nextInt();
            System.out.println();

        }
        System.out.println("--------------------------------------------------------");
        if (userShips > 0) {
            System.out.println("Hooray! you win the battle :)");
        } else {
            System.out.println("Sorry! you lost the battle :( ");
        }

    }

    private static void placeUserShips(char[][] oceanMap, Scanner input) {
        int count = 0;
        // Until user place 5 ships program keeps repeating for x and y co-ordinate
        while (count != 5) {
            // ask the user to enter in the coordinates for where to place a ship
            System.out.print("Enter X coordinate for your ship " + (count+1) + " : ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your ship " + (count+1) + " : ");
            int y = input.nextInt();
            // Checks and prompt user to enter valid x and y co-ordinate
            while (x > 9 || y > 9 || oceanMap[x][y] == '1') {
                if (x > 9 || y > 9) {
                    System.out.println("Please Enter co-ordinate between 0-9.");
                } else if(oceanMap[x][y] == '1') {
                    System.out.println("Entered co-ordinate already registered.");
                }
                System.out.print("Enter X coordinate for your ship " + (count+1) + " : ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship " + (count+1) + " : ");
                y = input.nextInt();
            }
            count++;        // if user enter correct value, increase count
            oceanMap[x][y] = '1';   // everything ok then, update oceanMap

        }
    }

    private static void compShips(char[][] oceanMap) {
        Random rand = new Random();
        int compCounter = 0;
        System.out.println();
        System.out.println("Computer is deploying ships");
        // Until an unless computer deploys 5 ships keep repeating
        while (compCounter != 5) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);
            // while x and y coordinate finds any of these char keep changing coordinates
            while (oceanMap[x][y] == '@'|| oceanMap[x][y] == '2') {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            }
            oceanMap[x][y] = '2';   // place computer ship sign to map
            System.out.println(compCounter+1 + ". ship DEPLOYED ");
            compCounter++;      // update computer counter
        }
        System.out.println();
    }

    private static void printMap(char[][] oceanMap) {

        System.out.println("\t0123456789");
        for (int r = 0; r < 10; r++) {
            System.out.print(r + " | ");
            for (int c = 0; c < 10; c++) {

                if (oceanMap[r][c] == '1' || oceanMap[r][c] == '@') {
                    oceanMap[r][c] = '@';
                    System.out.print(oceanMap[r][c]);
                } else if(oceanMap[r][c] == 'x'){
                    System.out.print(oceanMap[r][c]);
                } else if(oceanMap[r][c] == '!'){
                    System.out.print(oceanMap[r][c]);
                } else if (oceanMap[r][c] == '2') {
                    System.out.print(' ');
                    oceanMap[r][c] = '*';
//                    System.out.print(oceanMap[r][c]);
                } else {
                    System.out.print(' ');
                }

            }
            System.out.print(" | "+ r);
            System.out.println();
        }
        System.out.println("\t0123456789");
    }

    private static void createOceamMap(char[][] oceanMap) {
        System.out.println("**** Welcome to Battle ships game ****");
        System.out.println("Right now, the sea is empty.");
        System.out.println();
        System.out.println("\t0123456789");
        for (int r = 0; r < 10; r++) {
            System.out.print(r + " | ");    // print row number with pipe char
            for (int c = 0; c < 10; c++) {
                System.out.print(' ');      // print space char in column 1 to 9
            }
            System.out.print(" | "+ r);     // print pipe char with row number
            System.out.println();
        }
        System.out.println("\t0123456789");
        System.out.println();
    }
}
