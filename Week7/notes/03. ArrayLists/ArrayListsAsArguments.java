// ArrayListsAsArguments.java

import java.util.ArrayList;

/**
 * Description of ArrayListsAsArguments class.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
class ArrayListsAsArguments {

    public static void main( String[] args ) {
        ArrayList<Double> foo = createArrayList();
        printArrayList( foo );
    }



    // Method prints its ArrayList argument.
    static void printArrayList( ArrayList<Double> foo ) {
        System.out.printf( "\n\nfoo: \"%s\"\n\n", foo );
    }



    // Method returns a new ArrayList.
    static ArrayList<Double> createArrayList() {
        ArrayList<Double> temp = new ArrayList<Double>();
        temp.add( 1.1 );
        temp.add( 2.2 );
        return temp;
    }
}
