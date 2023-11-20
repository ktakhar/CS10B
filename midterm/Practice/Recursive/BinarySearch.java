class BinarySearch {
    public static void main(String[] args) { // The main method
        int[] foobar = {-20, -15, -10, -5, 0, 5, 10, 15, 20}; // An array of integers named foobar
        int key = 0; // The key you are searching for in the array

        // Call the binary method and store the result in the 'result' variable
        int result = binary(foobar, 0, foobar.length - 1, key);

        if (result == -1) { 
            System.out.println(key + " not found in the array.");
        } else {
            System.out.println(key + " found at index " + result);
        }
    }

    static int binary(int[] a, int fromIndex, int toIndex, int key) {
        if (fromIndex > toIndex) {
            return -1; // Return -1 to indicate that the key was not found
        } else {
            int middle = (fromIndex + toIndex) / 2; // Calculate the middle index of the search range
            if (key == a[middle]) {
                return middle; // Return the index where the key was found
            } else if (key > a[middle]) {
                return binary(a, middle + 1, toIndex, key); // Recursively search the right half of the array
            } else {
                return binary(a, fromIndex, middle - 1, key); // Recursively search the left half of the array
            }
        }
    }
}
