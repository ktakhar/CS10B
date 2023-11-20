//@@ 7 out of 10 points plus 0 out of 2 extra credit points

//@@     o Every method except main() needs a method-level comment immediately before the method, saying what the method does.
//@@     o Follow directions! printNumber() is not supposed to return the result.
//@@     o Follow directions! printNumber() is supposed to print the result. I temporarily patched your code to run my tests.

//@@ printNumber(int n) tests:

//@@              0: "zero"
//@@              1: "one"
//@@             -1: "minus one"
//@@             10: "ten"
//@@             11: "eleven"
//@@             20: "twenty"
//@@             21: "twenty one"
//@@             30: "thirty"
//@@              4: "four"
//@@             40: "forty"
//@@             50: "fifty"
//@@            300: "three hundred"
//@@            309: "three hundred nine"
//@@            319: "three hundred nineteen"
//@@            320: "three hundred twenty"
//@@            321: "three hundred twenty one"
//@@          1,000: "one thousand"
//@@         10,000: "ten thousand"
//@@        100,000: "one hundred thousand"
//@@        100,001: "one hundred thousand one"
//@@        100,010: "one hundred thousand ten"
//@@        100,100: "one hundred thousand one hundred"
//@@        101,000: "one hundred one thousand"
//@@        110,000: "one hundred ten thousand"
//@@          4,321: "four thousand three hundred twenty one"
//@@         54,321: "fifty four thousand three hundred twenty one"
//@@        654,321: "six hundred fifty four thousand three hundred twenty one"

//@@ printNumber(int n) extra credit (n>=1,000,000) tests:

//@@      1,000,000: "Number too large to handle"
//@@      1,000,001: "Number too large to handle"
//@@      1,000,010: "Number too large to handle"
//@@      1,000,100: "Number too large to handle"
//@@      1,001,000: "Number too large to handle"
//@@      1,010,000: "Number too large to handle"
//@@      1,100,000: "Number too large to handle"
//@@     -1,000,000: "minus Number too large to handle"
//@@      7,654,321: "Number too large to handle"
//@@     87,654,321: "Number too large to handle"
//@@    987,654,321: "Number too large to handle"
//@@  2,147,483,646: "Number too large to handle"
//@@ -2,147,483,647: "minus Number too large to handle"

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