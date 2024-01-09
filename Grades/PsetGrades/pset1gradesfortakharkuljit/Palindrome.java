//@@ 24 out of 25 points

//@@     o Every method except main() needs a method-level comment immediately before the method, saying what the method does.
//@@     o Outstanding implementation.

//@@ Passed all 26 isPalindrome() tests:

//@@  1. Passed isPalindrome ( ""           ) test.       Expected: true        Received: true
//@@  2. Passed isPalindrome ( "*"          ) test.       Expected: true        Received: true
//@@  3. Passed isPalindrome ( "!@"         ) test.       Expected: true        Received: true
//@@  4. Passed isPalindrome ( "!@#"        ) test.       Expected: true        Received: true
//@@  5. Passed isPalindrome ( "m"          ) test.       Expected: true        Received: true
//@@  6. Passed isPalindrome ( "_m"         ) test.       Expected: true        Received: true
//@@  7. Passed isPalindrome ( "m_"         ) test.       Expected: true        Received: true
//@@  8. Passed isPalindrome ( "mm"         ) test.       Expected: true        Received: true
//@@  9. Passed isPalindrome ( "_mm"        ) test.       Expected: true        Received: true
//@@ 10. Passed isPalindrome ( "mm_"        ) test.       Expected: true        Received: true
//@@ 11. Passed isPalindrome ( "mn"         ) test.       Expected: false       Received: false
//@@ 12. Passed isPalindrome ( "mom"        ) test.       Expected: true        Received: true
//@@ 13. Passed isPalindrome ( "mon"        ) test.       Expected: false       Received: false
//@@ 14. Passed isPalindrome ( "maam"       ) test.       Expected: true        Received: true
//@@ 15. Passed isPalindrome ( "mabm"       ) test.       Expected: false       Received: false
//@@ 16. Passed isPalindrome ( "mbam"       ) test.       Expected: false       Received: false
//@@ 17. Passed isPalindrome ( "madam"      ) test.       Expected: true        Received: true
//@@ 18. Passed isPalindrome ( "madbm"      ) test.       Expected: false       Received: false
//@@ 19. Passed isPalindrome ( "madan"      ) test.       Expected: false       Received: false
//@@ 20. Passed isPalindrome ( "zrace carz" ) test.       Expected: true        Received: true
//@@ 21. Passed isPalindrome ( "00+"        ) test.       Expected: true        Received: true
//@@ 22. Passed isPalindrome ( "01+"        ) test.       Expected: false       Received: false
//@@ 23. Passed isPalindrome ( "cigar? toss it in a can, it is so tragic!" ) test.       Expected: true        Received: true
//@@ 24. Passed isPalindrome ( "\"naomi, sex at noon taxes,\" i moan."     ) test.       Expected: true        Received: true
//@@ 25. Passed isPalindrome ( "marge let a moody baby doom a telegram."   ) test.       Expected: true        Received: true
//@@ 26. Passed isPalindrome ( "\"my gym tasks are too lonely?\" a jay leno looter asks at my gym." ) test.       Expected: true        Received: true

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