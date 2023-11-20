// Factorial.java

import java.util.Scanner;

/*
 * Program class to demonstrate recursively computing the factorial function.
 * Display useful output for tracking the recursive processing.
 */
class FactorialVerbose {

    public static void main( String [] args ) {
        Scanner keyboard = new Scanner( System.in );
        int number, numberFactorial;
        do {
            System.out.print( "\nEnter integer # (negative # to quit): ");
            number = keyboard.nextInt();
            if ( number >= 0 ) {
                System.out.printf( "\nmain: calling factorial( %d )\n", number );
                numberFactorial = factorial( number, "    " );
                System.out.printf( "\nmain: factorial( %d ) is %d\n\n", number, numberFactorial );
            }
        } while( number >= 0 );
    }



    /**
     * Recursively compute the factorial of a number.
     *
     * @param number The number whose factorial we will compute.
     *
     * @return The factorial of the argument, or -1 if the argument is negative.
     */
    static int factorial ( int number, String indentation ) {
        System.out.printf( "\n%sfactorial: The number passed as the argument is %d\n", indentation, number );

        if ( number < 0 ) {
            System.out.printf( "%s               Negative argument! Returning %d\n", indentation, -1 );
            return -1;
        }

        // Base case returns a result without needing to
        // recursively call the factorial method.
        else if ( number <= 1 ) {
            System.out.printf( "%s               Base case reached! Returning %d\n", indentation, 1 );
            return 1;
        }

        // Recursive case returns an intermediate result
        // and calls the factorial() method with an argument
        // that gets us closer to the base case.
        else {
            System.out.printf( "%s               Local variable number is %d\n", indentation, number );
            System.out.printf( "%s               (each method call has its own stack frame to hold its local variables)\n", indentation );
            System.out.printf( "%s               Calling factorial( %d )\n", indentation, number-1 );

            int intermediateResult = factorial( number-1, indentation+"    " );

            int result = intermediateResult * number;
            System.out.printf( "\n%sfactorial: factorial( %d ) returned %d", indentation, number-1, intermediateResult );
            System.out.printf( "\n%s               Multiplying %d by local variable number (%d) to return %d\n", indentation, intermediateResult, number, result );
            return result;
        }
    }
}