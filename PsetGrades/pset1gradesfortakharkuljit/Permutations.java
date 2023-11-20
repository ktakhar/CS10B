//@@ 9 out of 10 points

//@@     o Every method except main() needs a method-level comment immediately before the method, saying what the method does.

//@@ listPermutations( "ABCD" ) produced:
//@     ABCD
//@     ABDC
//@     ACBD
//@     ACDB
//@     ADBC
//@     ADCB
//@     BACD
//@     BADC
//@     BCAD
//@     BCDA
//@     BDAC
//@     BDCA
//@     CABD
//@     CADB
//@     CBAD
//@     CBDA
//@     CDAB
//@     CDBA
//@     DABC
//@     DACB
//@     DBAC
//@     DBCA
//@     DCAB
//@     DCBA

// Permutations.java

/**
 * Recursive method that prints all permutations of a string.
 * PSET1: Exercise 9 (Extra Credit)
 *
 * @author Kuljit Takhar
 * @version Last modified 19_Sept_2023
 *
 **/

public class Permutations {
    public static void main(String[] args) {
        listPermutations("ABCD");
    }

    public static void listPermutations(String input) {
        generatePermutations("", input);
    }

    private static void generatePermutations(String prefix, String remaining) {
        int length = remaining.length();

        if (length == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < length; i++) {
                String newPrefix = prefix + remaining.charAt(i);
                String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
                generatePermutations(newPrefix, newRemaining);
            }
        }
    }
}
