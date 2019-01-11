package org.sushil;

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
            while (x > 9 || y > 9) {
                System.out.println("Please Enter co-ordinate between 0-9.");
                System.out.print("Enter X coordinate for your ship " + (count+1) + " : ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship " + (count+1) + " : ");
                y = input.nextInt();
            }
            count++;        // if user enter correct value, increase count
            oceanMap[x][y] = '@';   // everything ok then, update oceanMap

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
            while (oceanMap[x][y] == '@'|| oceanMap[x][y] == 'x') {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            }
            oceanMap[x][y] = 'x';   // place computer ship sign to map
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
                if (oceanMap[r][c] == '@') {
                    System.out.print(oceanMap[r][c]);
                } else if (oceanMap[r][c] == 'x') {
                    System.out.print(oceanMap[r][c]);
                }
                else {
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
                System.out.print('o');      // print space char in column 1 to 9
            }
            System.out.print(" | "+ r);     // print pipe char with row number
            System.out.println();
        }
        System.out.println("\t0123456789");
        System.out.println();
    }
}
