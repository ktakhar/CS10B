/*  File SumFile2.java
 *  Reads a text file of integers, prints the sum of the ints found.
 *  @author  Dr. Henry H. Leitner
 *  @version Last Modified:  October 29, 2018
*/

import java.io.*;
import java.util.*;

class SumFile2 {
    public static void main(String[] args) {
        int count = 0; // the # of numbers input
        int sum = 0; // the sum of the numbers

        boolean validFile = false;
        Scanner keyboard = new Scanner(System.in);

        while (!validFile) {
            try {
                System.out.print("Type the name of an input file containing integers: ");
                String fname = keyboard.nextLine();
                Scanner in = new Scanner(new File(fname));

                while (in.hasNextInt()) {
                    int x = in.nextInt();
                    count++;
                    System.out.println("Value #" + count + " is " + x);
                    sum += x;
                }

                System.out.println("Sum of " + count + " numbers is " + sum);
                in.close();
                validFile = true; // If we reach this point, the file was successfully read.

            } catch (FileNotFoundException e) {
                System.out.println("Trouble opening or reading the file... maybe it was misspelled???");
                e.printStackTrace();
            }
        }
    }
}
