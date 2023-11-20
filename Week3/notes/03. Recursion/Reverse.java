// Reverse.java

import java.util.Scanner;

class Reverse {
    public static void main(String [] args) {
        reverse( new Scanner( System.in ) );
        System.out.println();
    }



    // Recursive method reads lines and then dislays them in reverse order.
    // Base case:      No more lines. Just return.
    // Recursive case: There is another line. Save it in a local variable,
    //                 recursively invoke the method, then display the line
    //                 that was saved in the local variable.
    static void reverse( Scanner keyboard ) {

        System.out.print( "Input another line (ctrl-z+enter when done): " );
        if ( keyboard.hasNextLine() ) {

            // Store the line in this invocation's local variable
            String line = keyboard.nextLine();

            // Recurse
            reverse( keyboard );

            // When we get back from the recursive invocation, display the original
            // line that was saved in this invocation's local variable
            System.out.println( line );
        }
    }
}
