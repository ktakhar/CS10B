//@@ 9 out of 9 points

//@@     o Student's modified power() produces correct values.
//@@     o Student's power(foobar, 1024) made 12 total calls (including the initial one).
//@@       The student's answer was 11 total calls.

//@@ Student:             power    (1.01,  1023) = 26349.075364   Total Calls=20
//@@ Reference:           power    (1.01,  1023) = 26349.075364   Total Calls=20
//@@ Unmodified Original: power    (1.01,  1023) = 26349.075364   Total Calls=1024
//@@ Math.pow:            Math.pow (1.01,  1023) = 26349.075364   Total Calls=0

//@@ Student:             power    (1.01,  1024) = 26612.566117   Total Calls=12
//@@ Reference:           power    (1.01,  1024) = 26612.566117   Total Calls=12
//@@ Unmodified Original: power    (1.01,  1024) = 26612.566117   Total Calls=1025
//@@ Math.pow:            Math.pow (1.01,  1024) = 26612.566117   Total Calls=0

//@@ Student:             power    (0.99, -1023) = 29187.434729   Total Calls=21
//@@ Reference:           power    (0.99, -1023) = 29187.434729   Total Calls=21
//@@ Unmodified Original: power    (0.99, -1023) = 29187.434729   Total Calls=1025
//@@ Math.pow:            Math.pow (0.99, -1023) = 29187.434729   Total Calls=0

//@@ Student:             power    (0.99, -1024) = 29482.257302   Total Calls=13
//@@ Reference:           power    (0.99, -1024) = 29482.257302   Total Calls=13
//@@ Unmodified Original: power    (0.99, -1024) = 29482.257302   Total Calls=1026
//@@ Math.pow:            Math.pow (0.99, -1024) = 29482.257302   Total Calls=0

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


