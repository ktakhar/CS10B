// Palindrome.java

/**
 * Recursive method that determines if a string is a palindrome.
 * PSET1: Exercise 5
 * 
 * @author Kuljit Takhar
 * @version Last modified 15_Sept_2023
 * 
 **/

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();

        input = input.toLowerCase();

        if (isPalindrome(input)) {
            System.out.println("It's a palindrome!");
        } else {
            System.out.println("It's not a palindrome.");
        }
    }

    public static boolean isPalindrome(String s) {

        if (s.length() <= 1) {
            return true;
        } else {
            int startIndex = 0;
            while (startIndex < s.length() && !Character.isLetterOrDigit(s.charAt(startIndex))) {
                startIndex++;
            }
            int endIndex = s.length() - 1;
            while (endIndex >= 0 && !Character.isLetterOrDigit(s.charAt(endIndex))) {
                endIndex--;
            }
            if (startIndex >= endIndex) {
                return true;
            }
            if (Character.toLowerCase(s.charAt(startIndex)) != Character.toLowerCase(s.charAt(endIndex))) {
                return false;
            }
            return isPalindrome(s.substring(startIndex + 1, endIndex));
        }
    }
}