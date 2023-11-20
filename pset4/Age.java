// Age.java

/**
 * PSET4 - #1
 * 
 * @author Kuljit Takhar
 * @version November 4 2023
 * 
 * Prompts the user to enter their age
 * Converts the entered age from a string to an integer
 * Displays a message based on the age
 * 
 */

import javax.swing.*;

public class Age {
    public static void main(String[] args) {
        // Prompt the user to enter their age and store it as a string
        String ageText = JOptionPane.showInputDialog(null, "Please Enter Age");
        
        // Convert the age string to an integer
        int age = Integer.parseInt(ageText);

        // Check if the age is less than 40
        if (age < 40) {
            // If the age is less than 40, display "You are young"
            JOptionPane.showMessageDialog(null, "You are young");
        } else {
            // If the age is 40 or greater, display "You used to be young."
            JOptionPane.showMessageDialog(null, "You used to be young.");
        }
        System.exit(0);
    }
}