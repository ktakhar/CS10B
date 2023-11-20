// ExcExample.java

/**
 * PSET3 - Problem 5
 * 
 * Reorder exception handling and control flow program
 * 
 * @author Kuljit takhar
 * @version October 25 2023
 * 
 */

class MyException extends Exception {}

public class ExcExample {
    static void doRisky(String arg) throws MyException {
         System.out.print("t");
         System.out.print("h");
        if ("Warren".equals(arg)) {
            System.out.print("a");
            System.out.print("w");
        } else {
            System.out.print("r");
            System.out.print("o");
            System.out.print("w");
        }
    }

   public static void main(String[] args) throws MyException {
        String test = args[0];
        try {
            doRisky(test);
        } catch (MyException e) {
            System.out.print("throw");
            throw new MyException();
        } finally {
        System.out.print("s");
        }
    }
}
