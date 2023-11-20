// Permutations.java

/**
 * CSCI E-10B Problem Set 1, problem 9: Write a recursive method named listPermutations that accepts
 * a String as its only parameter and prints the complete set of permutations from that String.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
class Permutations {
  public static void main (String [] args) {
     listPermutations ( "ABC" );
  }



    /**
     * listPermutations prints the complete set of permutations from its String argument.
     *
     * @param   string      String whose permutations are to be printed.
     * @param   prefix      Optional String used to communicate with recursive calls.
     */
    static void listPermutations (String string, String ... prefix) {

        // For very first call, assign "" to missing optional arg
        if (prefix.length == 0) {
            prefix = new String[1];
            prefix[0] = "";
        }

        // Base case: required arg's length is zero
        if ( string.length() == 0 ) { System.out.println( prefix[0] ); }

        // Recursive case:
        //     1. Construct substring by removing a char from the string arg,
        //     2. Append removed char to prefix arg,
        //     3. Recursively pass shortened substring and lengthened prefix
        //        to listPermutations. So each recursive call receives a
        //        string arg that is one char shorter, and a prefix arg that
        //        is one char longer. Eventually the string arg's length
        //        becomes zero and the prefix arg's length is the length of
        //        the original string arg. That's the base case and we print
        //        the prefix arg.
        else {
            char ch;
            String restOfString;
            for ( int i=0; i<string.length(); i++ ) {
                ch = string.charAt( i );
                restOfString = string.substring( 0, i ) + string.substring( i+1 );
                listPermutations( restOfString, prefix[0]+ch );
            }
        }
    }
}
