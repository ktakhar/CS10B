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
