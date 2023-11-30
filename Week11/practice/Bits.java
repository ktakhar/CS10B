import java.util.*;

public class Bits {
    public static void main(String [] args) {
        scannerKey();
        
    }
    private static void scannerKey() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a base-10 number");
        while(keyboard.hasNextInt()) {
            int number = keyboard.nextInt();
            if (number < 10) {
                System.out.println("That is less than ten.");
            } else {
                System.out.println("Cool.");
            }
        }
    }
}