// Scope1.java

/**
 * This class shows some "block"-centric examples of variable scope.
 * "Blocks" are {}-delimited blocks of code. E.g. the body of a for loop.
 *
 * @author David Habermehl
 * @version Last modified 26_Sep_2017
 **/
class Scope1 {
    public static void main( String [] args ) {

        demo01();
        //demo02();
        //demo03();
        //demo04();
    }

    // Scope of index variable is the for loop
    static void demo01() {
        System.out.println( "Demo01. What is the value of i after the for loop finishes?" );
        for( int i=0; i<=9; i++ ) {
            System.out.print( i + " " );
        }
        System.out.println();
        // What is the value of i?
        // System.out.println( "The value of i is " + i );
    }

    // Fix  for scope of index variable is the for loop
    static void demo02() {
        System.out.println( "Demo02. What is the value of j after the for loop finishes?" );
        int i;
        for( i=0; i<=9; i++ ) {
            System.out.print( i + " " );
        }
        System.out.println();
        System.out.println( "The value of i is " + i );
    }



    // What about other variables defined in the body of the for loop?
    static void demo03() {
        System.out.println( "demo03. What about other variables defined in the body of the for loop?" );
        int k=-1;
        for( int i=0; i<=9; i++ ) {
            k = i;
            System.out.print( k + " " );
        }
        System.out.println();
        // What is the value of k?
        System.out.println( "The value of k is " + k );
    }



    // How do things behave with if/else?
    static void demo04() {
        System.out.println( "How do things behave with if/else?" );
        if ( true ) {
            int m = 1;
        }
        else {
            int m = 2;
        }
        //System.out.println(m);

    }
}