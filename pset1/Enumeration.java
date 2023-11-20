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