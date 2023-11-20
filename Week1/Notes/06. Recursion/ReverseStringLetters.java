// ReverseStringLetters.java

/*
 * Program class to demonstrate recursively reversing the characters in a String.
 */
class ReverseStringLetters {

    public static void main ( String [] args ) {

        String s         = "abcdefghij";
        String sReversed = reverseString ( s );

        System.out.printf ( "\nreverseString( \"%s\" ) is \"%s\"\n\n", s, sReversed );
    }



    /**
     * Recursively reversing the characters in a String
     *
     * @param str The String whose characters we will reverse.
     *
     * @return The reversed String.
     */
    static String reverseString ( String str ) {

        // Base case: String's length <= 1
        if ( str.length() <= 1 ) { return str; }

        // Recursive case: String's length > 1
        else {
            char   firstChar = str.charAt ( 0 );
            String restOfString = str.substring ( 1 );

            String restOfStringReversed = reverseString ( restOfString );

            String result = restOfStringReversed + firstChar;

            return result;
        }
    }
}