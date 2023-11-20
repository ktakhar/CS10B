// Factorial.java

import java.util.Scanner;

/*
 * Program class to demonstrate recursively computing the factorial function
 */
class Factorial {
    public static void main( String [] args ) {
        Scanner keyboard = new Scanner( System.in );
        int number;
        do {
            System.out.print( "\n\nEnter integer # (negative # to quit): ");
            number = keyboard.nextInt();
            if ( number >= 0 ) {
                System.out.printf( "%d! = %d", number, factorial ( number ) );
            }
        } while( number >= 0 );
        System.out.println( "\n" );
    }



    /**
     * Recursively compute the factorial of a number.
     *
     * @param number The number whose factorial we will compute.
     *
     * @return The factorial of the argument.
     */
    static int factorial ( int number ) {

        // ...
        // 4! == 4*3*2*1 == 4*3!
        // 3! == 3*2*1   ==   3*2!
        // 2! == 2*1     ==     2*1!
        // 1! == 1          Base case
        // 0! == 1          Base case

        // Throw IllegalArgumentException for negative numbers
        if ( number < 0 ) { throw new IllegalArgumentException(); }

        // Base case returns a result without needing to
        // recursively call the factorial method.
        else if ( number <= 1 ) { return 1; }

        // Recursive case returns an intermediate result
        // and calls the factorial() method with an argument
        // that gets us closer to the base case.
        else { return number * factorial( number-1 ); }
    }
}