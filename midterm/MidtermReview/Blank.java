// P2Blank.java

/**
 * P2 
 * File Input Problem
 * 
 * The following program is supposed to report how many “blank lines” appear
   inside a file whose name is supplied on the command line. Four different Java
   expressions have been purposely omitted (each indicated by ???); you will need to
   change each ??? directly on the code to make this program work as intended.
 */

import java.io.*;
import java.util.*;

// class Blank{
//     public static void main (String [] args) throws ???1 {
//         File f = new File ( ???2 );
//         Scanner s = new Scanner (f);
//         int count = 0;
//         while ( ???3 )
//         {
//             String str = s.nextLine();
//             if ( ???4 ) count++;
//         }   
//         System.out.println("# of blank lines = " + count);
//     }
// }

class Blank{
    public static void main (String [] args) throws FileNotFoundException {
        File f = new File (args[0]);
        Scanner s = new Scanner (f);
        int count = 0;
        while (s.hasNextLine()) {
            String str = s.nextLine();
            if (str.length() == 0) count++;
        }   
        System.out.println("# of blank lines = " + count);
    }
}