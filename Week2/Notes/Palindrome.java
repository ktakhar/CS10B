class Palindrome
    {
        public static void main (String [] args)
        {
            String string = "Madam";

            System.out.println();
            System.out.println("Testing whether the the following " + "string is a palindrome: ");
            System.out.println(" " + string);
            System.out.println();

            StringBuilder sb = new StringBuilder(string);
            String reversed = sb.reverse().toString();

            if (string.equalsIgnoreCase(reversed))
            {
            System.out.println("It IS a palindrome.");
            }
            else {
            System.out.println("It IS not a palindrome.");
            }
            System.out.println();
        }
    }