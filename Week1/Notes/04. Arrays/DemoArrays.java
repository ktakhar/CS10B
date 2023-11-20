import java.util.Arrays;

class DemoArrays {

    public static void main( String[] args ) {
        //oneDimensionalArrayViaForLoop();
        //oneDimensionalArrayViaArrayInitializer();
        //oneDimensionalArrayViaMethodThatReturnsAnArray();
        //twoDimensionalArrayViaForLoop();
        //twoDimensionalArrayViaArrayInitializer();
        //twoDimensionalRaggedArrayViaForLoop();
        twoDimensionalRaggedArrayViaArrayInitializer();
        //twoDimensionalRaggedArrayViaArrayInitializerDeepToString();
        System.out.println();
    }

    static void oneDimensionalArrayViaForLoop() {
        System.out.println( "One dimensional array, initialized via for loop");
        int[] array = new int[3];
        System.out.printf( "array.length = %d\n", array.length );
        for ( int i=0; i<array.length; i++ ) {
            array[i] = i;
        }
        System.out.printf( "array = %s\n", Arrays.toString( array ) );
    }



    static void oneDimensionalArrayViaArrayInitializer() {
        System.out.println( "\nOne dimensional array, initialized via array initializer):");
        int[] array = { 9, 10, 74 };
        System.out.printf( "array = %s\n", Arrays.toString( array ) );
    }



    static void oneDimensionalArrayViaMethodThatReturnsAnArray() {
        System.out.println( "\nOne dimensional array, initialized via method that returns an array:");
        int[] array = methodThatReturnsAnArray( 8 );
        System.out.printf( "array = %s\n", Arrays.toString( array ) );
    }



    static int[] methodThatReturnsAnArray ( int arrayLength ) {
        int[] temp = new int [arrayLength];
        for ( int i=0; i<temp.length; i++ ) {
            temp[i] = i;
        }
        return temp;
    }



    static void twoDimensionalArrayViaForLoop() {
        System.out.println( "\nTwo dimensional array, initialized via nested for loops:");
        int[][] array = new int[3][];
        for ( int row=0; row<array.length; row++ ) {
            array[row] = new int[2];
            for ( int column=0; column<array[row].length; column++ ) {
                array[row][column] = row*10+column;
            }
        }
        for ( int row=0; row<array.length; row++ ) {
            System.out.printf( "array[%d] = %s\n", row, Arrays.toString( array[row] ) );
        }
    }



    static void twoDimensionalArrayViaArrayInitializer() {
        System.out.println( "\nTwo dimensional array, initialized via array initializer:");
        int[][] array = {
                         {00, 01},
                         {10, 11},
                         {20, 21}
                        };
        for ( int row=0; row<array.length; row++ ) {
            System.out.printf( "array[%d] = %s\n", row, Arrays.toString( array[row] ) );
        }
    }



    static void twoDimensionalRaggedArrayViaForLoop() {
        System.out.println( "\nTwo dimensional \"ragged\" array, initialized via nested for loops:");
        int[][] array = new int[3][];
        for ( int row=0; row<array.length; row++ ) {
            array[row] = new int[row+1];
            for ( int column=0; column<array[row].length; column++ ) {
                array[row][column] = row*10+column;
            }
        }
        for ( int row=0; row<array.length; row++ ) {
            System.out.printf( "array[%d] = %s\n", row, Arrays.toString( array[row] ) );
        }
    }



    static void twoDimensionalRaggedArrayViaArrayInitializer() {
        System.out.println( "\nTwo dimensional \"ragged\" array, initialized via array initializer:");
        int[][] array = {
                         {0},
                         {10, 11},
                         {20, 21, 22}
                        };
        for ( int row=0; row<array.length; row++ ) {
            System.out.printf( "array[%d] = %s\n", row, Arrays.toString( array[row] ) );
        }
    }



    static void twoDimensionalRaggedArrayViaArrayInitializerDeepToString() {
        System.out.println( "\nTwo dimensional \"ragged\" array, initialized via array initializer, printed via Arrays.deepToString():");
        int[][] array = {
                         {0,1},
                         {10, 11, 12},
                         {20}
                        };
        System.out.printf ( "\t%s\n", Arrays.deepToString ( array ) );
    }
}