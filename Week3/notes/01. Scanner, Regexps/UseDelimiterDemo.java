// UseDelimiterDemo.java

// Delimiters to retrieve words: \s+          = Any whitespace character, works like next()
//                               [^a-zA-Z']+  = Any char that's not a letter or apostrophe
//                               [^\w]+       = Any character that's not a word character
//                               [^\w']+      = Any character that's not a word character or apostrophe

// Try them on "I'll daydream 'til I  remember 10 tunes' lyrics!"

import java.util.Scanner;

/**
 * Program class that demonstrates the Scanner class's useDelimiter() instance method.
 *
 * @author David Habermehl
 * @version Last modified 10_Feb_2019
 **/
class UseDelimiterDemo {

    public static void main(String [] args) {
        runUseDelimiterDemo();
    }



    /**
     * This method executes the UseDelimiter demo
     */
    static void runUseDelimiterDemo() {

        boolean done;
        String temp, delimiterString="", delimiter, sentence="", word="";
        Scanner keyboard = new Scanner( System.in ), sentenceScanner;

        // Get a delimiter String, get a sentence, using the delimiter String display
        // the sentence's words. Repeat until delimiterString starts with "q".
        do {
            // Prompt for and read delimiter token
            if ( delimiterString.length() == 0 ) {
                System.out.print( "\n\ndelimiter (\"\" for empty String, q to quit): " );
            }
            else {
                System.out.printf( "\n\ndelimiter (\"\" for empty String, = for %s, q to quit): ", delimiterString.equals( "\"\"" ) ? "empty String" : delimiterString );
            }
            temp = keyboard.nextLine().trim();
            done = Character.toLowerCase( temp.charAt( 0 ) ) == 'q';

            if ( !done ) {

                // if temp is "=", then use existing delimiterString
                // if temp is not "=", then update delimiterString
                if ( !temp.equals( "=" ) ) { delimiterString = temp; }

                // Special case delimiterString.equals( "\"\"" )
                if ( delimiterString.equals( "\"\"" ) ) {
                    delimiter = "";
                }
                else {
                    delimiter = delimiterString;
                }
                //delimiter = "[^a-zA-Z']+"; // get words using non-(lettersOrApostrophes) delimiter

                if ( sentence.length() == 0 ) {
                    System.out.print( "Sentence: " );
                }
                else {
                    System.out.printf( "Sentence (= for %s): ", sentence );
                }
                temp = keyboard.nextLine().trim();

                // if temp is "=", then use existing sentence
                // if temp is not "=", then update sentence
                if ( !temp.equals( "=" ) ) { sentence = temp; }

                sentenceScanner = new Scanner( sentence );
                sentenceScanner.useDelimiter( delimiter );

                // Retrieve and display each word from the sentence
                while( sentenceScanner.hasNext() ) {
                    word = sentenceScanner.next();
                    System.out.printf( "\t\"%s\"\n", word );
                }
            }
        } while( !done );
        System.out.println();
    }
}
