/**
 * Program class to demonstrate how to count the number of recursive calls.
 */
class CountingRecursiveCalls {
    static int counter = 0;
    public static void main( String [] args ) {
        int fact = factorial(0);
        System.out.printf( "fact=%d, counter=%d\n", fact, counter );
    }

    static int factorial( int num ) {
        counter++;
        if ( num < 0 ) { return -1; }
        else if ( num == 0 || num == 1 ) { return 1; }
        else {
            return num * factorial( num-1 );
        }
    }
}
