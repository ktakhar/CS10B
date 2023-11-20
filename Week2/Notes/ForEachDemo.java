/**
 * This class demonstrates for each loops.
 *
 * @author David Habermehl
 * @version Last modified 01_February_2019
 **/
class ForEachDemo {
    public static void main( String [] args ) {

        int [] arrayOfInts = { 10, 29, 53 };

        for ( int i=0; i<arrayOfInts.length; i++ ) {
            System.out.printf( "%d ", arrayOfInts[i] );
        }
        System.out.println( "\n" );

        for ( int n : arrayOfInts ) {
            System.out.printf( "%d ", n );
        }
        System.out.println( "\n" );
    }
}