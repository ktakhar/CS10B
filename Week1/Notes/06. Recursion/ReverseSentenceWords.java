import java.util.Scanner;
class ReverseSentenceWords {
    public static void main( String [] args ) {
        String sentence = " David is name My";
        String reversedSentence = reverseTheSentence( sentence );
        System.out.printf("\nsentence=\"%s\"\nreversedSentence=\"%s\"\n", sentence, reversedSentence );
    }

    private static String reverseTheSentence( String sentenceToBeReversed ) {
        Scanner input = new Scanner( sentenceToBeReversed );
        System.out.printf( "Executing reverseTheSentence( \"%s\" )\n", sentenceToBeReversed );

        // "This is an example of a sentence."
        String currentToken = input.next();     // "This"
        if ( !input.hasNext() ) {
            // Base case: no additional tokens, so return the token we just retrieved.
            System.out.printf( "Base case. Returning currentToken=\"%s\"\n", currentToken );
            return currentToken;
        }
        else {
            String restOfString = input.nextLine(); // "is an example of a sentence."
            System.out.printf( "Recursive case. currentToken=\"%s\", restOfString=\"%s\", returning reverseTheSentence( \"%s\" ) + \" \" + \"%s\"\n", currentToken, restOfString, restOfString, currentToken );
            return reverseTheSentence( restOfString ) + " " + currentToken;
        }
    }
}