import java.util.Arrays;
/**
 * Program class to demonstrate variable number of arguments.
 */
class VarNumOfArgsDemo {
    public static void main( String [] args ) {
        foo("SomeString", 1.0, 2.0, 3.645343, 99 );

    }

    static void foo( String requiredString, double requiredDouble, double ... varArgs ) {
        System.out.printf( "requiredString=\"%s\",\n requiredDouble=%f\nArrays.toString( varArgs )=%s\n",
                           requiredString, requiredDouble, Arrays.toString( varArgs ) );
    }
}
