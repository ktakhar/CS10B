// RegexTestHarness.java

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * program class facilitates experimentation with regular expressions.
 *
 * See https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * for an excellent discussion of regular expressions
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
public class RegexTestHarness {

    public static void main( String[] args ){
        runRegexTestHarness();
    }



    /**
     * Method executes a loop that reads a regexp, reads a string to search for substrings that match the regexp,
     * and displays all of the matches. The program terminates when the suplied regexp is "quit".
     */
    static void runRegexTestHarness() {

        Scanner keyboard = new Scanner( System.in );

        // Loop reads a regex and a String to search, and displays all matching substrings.
        String regex, stringToSearch = null;
        do {
            System.out.print( "\nEnter your regex (or quit): " );
            regex = keyboard.nextLine();
            if ( !"quit".equals( regex.toLowerCase().trim() ) ) {

                // Compile the regexp to produce a matchable Pattern object.
                boolean compiledOK = true;
                Pattern pattern = null;
                try {
                    pattern = Pattern.compile( regex );
                }
                catch( Exception e ) {
                    compiledOK = false;
                    System.err.printf( "%s\n", e );
                }

                // If the compilation succeeded, read a stringToSearch and find all stringToSearch substrings that match the regexp.
                if ( compiledOK ) {
                    System.out.println( "                                        1         2         3" );
                    System.out.println( "                              0123456789012345678901234567890123456789" );
                    System.out.print( "Enter input string to search: " );
                    stringToSearch = keyboard.nextLine();

                    boolean found = false;
                    Matcher matcher = pattern.matcher( stringToSearch );
                    System.out.print( "\nMatches: " );
                    while ( matcher.find() ) {
                        System.out.printf( "%2d-%2d: \"%s\"\n         ",
                                           matcher.start(), matcher.end()-1,matcher.group() );
                        found = true;
                    }
                    if( !found ){
                        System.out.println( "No match found." );
                    }
                }
            }
        } while( !"quit".equals( regex.toLowerCase().trim() ) );

        System.out.println( "\nBye!\n" );
    }
}
