// SwitchModernCaseLabels.java

/**
 * SwitchModernCaseLabels program class demonstrates a switch statement using the modern case syntax.
 *
 * @author  David Habermehl
 * @version Last modified 25_Oct_2022
 **/
class SwitchModernCaseLabels {

    enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; }

    public static void main( String[] args ) {
        System.out.printf( "\"Thursday\" has %d letters\n", lettersInDayName( Day.THURSDAY ) );
    }



    /**
     * lettersInDayName returns the number of letters in the name of the day passed as argument.
     *
     * @param   whichDay    Day to examine.
     * @return              Number of letters in examined day's name.
     */
    static int lettersInDayName( Day whichDay ) {
        int numLetters;
        switch( whichDay ) {
            case MONDAY, FRIDAY, SUNDAY -> { numLetters = 6; }
            case TUESDAY                -> { numLetters = 7; }
            case THURSDAY, SATURDAY     -> { numLetters = 8; }
            case WEDNESDAY              -> { numLetters = 9; }
            default                     -> { numLetters = 0; }
        }
        return numLetters;
    }
}
