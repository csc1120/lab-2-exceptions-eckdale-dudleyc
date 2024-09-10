/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Chloe Eckdale-Dudley
 * Last Updated: 9/10/2024
 */
package eckdaledudley;

import java.util.Scanner;

public class Driver {

    public static final int MIN_DICE = 2;
    public static final int MAX_DICE = 10;
    private static final Scanner in = new Scanner(System.in);
    private static int numDice;
    private static int numSides;
    private static int numRolls;
    private static int total;

    public static void main(String[] args) {

        boolean validInput = false;
        while(!validInput) {
            try {
                int[] inputs = getInput();
                validInput = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: All values must be whole numbers");
            } catch (InvalidNumSides | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    /**
     * Asks the user for three numbers, number of dice to roll, num sides these dice will have, times to be rolled
     * @return these values in the same order as entered as an array
     */
    private static int[] getInput() {

        System.out.println("""
                Please enter the number of dice to roll, how many sides the dice have,
                and how many rolls to complete, separating the values by a space.
                Example: "2 6 1000"
                """);

        System.out.println("Enter configuration:");
        String input = in.nextLine();
        String[] ans = input.split(" ");

        if (ans.length != 3) {
            throw new IllegalArgumentException("Invalid input: Expected 3 values");
        }

        if(Integer.parseInt(ans[1]) > MAX_DICE || Integer.parseInt(ans[1]) < MIN_DICE) {
            throw new InvalidNumSides("Bad die creation: Illegal number of sides: "+Integer.parseInt(ans[1]));
        }

        numDice = Integer.parseInt(ans[0]);

        numSides = Integer.parseInt(ans[1]);

        numRolls = Integer.parseInt(ans[2]);

        return new int[]{numDice, numSides, numRolls};
    }



    /**
     * Will take as params the number of dice to create and the num sides the dice will have, returning an array of Die objects
     * @param numDice the number of dice to create
     * @param numSides the number of sides the dice will have
     * @return an array of Die objects
     */

    //CHECK
    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for(int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

}