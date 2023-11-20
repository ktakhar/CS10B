// RecursivePrint.java

/**
 * Recursive method that prints the contents of a directory.
 * PSET1: Exercise 8 (Supplemental)
 * 
 * @Kuljit Takhar
 * @version Last modified 19_Sept_2023
 * 
 **/

public class RecursivePrint {
    private static final String[] units = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public static void main(String[] args) {
        int n = 123456; 
        System.out.println(printNumber(n));
    }

    public static String printNumber(int n) {
        if (n < 0) {
            return "minus " + printNumber(-n);
        } else if (n == 0) {
            return "zero";
        } else if (n < 20) {
            return units[n];
        } else if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " + units[n % 10] : "");
        } else if (n < 1000) {
            return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " + printNumber(n % 100) : "");
        } else if (n < 1000000) {
            return printNumber(n / 1000) + " thousand" + ((n % 1000 != 0) ? " " + printNumber(n % 1000) : "");
        } else {
            return "Number too large to handle";
        }
    }
}