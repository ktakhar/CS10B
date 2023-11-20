/**
 * Program class to demonstrate Scanner class's "hasNext...() methods.
 */

import java.util.Scanner;

class ScannerHasNextDemo {
    public static void main( String [] args ) {
        Scanner keyboard = new Scanner( System.in );
        boolean gotBadInput;
        do {
            System.out.print( "Please type an int value: " );
            if (keyboard.hasNextInt() ) {
                gotBadInput = false;
                int response = keyboard.nextInt();
                System.out.printf( "response = %d\n", response );
            }
            else {
                gotBadInput = true;
                System.out.printf( "Hey, \"%s\" wasn't an int!!! Try again!!! \n", keyboard.nextLine() );
            }
        } while( gotBadInput );
    }
}