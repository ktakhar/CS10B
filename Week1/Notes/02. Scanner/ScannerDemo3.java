// ScannerDemo3.java

/**
 * This class demonstrates how to use the Scanner class's hasNextXxx() methods to avoid
 * InputMismatch exceptions.
 *
 * @author David Habermehl
 * @version Last modified 04_Oct_2008
 **/

// Tells compiler where to look to resolve references to Scanner class.
import java.util.Scanner;

class ScannerDemo3 {
    public static void main( String [] args ) {
        int firstInt;
        Scanner keyboard = new Scanner( System.in );

        // Each loop iteration sets this to true if we got bad input, false if we got good input.
        boolean gotBadInput;

        // New looping construct. Execute the body of the loop at least once. After each iteration
        // evaluate a boolean expression. If the expression is true, iterate again. If the expression
        // is false, do not iterate again.
        do {
            System.out.print( "Type an int value for firstInt: ");

            // hasNextXxx() returns true if the next token can be converted to an Xxx.
            // It returns false if the next token cannot be converted to an Xxx.
            if ( keyboard.hasNextInt() ) {

                // Get here if next token can be converted to an int. That's good!
                gotBadInput = false;
                firstInt = keyboard.nextInt();
                System.out.println( "firstInt is " + firstInt );
            }
            else {

                // Get here if next token can't be converted to an int. That's bad!
                gotBadInput = true;
                System.out.println( "Hey! That wasn't an int!!" );

                // IMPORTANT: To avoid an infinite loop, we need to remove the offending
                // token from the buffer. nextLine() clears the buffer. Since we're just
                // discarding the buffer's contents, we call nextLine() without putting
                // it on the right-hand side of an assignment statement.
                keyboard.nextLine();
            }
        } while( gotBadInput ); // Iterate again if gotBadInput evaluates to true.
    }
}