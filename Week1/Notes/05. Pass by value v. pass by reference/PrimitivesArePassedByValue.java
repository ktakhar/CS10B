/**
 * Program class: Demonstration program illustrates passing arguments by value.
 *
 * @author David Habermehl
 * @version Last modified 05_Dec_2017
 **/
class PrimitivesArePassedByValue {
    public static void main(String [] args) {

        System.out.println( "\nPrimitive data types are passed by value.\nThat means that " +
                            "called methods see a local copy of the passed variable.\nThat " +
                            "means that modifications made by the called  method don't show " +
                            "up in the calling method.");

        int primitiveValue = 7;
        System.out.printf( "\nIn main(). primitiveValue = %d\n", primitiveValue );

        // Primitive data types are passed by value.
        foo( primitiveValue );

        System.out.printf( "\nIn main(). primitiveValue = %d.\n\n", primitiveValue );
    }



    /**
     * foo
     *     1. Display argument
     *     2. Modify argument
     *     3. Display modified argument.
     *
     * @param  primitiveValue   Argument is an int value.
     */
    static void foo( int primitiveValue ) {

        System.out.printf( "\n\tIn foo() before modifying " +
                           "primitiveValue. primitiveValue = %d.\n", primitiveValue );

        primitiveValue++;

        System.out.printf( "\tIn foo()  after modifying " +
                           "primitiveValue. primitiveValue = %d.\n", primitiveValue );
    }
}
