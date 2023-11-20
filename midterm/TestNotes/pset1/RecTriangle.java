// RecTriangle.java

/**
 * Recursive method that prints a triangle of size s using nested for loop.
// RecTriangle.java 
 
 * PSET1: Exercise 2
 * Prints triangle upside down
 * 
 * @author Kuljit Takhar
 * @version Last modified 24_Sept_2023
 * 
 **/

public class RecTriangle {
   public static void printTriangle(int s) {
        if (s < 1) return;
        printTriangle(s - 1); // prints decreasing values of s which prints the triangle upside down.
        for (int i = 0; i < s; i++) {
        System.out.print("[]");
        }
   
    System.out.println();
   
   }

    public static void main(String[] args) {
        RecTriangle recTriangle = new RecTriangle();
        recTriangle.printTriangle(4);
    }
}

public static void printTriangle (int s)
  {
         if (s < 1) return;
        
         for (int i = 0; i < s; i++)  
         {
             System.out.print( "[]");
         }
         System.out.println (); 
       }