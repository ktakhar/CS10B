// PlaceholderDemo.java

/**
 * Demonstrates the placeholders used by the printf method to produce formatted output.
 * printf() requires at least one argument: a String that can include zero or more place-
 * holders, plus additional arguments that provide values to be substituted into those
 * placeholders. printf() prints that String after replacing its placeholders with their
 * corresponding values.
 *
 * Placeholders begin with a '%' character and end with a character that identifies the
 * placeholder's type (e.g. floating point number, boolean value, String, ...). In between
 * is data that is used to format the displayed value.
 *
 * printf() is not the only place that uses these placeholders. The String.format method ac-
 * cepts the same arguments but it returns the formatted String instead of printing it.
 *
 * @author David Habermehl
 * @version Last modified 11_Feb_2019
 **/

import java.util.Scanner;
class PlaceholderDemo {

    // Placeholders Used in printf

    // Code   Type                          Example
    // ----   ----------------------------  -----------------------
    // %b     boolean                       true
    // %c     char                          'a'
    // %d     signed decimal integer        123
    // %e     exponential floating-point    1.23E+01
    // %f     fixed floating-point          12.3
    // %n     platform-specific newline
    // %o     unsigned octal integer        173
    // %s     string                        Tax=
    // %x %X  unsigned hexadecimal integer  7b 7B
    // %%     output a percent sign         %


    // Format Flags Used in printf (they appear right after the %)

    // Flag   Meaning                       Example
    // ----   ----------------------------  -----------------------
    // -      left justification            1.23 followed by spaces
    // 0      show leading zeroes           0001.23
    // +      show + sign for # >= 0        +1.23
    // (      enclose # < 0 in parens       (1.23)
    // ,      show decimal separators       12,300


    // printf Width Examples

    // This   Means ...
    // ----   ---------------------------------------------------------------------
    // %6d    integer, 6 characters wide, right-aligned
    // %-6d   integer, 6 characters wide, left-aligned
    // %6.2f  floating point, 6 characters wide, 2 fractional digits, right-aligned

    public static void main( String [] args ) {
        runPlaceholderDemo();
    }



    /**
     * This method executes the Placeholder demo
     */
    static void runPlaceholderDemo() {

        Scanner keyboard = new Scanner( System.in ), stringScanner=null;
        String placeholder="", value="", temp;
        char placeholderLastChar;
        boolean done;

        // Process user input until user inputs 'q'
        do {
            // Prompt for and read placeholder token
            if ( placeholder.length() == 0 ) {
                System.out.print( "\n\nPlaceholder (q to quit): " );
            }
            else {
                System.out.printf( "\n\nPlaceholder (= for %s, q to quit): ", placeholder );
            }
            temp = keyboard.nextLine().trim();

            // if temp is "=", then use existing placeholder
            // if temp is not "=", then update placeholder
            if ( !temp.equals( "=" ) ) { placeholder = temp; }

            placeholderLastChar = placeholder.charAt( placeholder.length()-1 );
            done = Character.toLowerCase( placeholderLastChar ) == 'q';

            if ( !done ) {

                // Unless placeholder is ? or %n or %%, prompt for value to be fed to placeholder.
                // Note that the %n and %% placeholders don't use substituted values.
                if ( placeholderLastChar != '?' &&  placeholderLastChar != 'n' &&  placeholderLastChar!= '%' ) {

                    if ( value.length() == 0 ) {
                        System.out.print( "Value: " );
                    }
                    else {
                        System.out.printf( "Value (= for %s): ", value );
                    }
                    temp = keyboard.nextLine().trim();

                    // if temp is "=", then use existing value
                    // if temp is not "=", then update value
                    if ( !temp.equals( "=" ) ) { value = temp; }
                    stringScanner = new Scanner( value );
                }

                // Read  a value that's appropriate for the placeholder and pass that value to the demoPlaceholder method
                switch( placeholderLastChar ) {

                    case '?':     // help
                        System.out.println( "Placeholders Used in printf                      Format Flags Used in printf (they appear right after the %)" );
                        System.out.println( "" );
                        System.out.println( "Code   Type                          Example     Flag   Meaning                       Example" );
                        System.out.println( "----   ----------------------------  --------    ----   ----------------------------  -----------------------" );
                        System.out.println( "%b     boolean                       true        -      left justification            1.23 followed by spaces" );
                        System.out.println( "%c     char                          'a'         0      show leading zeroes           0001.23" );
                        System.out.println( "%d     signed decimal integer        123         +      show + sign for # >= 0        +1.23" );
                        System.out.println( "%e     exponential floating-point    1.23E+01    (      enclose # < 0 in parens       (1.23)" );
                        System.out.println( "%f     fixed floating-point          12.3        ,      show decimal separators       12,300" );
                        System.out.println( "%n     platform-specific newline" );
                        System.out.println( "%o     unsigned octal integer        173" );
                        System.out.println( "%s     string                        Tax=" );
                        System.out.println( "%x %X  unsigned hexadecimal integer  7b 7B" );
                        System.out.println( "%%     output a percent sign         %" );
                        break;

                    case 'b':     // boolean
                        Boolean booleanValue = getBoolean( stringScanner);
                        if ( booleanValue != null ) { demoBooleanPlaceholder( placeholder, booleanValue ); }
                        break;

                    case 'c':     // char
                        demoCharPlaceholder( placeholder, stringScanner.next().charAt(0) );
                        break;

                    case 'd':     // signed decimal integer
                    case 'o':     // unsigned octal integer
                    case 'x':     // unsigned hexadecimal integer
                    case 'X':
                        Integer intValue = getInt( stringScanner);
                        if ( intValue != null ) { demoIntegerPlaceholder( placeholder, intValue ); }
                        break;

                    case 'e':     // exponential floating-point
                    case 'f':     // fixed floating-point
                        Double doubleValue = getDouble( stringScanner);
                        if ( doubleValue != null ) { demoDoublePlaceholder( placeholder, doubleValue ); }
                        break;

                    case 'n':     // platform-specific newline
                    case '%':     // output a percent sign
                        demoNewlineAndPercentPlaceholders( placeholder );
                        break;

                    case 's':     // string
                        demoStringPlaceholder( placeholder, value );
                        break;

                    default:
                        System.out.println( "Invalid placeholder." );
                        break;
                }
            }

        } while( !done );
        System.out.println();
    }



    /**
     * Method to get a boolean value from the value String
     *
     * @param  stringScanner     Scanner object constructed from the value String
     * @return                   boolean value (null if value caused InputMismatchException)
     */
    static Boolean getBoolean( Scanner stringScanner ) {
        try {
            return stringScanner.nextBoolean();
        }
        catch( Exception e ) {
            System.out.println( e ) ;
            return null;
        }
    }



    /**
     * Method to get an int value from the value String
     *
     * @param  stringScanner     Scanner object constructed from the value String
     * @return                   int value (null if value caused InputMismatchException)
     */
    static Integer getInt( Scanner stringScanner ) {
        try {
            return stringScanner.nextInt();
        }
        catch( Exception e ) {
            System.out.println( e ) ;
            return null;
        }
    }



    /**
     * Method to get a double value from the value String
     *
     * @param  stringScanner     Scanner object constructed from the value String
     * @return                   double value (null if value caused InputMismatchException)
     */
    static Double getDouble( Scanner stringScanner ) {
        try {
            return stringScanner.nextDouble();
        }
        catch( Exception e ) {
            System.out.println( e ) ;
            return null;
        }
    }



    /**
     * Method to demo the %...b placeholder
     *
     * @param  placeholder     The placeholder String
     * @param  value           The boolean value to be substituted for the placeholder
     */
    static void demoBooleanPlaceholder( String placeholder, boolean value ) {
        displayResult( placeholder, ""+value, String.format( placeholder, value ) );
    }



    /**
     * Method to demo the %...c placeholder
     *
     * @param  placeholder     The placeholder String
     * @param  value           The char value to be substituted for the placeholder
     */
    static void demoCharPlaceholder( String placeholder, char value ) {
        displayResult( placeholder, "'"+value+"'", String.format( placeholder, value ) );
    }



    /**
     * Method to demo the %...d, %...o, %...x, %...X placeholders
     *
     * @param  placeholder     The placeholder String
     * @param  value           The int value to be substituted for the placeholder
     */
    static void demoIntegerPlaceholder( String placeholder, int value ) {
        try {
            displayResult( placeholder, ""+value, String.format( placeholder, value ) );
        }
        catch( Exception e ) {
            System.out.println( e ) ;
        }
    }



    /**
     * Method to demo the %...e, %...f placeholders
     *
     * @param  placeholder     The placeholder String
     * @param  value           The double value to be substituted for the placeholder
     */
    static void demoDoublePlaceholder( String placeholder, double value ) {
        displayResult( placeholder, ""+value, String.format( placeholder, value ) );
    }



    /**
     * Method to demo the %...s placeholder
     *
     * @param  placeholder     The placeholder String
     * @param  value           The String value to be substituted for the placeholder
     */
    static void demoStringPlaceholder( String placeholder, String value ) {
        displayResult( placeholder, "\""+value+"\"", String.format( placeholder, value ) );
    }



    /**
     * Method to demo the %n, %% placeholders
     *
     * @param  placeholder     The placeholder String
     */
    static void demoNewlineAndPercentPlaceholders( String placeholder ) {
        displayResult( placeholder, String.format( placeholder ) );
    }



    /**
     * Method to display the result of replacing the placeholder by its formatted value
     *
     * @param  placeholder     The placeholder String
     * @param  value           The String-ified value to be substituted for the placeholder
     * @param  result          The result of substituting the value for the placeholder, via String.format()
     */
    static void displayResult( String placeholder, String value, String result ) {
        System.out.printf( "String.format( \"%s\", %s ) yielded \"%s\"\n\n", placeholder, value, result );
    }



    /**
     * Method to display the %n and %% placeholders, that don't use substituted values.
     *
     * @param  placeholder     The placeholder String
     * @param  result          The result of substituting the value for the placeholder, via String.format()
     */
    // Display value returned by String.format( placeholder )
    // This version of displayResult is used for %...n and %...% placeholders, because they don't use values.
    static void displayResult( String placeholder, String result ) {
        System.out.printf( "String.format( \"%s\" ) yielded \"%s\"\n\n", placeholder, result );
    }
}
