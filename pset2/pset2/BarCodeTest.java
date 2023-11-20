// BarCodeTest.java

/**
 * PSET2 #4
 * Test for BarCode class creating instances with different inputs,
 * validating their creation, and displaying the resulting bar codes and ZIP codes.
 * Handles potential IllegalArgumentExceptions.
 * 
 * @author Kuljit Takhar
 * @version October 3, 2023
 * 
 */

public class BarCodeTest {
    public static void main(String[] args) {
        
        // An array of test inputs
        String[] tester = {
            "02138",
            "||:|:::|:|:||::::::||:|::|:::|||",
            "||:::||::|:::|:|:|::|||:::|:|::|",
            "||:::||::|:::|:|:|::|||:::||:::|",
            "||:::||::|:::|:|:|::|||:::|:::|",
            "||:|:::|:|:||::::::||:|::|:::||:",
            "0231a"
        };

        // Loop through the test inputs
        for (String input : tester) {
            System.out.println();
            try {
                System.out.println("Creating bar code with " + input);
                BarCode bc = new BarCode(input); // Attempt to create a BarCode instance
                System.out.println("\tCode created!");
                System.out.println("\tBar code is : " + bc.getBarCode() + "; zip is : " + bc.getZipCode());
            } catch (IllegalArgumentException iae) {
                
                // Handle any IllegalArgumentException thrown during object creation
                System.out.println(iae.getMessage());
            }
            System.out.println();
        }
    }
}
