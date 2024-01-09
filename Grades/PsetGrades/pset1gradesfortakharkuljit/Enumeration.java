//@@ 4 out of 6 points

//@@     o You moved the enum to inside of the class. You weren't supposed to make
//@@       any changes outside of the switch body. I temporarily patched your code
//@@       to run my tests.
//@@     o You You changed the enum's name from Months to Month. You weren't
//@@       supposed to make any changes outside of the switch body. I temporarily
//@@       patched your code to run my tests.

//@@ Passed all 15 regular credit daysInMonth() tests:

//@@  1. Passed daysInMonth ( JAN, 2016 ) test.       Expected: 31       Received: 31
//@@  2. Passed daysInMonth ( FEB, 1999 ) test.       Expected: 28       Received: 28
//@@  3. Passed daysInMonth ( FEB, 2000 ) test.       Expected: 29       Received: 29
//@@  4. Passed daysInMonth ( FEB, 2016 ) test.       Expected: 29       Received: 29
//@@  5. Passed daysInMonth ( FEB, 2200 ) test.       Expected: 28       Received: 28
//@@  6. Passed daysInMonth ( MAR, 2016 ) test.       Expected: 31       Received: 31
//@@  7. Passed daysInMonth ( APR, 2016 ) test.       Expected: 30       Received: 30
//@@  8. Passed daysInMonth ( MAY, 2016 ) test.       Expected: 31       Received: 31
//@@  9. Passed daysInMonth ( JUN, 2016 ) test.       Expected: 30       Received: 30
//@@ 10. Passed daysInMonth ( JUL, 2016 ) test.       Expected: 31       Received: 31
//@@ 11. Passed daysInMonth ( AUG, 2016 ) test.       Expected: 31       Received: 31
//@@ 12. Passed daysInMonth ( SEP, 2016 ) test.       Expected: 30       Received: 30
//@@ 13. Passed daysInMonth ( OCT, 2016 ) test.       Expected: 31       Received: 31
//@@ 14. Passed daysInMonth ( NOV, 2016 ) test.       Expected: 30       Received: 30
//@@ 15. Passed daysInMonth ( DEC, 2016 ) test.       Expected: 31       Received: 31

// Enumeration.java

/**
 * Switch statement using the traditional case syntax.
 * PSET1: Exercise 3
 *
 * @author Kuljit Takhar
 * @version Last modified 09_Sept_2023
 *
 **/

import java.util.*;

class Enumeration {

    enum Month { JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC; }

    public static int daysInMonth(Month m, int year) {

        switch (m) {
            case JAN:
            case MAR:
            case MAY:
            case JUL:
            case AUG:
            case OCT:
            case DEC:
                return 31;
            case APR:
            case JUN:
            case SEP:
            case NOV:
                return 30;
            case FEB:
                 if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        for (Month m : Month.values()) {
            System.out.println(m + " 2023 has " +
                daysInMonth(m, 2023) + " days");
        }
    }
}