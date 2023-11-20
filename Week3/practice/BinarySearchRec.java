// BinarySearchRec.java

/** Searching for a key in a sorted array using binary search using Scanner.
 * 
 * @author kuljit takhar
 * @version 09/28/2023
 * 
*/

import java.util.Scanner;


class BinarySearchRec {
    static int binary (int[] a, int fromIndex, int toIndex, int key) {
        if (fromIndex > toIndex) return -1; // Base Case 
        else {
            int middle = (fromIndex + toIndex) / 2;
            if (key == a[middle]) return middle;
            else if (key < a[middle])
                return binary (a, fromIndex, middle - 1, key);
            else
                return binary (a, middle + 1, toIndex, key);
        }
    }
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int size = s.nextInt();
        int[] a = new int[size];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            a[i] = s.nextInt();
        }
        System.out.println("Enter the key to search: ");
        int key = s.nextInt();
        int index = binary (a, 0, a.length - 1, key);
        System.out.println (index);
    }
}