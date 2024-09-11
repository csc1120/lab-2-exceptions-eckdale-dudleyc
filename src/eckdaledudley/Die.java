/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Chloe Eckdale-Dudley
 * Last Updated: 9/11/2024
 */
package eckdaledudley;

import java.util.Random;

public class Die {

    public static final int MIN_SIDES = 2;
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();

    public Die(int numSides) {
        this.numSides = numSides;
        if(this.numSides > MAX_SIDES || this.numSides < MIN_SIDES) {
            throw new IllegalArgumentException("Out of range");
        }
    }

    public int getCurrentValue() {
        int sideUp = currentValue;
        currentValue = 0;

        if(sideUp < 1 || sideUp > numSides) {
            throw new DieNotRolledException("Die not rolled");
        }
        return sideUp;

    }
    public void roll() {
        currentValue = random.nextInt(1,numSides+1);
    }

}