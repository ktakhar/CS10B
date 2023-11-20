// ScannerDemo1.java

/**
 * This class demonstrates how to use the Scanner class to get input from the user's keyboard,
 * by prompting the user twice to input a number, then prompting the user to enter some text,
 // and then echoing the two numbers and the text.
 *
 * @author David Habermehl
 * @version Last modified 04_Oct_2008
 **/

// Tells compiler where to look to resolve references to Scanner class.
import java.util.Scanner;

class ScannerDemo1 {
    public static void main( String [] args ) {
        int firstNumber;
        double secondNumber;
        String someString;

        // Create a variable of type Scanner, named keyboard.
        // Point that variable at a newly constructed instance of the Scanner class.
        // Constructing an instance of the Scanner class with the argument System.in
        // means that that instance will be used to get input from the user's keyboard.
        Scanner keyboard = new Scanner( System.in );

        // Whenever the user types stuff on the keyboard and presses Enter, Scanner stores
        // what was typed in a buffer associated with the current "System.in" Scanner instance.

        // Whenever the program calls one of the "nextXxx()" methods on the current "System.in"
        // Scanner instance, Scanner retrieves the next whitespace-delimited token from the buffer,
        // converts it to, say, an int value, and returns that value to the program. The token is
        // removed from the buffer.

        // Because of the way Scanner manages its keyboard buffer, the user can input both tokens
        // on the same line, for convenience.

        System.out.print( "Type an int value for firstNumber: "); // Prompt the user
        firstNumber = keyboard.nextInt();                         // Get token from buffer, convert to int, remove token from buffer.

        System.out.print( "Type a double value for secondNumber: " );
        secondNumber = keyboard.nextDouble();                        // Get token from buffer, convert to int, remove token from buffer.

        System.out.print( "Type a String value for someString: " );
        someString = keyboard.next();                          // Get token from buffer, remove token from buffer.

        System.out.println( "\nfirstNumber is " + firstNumber );
        System.out.println( "secondNumber is " + secondNumber );
        System.out.println( "someString is " + someString );
    }
}