import java.util.Arrays;

class BinarySearchDemoNonRecursive {
    public static void main(String [] args) {
        int[] array = { 1, 2, 3, 4 };
        System.out.printf( "binarySearch( %s, %d ) = %d\n", Arrays.toString( array ), 3, binarySearch( array, 3 ) );
        System.out.printf( "binarySearch( %s, %d ) = %d\n", Arrays.toString( array ), 5, binarySearch( array, 5 ) );
    }

    static int binarySearch( int[] array, int target ) {
        int index, left = 0, right = array.length-1;
        boolean found;
        do {
            index = (left+right) / 2;
            found = target == array[index];
            if      ( target < array[index] ) { right = index-1; }
            else if ( target > array[index] ) { left  = index+1; }
        } while( !found && left <= right );
        if (!found ) { index = -1; }
        return index;
    }
}
