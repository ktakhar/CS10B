//@@ 0 out of 7 points

//@@     o Follow directions! Pset said "printTriangle (4) should still be recursive."
//@@     o You removed "static" from printTriangle()'s signature.

// RecTriangle.java

/**
 * Recursive method that prints a triangle of size s using nested for loop.
 * PSET1: Exercise 2
 *
 * @author Kuljit Takhar
 * @version Last modified 24_Sept_2023
 *
 **/

public class RecTriangle {
    public void printTriangle(int s) {
        if (s < 1) return;
        for (int i = s; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("[]");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RecTriangle recTriangle = new RecTriangle();
        recTriangle.printTriangle(4);
    }
}
