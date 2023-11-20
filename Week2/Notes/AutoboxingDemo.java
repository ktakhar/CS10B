/**
 * Program class to demonstrate autoboxing and unboxing.
 */

import java.util.ArrayList;

class AutoboxingDemo {
    public static void main( String [] args ) {
        // Autoboxing
        int intValue = 99;
        Integer integerObject = intValue;
        intValue = integerObject;

        System.out.printf( "intValue=%d, integerObject=%d\n", intValue, integerObject );

        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int foo = 0; foo < 5; foo++ ) {
            integers.add(foo);
        }
        System.out.printf("integers ArrayList is %s\n", integers );
    }
}
