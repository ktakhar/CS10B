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