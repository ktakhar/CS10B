import java.util.*;

enum Months { JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC};

class Enumeration {
    public static int daysInMonth(Months m, int year) {
        int daysInMonth = 0;
        
        switch(m) {
            case JAN:
            case MAR:
            case MAY:
            case JUL:
            case AUG:
            case OCT:
            case DEC:
                daysInMonth = 31;
                break;
            case APR:
            case JUN:
            case SEP:
            case NOV:
                daysInMonth = 30;
                break;
            case FEB:
                if ((year % 4 == 00 && year % 100 != 0) || (year % 400 == 0)) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
            default:
                System.out.println("Invalid month");
                break;
        }
    }
    public static void main(String[] args) {
        for (Months m: Months.values()) {
            System.out.println (m + " 2023 has " + daysInMonth(m, 2023) + " days!");
        }
    }
}