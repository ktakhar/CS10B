import java.util.Arrays;

class BinarySearchDemoRecursive {
    public static void main(String [] args) {
        int[] array = { 1, 2, 3, 4 };
        System.out.printf( "binarySearch( %s, %d ) = %d\n", Arrays.toString( array ), 3, binarySearch( array, 3 ) );
        System.out.printf( "binarySearch( %s, %d ) = %d\n", Arrays.toString( array ), 5, binarySearch( array, 5 ) );

        System.out.println();
     // System.out.printf( "binarySearchVarArgs( %s, %d ) = %d\n", Arrays.toString( array ), 3, binarySearchVarArgs( array, 3 ) );
     // System.out.printf( "binarySearchVarArgs( %s, %d ) = %d\n", Arrays.toString( array ), 5, binarySearchVarArgs( array, 5 ) );
    }

    static int binarySearch( int[] array, int target ) {
        return binarySearch( array, target, 0, array.length-1 );
    }



    static int binarySearch( int[] array, int target, int left, int right ) {
        int index;
        boolean found;
        // Base case
        if ( left > right ) { return -1; }

        // Recursive case
        else {
            index = (left+right) / 2;
            if      ( target < array[index] ) { return binarySearch( array, target, left,    index-1 ); }
            else if ( target > array[index] ) { return binarySearch( array, target, index+1, right   ); }
            else                              { return index; }
        }
    }


    static int binarySearchVarArgs( int[] array, int target, int ... leftRight ) {

        int left=0, right=array.length-1;
        if ( leftRight.length==2 ) {
            left=leftRight[0];
            right=leftRight[1];
        }

        int index;
        boolean found;
        // Base case
        if ( left > right ) { return -1; }

        // Recursive case
        else {
            index = (left+right) / 2;
            if      ( target < array[index] ) { return binarySearchVarArgs( array, target, left,    index-1 ); }
            else if ( target > array[index] ) { return binarySearchVarArgs( array, target, index+1, right   ); }
            else                              { return index; }
        }
    }
}
