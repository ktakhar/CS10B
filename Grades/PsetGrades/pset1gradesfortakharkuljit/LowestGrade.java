//@@ 23 out of 30 points

//@@     o Every method except main() needs a method-level comment immediately before the method, saying what the method does.
//@@     o Tests 2-4,6,11-12,14-16: Replaced lowest entry with 0
//@@     o Tests 5,7: Threw ArrayIndexOutOfBoundsException
//@@     o Tests 8,9,10,11: When lowest grade occurred more than once, only one should be reoved.


//@@ Failed 14 of 16 removeLowest() tests:

//@@  1. Passed removeLowest(                                ) test.
//@@         Expected: []
//@@         Received: []
//@@  2. FAILED removeLowest( 85                             ) test.
//@@         Expected: [85]
//@@         Received: [0]
//@@  3. FAILED removeLowest( 23, 90, 47, 55, 88             ) test.
//@@         Expected: [90, 47, 55, 88]
//@@         Received: [90, 47, 55, 88, 0]
//@@  4. FAILED removeLowest( 23, 90, 47, 55, 88, 89, 90, 91 ) test.
//@@         Expected: [90, 47, 55, 88, 89, 90, 91]
//@@         Received: [90, 47, 55, 88, 89, 90, 91, 0]
//@@  5. FAILED removeLowest( 90, 47, 23, 55, 88             ) test.
//@@         Expected: [90, 47, 55, 88]
//@@         Received: java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
//@@  6. FAILED removeLowest( 47, 47, 59, 92, 93, 88         ) test.
//@@         Expected: [47, 59, 92, 93, 88]
//@@         Received: [59, 92, 93, 88, 0]
//@@  7. FAILED removeLowest( 90, 47, 55, 88, 23             ) test.
//@@         Expected: [90, 47, 55, 88]
//@@         Received: java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
//@@  8. FAILED removeLowest( 59, 92, 93, 47, 88, 47         ) test.
//@@         Expected: [59, 92, 93, 88, 47]
//@@         Received: [59, 92, 93, 88]
//@@  9. FAILED removeLowest( 59, 92, 93, 47, 47, 88         ) test.
//@@         Expected: [59, 92, 93, 47, 88]
//@@         Received: [59, 92, 93, 88]
//@@ 10. FAILED removeLowest( 59, 92, 93, 88, 47, 47         ) test.
//@@         Expected: [59, 92, 93, 88, 47]
//@@         Received: [59, 92, 93, 88]
//@@ 11. FAILED removeLowest( 59, 59                         ) test.
//@@         Expected: [59]
//@@         Received: [0]
//@@ 12. FAILED removeLowest( 59, 60                         ) test.
//@@         Expected: [60]
//@@         Received: [60, 0]
//@@ 13. Passed removeLowest( 60, 59                         ) test.
//@@         Expected: [60]
//@@         Received: [60]
//@@ 14. FAILED removeLowest( 100, 100                         ) test.
//@@         Expected: [100]
//@@         Received: [0]
//@@ 15. FAILED removeLowest( 101, 102                         ) test.
//@@         Expected: [102]
//@@         Received: [102, 0]
//@@ 16. FAILED removeLowest( 100001, 100002                         ) test.
//@@         Expected: [100002]
//@@         Received: [100002, 0]


//@@ Passed all 7 arrayPrint() tests:

//@@  1. Passed arrayPrint( []           ) test.
//@@         Expected: "[]"
//@@         Received: "[]"
//@@  2. Passed arrayPrint( [1]          ) test.
//@@         Expected: "[1]"
//@@         Received: "[1]"
//@@  3. Passed arrayPrint( [1, 20]      ) test.
//@@         Expected: "[1, 20]"
//@@         Received: "[1, 20]"
//@@  4. Passed arrayPrint( [1, 20, 300] ) test.
//@@         Expected: "[1, 20, 300]"
//@@         Received: "[1, 20, 300]"
//@@  5. Passed arrayPrint( [1, 20, 300, 4000] ) test.
//@@         Expected: "[1, 20, 300, 4000]"
//@@         Received: "[1, 20, 300, 4000]"
//@@  6. Passed arrayPrint( [10, 20, 30, 40] ) test.
//@@         Expected: "[10, 20, 30, 40]"
//@@         Received: "[10, 20, 30, 40]"
//@@  7. Passed arrayPrint( [10, 20, 30, 40, 50] ) test.
//@@         Expected: "[10, 20, 30, 40, 50]"
//@@         Received: "[10, 20, 30, 40, 50]"

// LowestGrade.java

/**
 * Iterative method that removes lowest grade in an array.
 * PSET1: Exercise 6
 *
 * @author Kuljit Takhar
 * @version Last modified 15_Sept_2023
 *
 **/

public class LowestGrade {
    public static void main(String[] args) {
        int[] a = removeLowest(23, 90, 47, 55, 88);
        int[] b = removeLowest(85);
        int[] c = removeLowest();
        int[] d = removeLowest(59, 92, 93, 47, 88, 47);

        System.out.println("a = " + arrayPrint(a));
        System.out.println("b = " + arrayPrint(b));
        System.out.println("c = " + arrayPrint(c));
        System.out.println("d = " + arrayPrint(d));
    }

    public static int[] removeLowest(int... scores) {
        if (scores.length == 0) {
            return new int[0];
        }

        int lowest = scores[0];
        int count = 1;

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < lowest) {
                lowest = scores[i];
            } else if (scores[i] > lowest) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;

        for (int score : scores) {
            if (score != lowest) {
                result[index] = score;
                index++;
            }
        }
        return result;
    }

    public static String arrayPrint(int[] arr) {
        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[arr.length - 1]).append("]");
        return sb.toString();
    }
}
