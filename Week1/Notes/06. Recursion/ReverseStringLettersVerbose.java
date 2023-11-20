class ReverseStringLettersVerbose {

    public static void main( String [] args ) {
        String str = "abc",
               strReversed;

        System.out.printf( "\nmain: calling reverseString( \"%s\" )\n", str );

        strReversed = reverseString( str, "    " );

        System.out.printf( "\nmain: reverseString( \"%s\" ) is %s\n\n", str, strReversed );
    }

    static String reverseString( String str, String indentation ) {
        System.out.printf( "\n%sreverseString: The String passed as the argument is \"%s\"\n", indentation, str );

        // // Base case: String's length <= 1
        if ( str.length() <= 1 ) {
            System.out.printf( "%s               Base case reached! Returning \"%s\"\n", indentation, str );
            return str;
        }

        // Recursive case: String's length > 1
        else {
            char   firstChar = str.charAt( 0 );
            String restOfString = str.substring( 1 );

            System.out.printf( "%s               Local variable firstChar is '%c'\n", indentation, firstChar );
            System.out.printf( "%s               (each method call has its own stack frame to hold its local variables)\n", indentation );
            System.out.printf( "%s               Calling reverseString( \"%s\" )\n", indentation, restOfString );

            String restOfStringReversed = reverseString( restOfString, indentation+"    " );

            String result = restOfStringReversed + firstChar;
            System.out.printf( "\n%sreverseString: reverseString( \"%s\" ) returned \"%s\".", indentation, restOfString, restOfStringReversed );
            System.out.printf( "\n%s               Concatenating \"%s\" with local variable firstChar ('%c') to return \"%s\"\n", indentation, restOfStringReversed, firstChar, result );
            return result;
        }
    }
}