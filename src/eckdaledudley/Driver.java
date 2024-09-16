/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Chloe Eckdale-Dudley
 * Last Updated: 9/13/2024
 */
package eckdaledudley;

import javafx.application.Application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static final int MIN_DICE = 2;
    public static final int MAX_DICE = 10;
    private static final Scanner in = new Scanner(System.in);

    static int numDice;
    static int numSides;
    static int numRolls;

    public static void main(String[] args) {

        boolean validInput = false;
        while(!validInput) {
            try {
                getInput();
                validInput = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: All values must be whole numbers");
            } catch (InvalidNumSides | InputMismatchException e) {
                System.err.println(e.getMessage());
            }
        }
        Die[] dice = createDice(numDice,numSides);
        int[] rolls = rollDice(dice,numSides,numRolls);
        report(numDice,rolls,findMax(rolls));
    }

    /**
     * Asks the user for three numbers, number of dice to roll, num sides these dice will have, times to be rolled
     */
    private static void getInput() {

        System.out.println("""
                Please enter the number of dice to roll, how many sides the dice have,
                and how many rolls to complete, separating the values by a space.
                Example: "2 6 1000"
                """);

        System.out.println("Enter configuration:");
        String input = in.nextLine();
        String[] ans = input.split(" ");


        if (ans.length != 3) {
            throw new InputMismatchException("Invalid input: Expected 3 values");
        }

        if(Integer.parseInt(ans[1]) > MAX_DICE || Integer.parseInt(ans[1]) < MIN_DICE) {
            throw new InvalidNumSides("Bad die creation: Illegal number of sides: "+Integer.parseInt(ans[1]));
        }


        numDice = Integer.parseInt(ans[0]);

        numSides = Integer.parseInt(ans[1]);

        numRolls = Integer.parseInt(ans[2]);

    }



    /**
     * Will take as params the number of dice to create and the num sides the dice will have, returning an array of Die objects
     * @param numDice the number of dice to create
     * @param numSides the number of sides the dice will have
     * @return an array of Die objects
     */

    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for(int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }


    /**
     * This method will roll all the dice, total up the values, and add to that value's total, which will be done as many times as the user specifies
     * @param dice Takes in the array of dice
     * @param numSides take in the num sides of dice
     * @param numRolls take in the num of times the dice are to be rolled
     * @return returns an array of the die values
     */

    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {

        int min = numDice;
        int max = numDice*numSides;

        int[] rolls = new int[max - min +1];

        for(int i = 0; i < numRolls; i++) {
            int total = 0;
            for(Die die : dice) {
                die.roll();
                total += die.getCurrentValue();
            }
            rolls[total-min] += 1;
        }
        return rolls;
    }

    /**
     * Will take the array that contains the rolling statistics and find and return the largest count
     * @param rolls takes in the array that contains the rolling statistics
     * @return returns what the count is, the value of which has the largest count is not important
     */
    private static int findMax(int[] rolls) {

        int max = rolls[0];

        for (int roll : rolls) {
            if (roll > max) {
                max = roll;
            }
        }
        return max;
    }

    /**
     * @param numDice the numDice to be rolled
     * @param rolls the array holding roll values
     * @param max the max num rolls in the rolls array
     */
    private static void report(int numDice, int[] rolls, int max) {
        int scale = (max<10) ? 1 : max / 10;

        for (int roll : rolls) {

            int numStars = roll / scale;

            System.out.printf("%-2d : %5d       %s%n", numDice, roll, "*".repeat(Math.max(0, numStars)));

            numDice++;
        }
    }

}