// Prob4.java

/**
 * PSET3 - Problem 4
 * 
 * Try-catch block with multiple catches and exception handling
 * 
 * Java program repeatedly prompts the user to enter two integers 
 * Performs the division of the first integer by the second
 * Handles potential input errors (non-integer input and division by zero) until a successful division occurs.
 * 
 * TEST: 
 * int = 2 
 * int = 2
 * 
 * OUTPUT:
 * Result: 1
 * 
 * @author Kuljit Takhar
 * @version October 21 2023
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prob4{
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner keyboard = new Scanner(System.in);
        // Declare variables to store the two integers
        int n1, n2;

        // Loop until the user enters two integers and division is successful
        while (true) {
            try {
                // Prompt the user to enter the first integer
                System.out.print("Type an int: ");
                n1 = keyboard.nextInt();
                
                // Prompt the user to enter the second integer
                System.out.print("Now type another int: ");
                n2 = keyboard.nextInt();

                // Attempt to perform division
                int r = n1 / n2;

                // If division is successful, print the result and exit the loop
                System.out.println("Result: " + r);
                break;

            } catch (InputMismatchException e) {
                // Handle the case where non-integer input is provided
                System.out.println("Error: You must enter integers. Please try again.");
                keyboard.nextLine(); // Clear the input buffer
            } catch (ArithmeticException e) {
                // Handle the case where division by zero is attempted
                System.out.println("Error: Division by zero is not allowed. Please try again.");
            }
        }  keyboard.close();
    }  
}

