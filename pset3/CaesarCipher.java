// CaesarCipher 

/**
 * PSET3 - Problem 7
 * 
 * @author Kuljit Takhar
 * @version October 28 2023
 * 
 */

import java.io.*;
import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        // Display a welcome message
        System.out.println("Welcome to CaesarCipher");

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Ask the user to choose between enciphering, deciphering, or exiting
            System.out.println("Enter 1 to encipher, or 2 to decipher (-1 to exit): ");
            choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                System.out.print("What shift should I use? ");
                int shift = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("What is the input file name? "); // encipher: hello.txt decipher: encodedHello.txt
                String inputFileName = scanner.nextLine();

                System.out.print("What is the output file name? "); // encipher: encodedHello.txt decipher: originalHello.txt
                String outputFileName = scanner.nextLine();

                if (choice == 1) {
                    // Encipher the input file and display a confirmation message
                    if (encipherFile(inputFileName, outputFileName, shift)) {
                        System.out.println("DONE!");
                    } else {
                        System.out.println("Error enciphering the file.");
                    }
                } else if (choice == 2) {
                    // Decipher the input file and display a confirmation message
                    if (decipherFile(inputFileName, outputFileName, shift)) {
                        System.out.println("DONE!");
                    } else {
                        System.out.println("Error deciphering the file.");
                    }
                }
            } else if (choice != -1) {
                System.out.println("Invalid choice. Please enter 1, 2, or -1.");
            }
        } while (choice != -1);
    }

    /**
     * Encipher a given input string using the Caesar Cipher algorithm.
     *
     * @param input The input string to be enciphered.
     * @param shift The shift value for the Caesar Cipher.
     * @return The enciphered string.
     */
    
    public static String caesarEncipher(String input, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            char upperCaseChar = Character.toUpperCase(c); // Convert to uppercase
            if (Character.isLetter(upperCaseChar)) {
                char encipheredChar = (char) (((upperCaseChar - 'A' + shift) % 26) + 'A');
                result.append(encipheredChar);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Decipher a given input string using the Caesar Cipher algorithm.
     *
     * @param input The input string to be deciphered.
     * @param shift The shift value for the Caesar Cipher.
     * @return The deciphered string.
     */
   
    public static String caesarDecipher(String input, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            char upperCaseChar = Character.toUpperCase(c); // Convert to uppercase
            if (Character.isLetter(upperCaseChar)) {
                char decipheredChar = (char) (((upperCaseChar - 'A' - shift + 26) % 26) + 'A');
                result.append(decipheredChar);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Encipher the contents of an input file and write the result to an output file.
     *
     * @param inputFileName  The name of the input file.
     * @param outputFileName The name of the output file.
     * @param shift          The shift value for the Caesar Cipher.
     * @return True if enciphering is successful, false otherwise.
     */
    
    public static boolean encipherFile(String inputFileName, String outputFileName, int shift) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            String line;
            while ((line = reader.readLine()) != null) {
                String encipheredLine = caesarEncipher(line, shift);
                writer.write(encipheredLine);
                writer.newLine();
            }

            reader.close();
            writer.close();
            System.out.println("Reading from input file: " + inputFileName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Decipher the contents of an input file and write the result to an output file.
     *
     * @param inputFileName  The name of the input file.
     * @param outputFileName The name of the output file.
     * @param shift          The shift value for the Caesar Cipher.
     * @return True if deciphering is successful, false otherwise.
     */
    
    public static boolean decipherFile(String inputFileName, String outputFileName, int shift) {
        return encipherFile(inputFileName, outputFileName, 26 - shift);
    }
}
