// ScannerDemo2.java

/**
 * This class demonstrates how NOT to use the Scanner class to get input from the user's keyboard.
 *
 * It prompts the user twice to input an int, and then echoes the two ints. But it uses a new
 * Scanner instance to get the second int, which breaks the convenience of typing ahead.
 *
 * DON'T CREATE A NEW SCANNER INSTANCE EACH TIME YOU WANT TO RETRIEVE ANOTHER TOKEN, BECAUSE THAT
 * BREAKS THE CONVENIENCE OF TYPING AHEAD.
 *
 * @author David Habermehl
 * @version Last modified 04_Oct_2008
 **/

// Tells compiler where to look to resolve references to Scanner class.
import java.util.Scanner;

class ScannerDemo2 {
    public static void main( String [] args ) {
        int firstInt, secondInt;

        Scanner keyboard1 = new Scanner( System.in );
        System.out.print( "Type an int value for firstInt: "); // Prompt the user
        firstInt = keyboard1.nextInt();                        // get token from buffer, convert to int, remove token from buffer.

        // By creating a second Scanner instance to get the second token, we lose any type-ahead
        // that might have been done in response to the first prompt.
        Scanner keyboard2 = new Scanner( System.in );
        System.out.print( "Type an int value for secondInt: ");
        secondInt = keyboard2.nextInt();

        System.out.println( "\nfirstInt is " + firstInt );
        System.out.println( "secondInt is " + secondInt );
    }
}