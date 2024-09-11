/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Chloe Eckdale-Dudley
 * Last Updated: 9/11/2024
 */
package eckdaledudley;

import java.util.Scanner;

public class Driver {

    /*
    - Implement `rollDice()` and `findMax()` methods
    - In the `main()` method, create the dice, run the experiment, and find the max value count
     */

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
                int[] inputs = getInput();
                validInput = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: All values must be whole numbers");
            } catch (InvalidNumSides | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        //create the dice
        Die[] dice = createDice(numDice,numSides);
        //roll the die
        int[] rolls = rollDice(dice,numSides,numRolls);
        //Find the max in rolls[]
        int max = findMax(rolls);
        System.out.println("Max: "+max);

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
            System.out.println("Total rolled: "+total);
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

}