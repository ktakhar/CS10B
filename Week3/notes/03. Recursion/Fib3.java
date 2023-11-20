import java.util.Scanner;
import java.util.Arrays;

/*
 * Program class to demonstrate show how inefficient it is to recursively compute the factorial function
 */
class Fib3 {

    static final int MAXARG = 44;

    // NORMALLY WE PROHIBIT NON-FINAL CLASS-LEVEL VARIABLES IN PROGRAM CLASSES. WE MAKE
    // AN EXCEPTION HERE BECAUSE IT'S THE ONLY PRACTICAL WAY TO COUNT RECURSIVE CALLS.
    static int[] totalCallsPerArg = new int[MAXARG+1];

    public static void main( String[] args ) {

        Scanner keyboard = new Scanner( System.in );
        int arg;
        long answer, totalCalls;

        do {
            System.out.printf( "\nWhich Fibonacci # (<=%d)? ", MAXARG );
            arg = keyboard.nextInt();
            if ( arg >= 0 ) {
                if ( arg <= MAXARG ) {
                    Arrays.fill( totalCallsPerArg, 0 );
                    totalCalls=0;
                    answer = fib( arg );
                    System.out.printf( "fib( %d ) = %d\n", arg, answer );

                    // Display total times fib was called for each argument value
                    for ( int i=arg; i>=0; i-- ) {
                        totalCalls += totalCallsPerArg[i];
                        System.out.printf( "%5d. %,13d\n", i, totalCallsPerArg[i] );
                    }

                    // Display total time fib was called overall
                    System.out.printf( "%s: %,13d\n", "Total", totalCalls );
                }
                else { System.out.printf( "Input must be <= %d\n", MAXARG ); }
            }
        } while ( arg >= 0 );
        System.out.println( "Bye" );
    }



    /**
     * Recursive fibonacci method
     *
     * @param  arg     int value identifying which number in the fibonacci sequence to compute.
     * @return         That entry in the fibonacci sequence.
     */
    static int fib( int arg ) {
        totalCallsPerArg[arg]++;
        if ( arg == 0 )      { return 0; }
        else if ( arg == 1 ) { return 1; }
        else                 { return fib( arg-1 ) + fib( arg-2 ); }
    }
}
