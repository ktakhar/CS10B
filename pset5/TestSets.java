/**
*  TestSets.java
*
*  @version: Last Modified April 8, 2020
*  @author:  Henry Leitner
*/

import java.util.*;

public class TestSets
{
  static void menu()
  {
     System.out.println ();
     System.out.print ("Type 1 to CREATE SET A\n");
     System.out.print ("Type 2 to CREATE SET B\n");
     System.out.print ("Type 3 to CREATE INTERSECTION (A * B)\n");
     System.out.print ("Type 4 to CREATE UNION (A + B)\n");
     System.out.print ("Type 5 to CREATE DIFFERENCE (A - B)\n");
     System.out.print ("Type 6 to CALCULATE CARDINALITY OF SET A\n");
     System.out.print ("Type 7 to CALCULATE CARDINALITY OF SET B\n");
     System.out.print("Type 8 to CHECK IF SET A IS A SUBSET OF SET B\n");
     System.out.print ("Type any OTHER # to EXIT PROGRAM \n\n");
     System.out.print ("Command: ");
  }
  
  public static void main (String [] args) 
  {
     Bitset setA = new Bitset (16);
     Bitset setB = new Bitset (8);
     int command;
    
     Scanner keyboard = new Scanner (System.in);
     do 
     {
         menu();
         
         switch (command = keyboard.nextInt ()) 
         {
            case 1:
              System.out.println ("Type some small integers, each < 16" 
                                 + ", and type DONE when all done!");
              setA.readSet(keyboard);
              System.out.print ("     SET A = " + setA);
              break;

            case 2:
              System.out.println ("Type some small integers, each < 8"
                                 + ", and type DONE when all done!");
              setB.readSet(keyboard);
              System.out.print ("     SET B = " + setB);
              break;

           case 3:
              System.out.print ("     Intersection (A * B) = ");
              System.out.print (setA.intersect(setB));
              break;
           
	       case 4:
              System.out.print ("     Union (A + B) = ");
              System.out.print (setA.union(setB));
              break;

           case 5:
              System.out.print ("     Difference (A - B) = ");
              System.out.print (setA.difference(setB));
              break;

           /**
            *  PSET 5 #1 Part A
            *  Modify TestSet to include cardinality instance method
            * */ 

           case 6:
              System.out.print ("    Cardinality of SET A = "); 
              System.out.print (setA.cardinality()); // apply cardinality() method to setA
              break;

            case 7:
              System.out.print ("    Cardinality of SET B = ");
              System.out.print (setB.cardinality()); // apply cadinarlity() method to set B
              break;
            
            /**
             * PSET 5 #1 Part B
             * Modify TestSet to include isSubset() instance method
             * Check if Set A is a subset of B or if Set B is a subset of A
             */

            case 8:
                boolean isSubsetAB = setA.isSubset(setA, setB);
                boolean isSubsetBA = setB.isSubset(setB, setA);
                    if (isSubsetAB && isSubsetBA) {
                         System.out.println("     SET A is equal to SET B");
                    } else if (isSubsetAB) {
                        System.out.println("     SET A is a subset of SET B");
                    } else if (isSubsetBA) {
                        System.out.println("     SET B is a subset of SET A");
                    } else {
                        System.out.println("     Neither SET A nor SET B is a subset of the other");
                    }
                    break;

            default:  System.exit(0);
         }
       } while (command > 0 && command < 8);  // change int to 8 to include new switch cases
  }
}
