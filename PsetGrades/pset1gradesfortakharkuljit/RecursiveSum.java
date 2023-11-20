//@@ 19 out of 20 points

//@@     o Every method except main() needs a method-level comment immediately before the method, saying what the method does.

//@@ Passed all 5 sumTo tests:

//@@  1. Passed sumto( -1 ) test.
//@@         Expected: java.lang.IllegalArgumentException
//@@         Received: java.lang.IllegalArgumentException: Input must be non-negative.
//@@  2. Passed sumto( 0  ) test.
//@@         Expected: 0.0
//@@         Received: 0.0
//@@  3. Passed sumto( 1  ) test.
//@@         Expected: 1.0
//@@         Received: 1.0
//@@  4. Passed sumto( 2  ) test.
//@@         Expected: 1.5
//@@         Received: 1.5
//@@  5. Passed sumto( 3  ) test.
//@@         Expected: 1.8333333333333333
//@@         Received: 1.8333333333333333

// RecursiveSum.java

/**
 * Recursive method that computes the sum of the first n integers.
 * PSET1: Exercise 7
 *
 * @author Kuljit Takhar
 * @version Last modified 19_Sept_2023
 *
 **/

public class RecursiveSum {
    public static void main(String[] args) {
        try {
            int n = 5;
            double result = sumTo(n);
            System.out.println("sumTo(" + n + ") = " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
    }

    public static double sumTo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative.");
        } else if (n == 0) {
            return 0.0;
        } else {
            return 1.0 / n + sumTo(n - 1);
        }
    }
}