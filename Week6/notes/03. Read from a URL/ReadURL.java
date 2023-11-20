// ReadURL.java

import java.util.Scanner;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;

/**
 * ReadURL program class demonstrates reading a remote file.
 *
 * @author  David Habermehl
 * @version Last modified 05_Mar_2019
 **/
class ReadURL {

    public static void main( String[] args ) {
        Scanner keyboard  = new Scanner( System.in );
        Scanner inputFileReader = getUrlReader( keyboard );
        readFile( inputFileReader );
    }



    /**
     * getUrlReader prompts user for url and returns Scanner constructed from that url,
     * repeating until a well-formed url is provided. If the well-formed url throws an
     * IOException, the program terminates
     *
     * @param  keyboard   Scanner used to read response.
     * @return Scanner object for url.
     */
    private static Scanner getUrlReader( Scanner keyboard ) {

        URL url = null;
        String urlString;
        InputStream urlInputStream = null;

        do {
            // E.g. "https://www.thecrimson.com"
            urlString = promptForStringValue( keyboard, "URL: " );

            // URL constructor throws a MalformedURLException.
            // That's a "checked" exception, so compiler insists that we handle it.
            try {
                url = new URL( urlString );
            }
            catch( MalformedURLException mue ) {
                System.err.printf( "%s is not a well-formed url\n\n", urlString );
            }
        } while( url == null );

        // openStream() throws an IOException.
        // That's a "checked" exception, so compiler insists that we handle it.
        try {
            urlInputStream = url.openStream();
        }
        catch( IOException ioe ) {
            System.err.printf( "%s cannot be opened for reading. Exiting program.\n\n", urlString );
            System.exit( -1 );
        }

        return new Scanner( urlInputStream );
    }



    /**
     * readFile reads a text file
     *
     * @param  inputFileReader   Scanner used to read input file.
     */
    private static void readFile( Scanner inputFileReader ) {

        int lineNumber = 1;
        String nextLine;

        // As long as input file has more lines, read the line, echo it to screen,
        // and write it to output file.
        while( inputFileReader.hasNextLine() ) {
            nextLine = inputFileReader.nextLine();
            System.out.printf( "%4d  %s\n", lineNumber++, nextLine );
        }

        // Close the Scanner.
        inputFileReader.close();
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
