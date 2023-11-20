//ReverseStringLettersVerbose.java

import java.util.Scanner;
class ReverseStringLettersVerbose {

    public static void main( String [] args ) {
        Scanner keyboard = new Scanner( System.in );
        String str, strReversed;

        System.out.print( "\n\nType some characters (ctrl-z (windows) or ctrl-d (mac) to quit) and press enter: ");
        while( keyboard.hasNextLine() ) {
            str = keyboard.nextLine();
            System.out.printf( "\nmain: calling reverseString( \"%s\" )\n", str );
            strReversed = reverseString( str, "    " );
            System.out.printf( "\nmain: reverseString( \"%s\" ) is \"%s\"\n\n", str, strReversed );

            System.out.print( "\n\nType some characters (ctrl-z (windows) or ctrl-d (mac) to quit) and press enter: ");
        }
        System.out.println( "\nBye!" );
    }



    static String reverseString( String str, String indentation ) {
        System.out.printf( "\n%sreverseString( \"%s\" ):\n", indentation, str );
        System.out.printf( "%s           The String passed as the argument is \"%s\"\n", indentation, str );

        // // Base case: String's length <= 1
        if ( str.length() <= 1 ) {
            System.out.printf( "%s           Base case reached!\n", indentation );
            System.out.printf( "%s           Returning \"%s\"\n", indentation, str );
            return str;
        }

        // Recursive case: String's length > 1
        else {
            char   firstChar = str.charAt( 0 );
            String restOfString = str.substring( 1 );

            System.out.printf( "%s           Local variable firstChar is '%c'\n", indentation, firstChar );
            System.out.printf( "%s           Calling reverseString( \"%s\" )\n", indentation, restOfString );

            String restOfStringReversed = reverseString( restOfString, indentation+"    " );
            String result = restOfStringReversed + firstChar;

            System.out.printf( "\n%sreverseString( \"%s\" ):", indentation, str );
            System.out.printf( "\n%s           reverseString( \"%s\" ) returned \"%s\".", indentation, restOfString, restOfStringReversed );
            System.out.printf( "\n%s           result =  \"%s\" = \"%s\" + local variable firstChar ('%c')", indentation, result, restOfStringReversed, firstChar );
            System.out.printf( "\n%s           Returning \"%s\"\n", indentation, result );

            return result;
        }
    }
}