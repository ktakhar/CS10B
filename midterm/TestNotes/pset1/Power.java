// Power.java

/**
 * Recursive method that computes x^n.
 * PSET1: Exercise 4
 * 
 * @author Kuljit Takhar
 * @version Last modified 15_Sept_2023
 **/

class Power {
    public static double power(double x, int n) {
        if (n == 0) return 1.0;
        else if (n > 0) {
            if (n % 2 == 0) {
                double halfPower = power(x, n / 2);
                return halfPower * halfPower;
            } else {
                return x * power(x, n - 1);
            }
        } else {
            return 1.0 / power(x, -n);
        }
    }

    public static void main(String[] args) {
        double result = power(2.0, 10);
        System.out.println("Result: " + result);
    }
}

/**
*
* The modified power method will be called a total number of 11 times. 
* log₂(1024) + 1 = 11
**/


