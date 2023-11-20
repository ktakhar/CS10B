// Finally.java

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * The Finally class demonstrates that the finally block's code ALWAYS runs:
 *     1. When the try block executes normally
 *     2. When the try block throws an uncaught exception
 *     3. When the try block throws a caught exception and the catch block executes normally
 *     4. When the try block throws a caught exception and the catch block throws an exception
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
class Finally {

    public static void main( String [] args ) throws Exception {
        demoFinally();
    }



    /**
     * demoFinally Demontrates that the finally block's code runs even if a catch block throws an exception.
     */
    private static void demoFinally() throws Exception {
        String filename = "q";
        Scanner keyboard = new Scanner( System.in ), fileReader=null;
        do {
            System.out.print( "File: " );
            filename = keyboard.nextLine();
            if ( !filename.trim().startsWith( "q" ) ) {
                try {
                    fileReader = new Scanner( new File( filename ) );
                    System.out.printf( "Created Scanner from \"%s\"\n", filename );
                }
                catch( FileNotFoundException fnfe ) {
                    System.out.println( "In FileNotFoundException catch block" );

                    System.out.print( "Do you want the FileNotFoundException catch block to throw an exception? " );
                    if ( keyboard.nextLine().trim().startsWith( "y" ) ) { throw new Exception(); }
                }
                finally {
                    System.out.println( "Code in the finally block runs no matter what!" );
                }

                if ( fileReader == null ) { System.out.printf( "Failed to create Scanner from %s\n", filename ); }
            }
        } while( !filename.trim().startsWith( "q" ) );
    }
}