// Prob1.java

/**
 * PSET 3 - Problem 1
 * 
 * Program that outputs all command-line arguments, one per line, in reverse order.
 * 
 * TEST: 
 * hello world
 * 
 * OUTPUT: 
 * command line in reverse order:
 * world
 * hello
 * 
 * 
 * @author Kuljit Takhar
 * @version October 15 2023
 * 
 */

public class Prob1 {
    public static void main(String[] args) {
        // Check if there are command line arguments
        if (args.length == 0) {
            // if there are no command line arguments, print message
            System.out.println("No command line arguments found.");
        } else {
            System.out.println("Command line arguments in reverse order:");
            // Iterate through the arguments in reverse order
            for (int i = args.length - 1; i >= 0; i--) {
                // Print the argument
                System.out.println(args[i]);
            }
        }
    }
}