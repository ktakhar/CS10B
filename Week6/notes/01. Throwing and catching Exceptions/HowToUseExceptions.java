// HowToUseExceptions.java

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The HowToUseExceptions program class shows how to properly use exceptions.
 * 1. It demonstrates the right way to use exceptions,
 * 2. It demonstrates the wrong way to use exceptions.
 * 3. It demonstrates the right way to use exceptions with an example that
 *    recovers from the exception.
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
class HowToUseExceptions {
    public static void main( String [] args ) {

        //Scanner keyboard = new Scanner( System.in );
        Scanner keyboard = new Scanner( System.in );

        // 1. RIGHT WAY:
        rightWay( keyboard );

        // 2. WRONG WAY:
        //wrongWay( keyboard );

        // 3. RIGHT WAY WITH RECOVERY:
        //rightWayWithRecovery( keyboard );
    }



    /**
     * rightWay() demonstrates the right way to use exceptions:
     *     1. Program executes potentially problematic code in a try block.
     *     2. SomeoneElse (Java or a template class) detects the error condition.
     *     3. SomeoneElse throws the exception when it detects the problematic situation.
     *     4. Program catches the exception that *Someone Else* just threw.
     *
     * @param  keyboard   Scanner used to read from keyboard.
     */
    private static void rightWay( Scanner keyboard ) {
        System.out.println( "rightWay() demonstrates how a program class should deal with exception:\n\n    1. Put the code that might throw an exception into a try block\n    2. Let \"someone else\" detect any errors and throw any exceptions." );

        String filePath = promptForStringValue( keyboard, "\nFile to open: " );
        File   file = new File( filePath );

        try {
            Scanner fileToRead = new Scanner( file );
            System.out.printf( "\n    - Successfully create a Scanner object to read %s\n\n", filePath );
        }
        catch ( FileNotFoundException fnfe ) {
            System.out.printf( "\n    - Program caught the FileNotFoundException that *SomeoneElse* just threw:\n          %s\n\n", fnfe );
        }
        System.out.println( "Contnuing execution!" );
    }



    /**
     * wrongWay() demonstrates the wrong way for a program to use exceptions:
     *     1. Program executes potentially problematic code in a try block.
     *     2. The program manually detects the error condition instead of letting Java or a template class detect the error.
     *     3. The program manually throws the exception that Java or a template class would have thrown.
     *     4. The program catches the exception that it just threw.
     *
     * @param  keyboard   Scanner used to read from keyboard.
     */
    private static void wrongWay( Scanner keyboard ) {
        System.out.println( "wrongWay() demonstrates how a program class should NOT deal with exception:\n\n    1. Put the code that might throw an exception into a try block\n    2. Manually do its own check for the error condition\n    3. Throw its own exception from inside the try block when it\n       manually detects the error condition." );

        String filePath = promptForStringValue( keyboard, "\nFile to open: " );
        File   file = new File( filePath );

        try {
            System.out.println( "\n    - Program class is manually checking for the error condition." );
            if ( !file.exists() ) {
                System.out.println( "    - Program class detected the error condition, so it is manually\n      throwing the exception." );
                throw new FileNotFoundException();
            }
            else {
                Scanner fileToRead = new Scanner( file );
            }
            System.out.printf( "    - Successfully create a Scanner object to read %s\n\n", filePath );

        }
        catch ( FileNotFoundException fnfe ) {
            System.out.printf( "    - Program class caught the exception that it just manually threw:\n          %s\n\n", fnfe );
        }
    }



    /**
     * rightWayWithRecovery() demonstrates use of exceptions with error recovery
     *     1. Program executes potentially problematic code in a try block.
     *     2. SomeoneElse (Java or a template class) detects the error condition.
     *     3. SomeoneElse throws the exception when it detects the problematic situation.
     *     4. Program catches the exception that *Someone Else* just threw and tries again.
     *
     * @param  keyboard   Scanner used to read from keyboard.
     */
    private static void rightWayWithRecovery( Scanner keyboard ) {
        String  filePath;
        File    file;
        boolean caughtAnException;

        do {
            filePath = promptForStringValue( keyboard, "\nFile to open: " );
            file = new File( filePath );
            try {
                Scanner fileToRead = new Scanner( file );
                System.out.printf( "Successfully create a Scanner object to read %s\n\n", filePath );
                caughtAnException = false;
            }
            catch ( FileNotFoundException fnfe ) {
                System.out.println( "Caught java.io.FileNotFoundException" );
                caughtAnException = true;
            }
        } while( caughtAnException );

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