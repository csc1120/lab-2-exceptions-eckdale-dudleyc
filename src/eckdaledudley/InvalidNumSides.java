/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Chloe Eckdale-Dudley
 * Last Updated: 9/13/2024
 */
package eckdaledudley;

public class InvalidNumSides extends RuntimeException{

    public InvalidNumSides(String message) {
        super(message);
    }
}
