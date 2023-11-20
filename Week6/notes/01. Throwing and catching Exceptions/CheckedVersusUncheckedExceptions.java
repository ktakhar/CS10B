// CheckedVersusUncheckedExceptions.java

import java.io.File;
import java.io.*;
import java.util.Scanner;

/**
 * The CheckedVersusUncheckedExceptions class illustrates the difference between checked
 * and unchecked exceptions.
 *
 * Checked exceptions can occur even if your code is well-written, e.g. because they are
 * associated with conditions out of your control like files that were deleted or were
 * made read-only. The compiler insists that you handle checked exceptions.
 *
 * Unchecked exceptions can be avoided by well-written code, e.g. because well-written
 * code should never experience a String index out of bounds error. The compiler lets
 * you choose not to handle unchecked exceptions.
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
class CheckedVersusUncheckedExceptions {

    public static void main( String [] args ) throws FileNotFoundException {

        demoCheckedException();
        //demoUncheckedException();

    }



    /**
     * demoCheckedException triggers a checked exception. Checked means
     * compiler insists that we check for it.
     */

    // Using the "throws" clause is the "lazy" way to satisfy the compiler that
    // we're accounting for the checked exception FileNotFound.

    //private static void demoCheckedException() throws FileNotFoundException {
    private static void demoCheckedException() {

        Scanner keyboard = new Scanner( System.in );
        String filename;
        boolean weNeedToContinue;

        do {
            filename = promptForStringValue( keyboard, "\nPlease type a filename and press enter: " );
            weNeedToContinue = false;
            //try {
                Scanner fileReader = new Scanner( new File( filename ) );
            //}
            //catch( FileNotFoundException fnfe) {
            //    weNeedToContinue = true;
            //    System.err.printf( "\nCaught FileNotFoundException: \"%s\"", fnfe );
            //}
        } while( weNeedToContinue );
        System.out.printf( "\n%s is an existing file.\n\n", filename );
    }



    /**
     * demoUncheckedException triggers an unchecked exception. Unchecked means
     * compiler doesn't care if we don't check for it.
     */
    private static void demoUncheckedException() {
        Scanner keyboard = new Scanner( System.in );
        String str;
        int position;
        boolean weNeedToContinue;
        char ch = 0;

        do {
            str = promptForStringValue( keyboard, "\nString: " );
            position = promptForIntValue( keyboard, String.format( "\nPlease type the position of the char to retrieve from \"%s\": ", str ) );
            weNeedToContinue = false;
//            try {
                ch = str.charAt( position );
//            }
//            catch( StringIndexOutOfBoundsException e ) {
//                weNeedToContinue = true;
//                System.err.printf( "\nCaught Exception: %s\n\n", e );
//                e.printStackTrace();
//            }
        } while( weNeedToContinue );
        System.out.printf( "\"%s\".charAt( %d ) = %c\n\n", str, position, ch );
    }



    /**
     * promptForIntValue prompts the user and returns the int response.
     *
     * @param  keyboard   Scanner used to read response.
     * @param  prompt     String used to prompt the user.
     * @return int    containing user's response.
     */
    private static int promptForIntValue( Scanner keyboard, String prompt ) {
        System.out.print( prompt );
        return keyboard.nextInt();
    }



    /**
     * promptForStringValue prompts the user and returns the String response.
     *
     * @param  keyboard   Scanner used to read response.
     * @param  prompt     String used to prompt the user.
     * @return String containing user's response.
     */
    private static String promptForStringValue( Scanner keyboard, String prompt ) {
        System.out.print( prompt );
        return keyboard.nextLine();
    }
}