// DemoArrays.java

import java.util.Arrays;
class DemoArrays {

    public static void main( String[] args ) {
        arrayViaForLoop();
     // arrayViaArrayInitializer();
     // arrayViaMethodThatReturnsAnArray();

        System.out.println();
    }



    // Create int array, initialize it via a for loop
    static void arrayViaForLoop() {
        System.out.println( "Array initialized via for loop");
        int[] array = new int[3];
        System.out.printf( "array.length = %d\n", array.length );
        for ( int i=0; i<array.length; i++ ) {
            array[i] = i;
        }
        System.out.printf( "array = %s\n", Arrays.toString( array ) );
    }



    // Create int array, initialize it via an array initializer
    static void arrayViaArrayInitializer() {
        System.out.println( "\nArray initialized via array initializer):");
        int[] array = { 9, 10, 74 };
        System.out.printf( "array = %s\n", Arrays.toString( array ) );
    }



    // Create int array, initialize it via a method that return an array
    static void arrayViaMethodThatReturnsAnArray() {
        System.out.println( "\nArray initialized via method that returns an array:");
        int[] array = methodThatReturnsAnArray( 8 );
        System.out.printf( "array = %s\n", Arrays.toString( array ) );
    }



    // Method returns an array
    static int[] methodThatReturnsAnArray ( int arrayLength ) {
        int[] temp = new int [arrayLength];
        for ( int i=0; i<temp.length; i++ ) {
            temp[i] = i;
        }
        return temp;
    }
}
