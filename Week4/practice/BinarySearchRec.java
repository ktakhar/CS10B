// BinarySearchRec.java

/** Searching for a key in a sorted array using binary search.
 * 
 * @author kuljit takhar
 * @version 09/28/2023
 * 
*/


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
        int[] a = {1, 3, 5, 7, 9, 11, 13, 15};
        int key = 11;
        int index = binary (a, 0, a.length - 1, key);
        System.out.println (index);
    }
}