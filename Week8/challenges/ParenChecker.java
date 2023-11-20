import java.util.Scanner;
import java.util.Stack;

/**
   This program uses a stack to check whether an expression has balanced
   parentheses.
*/
public class ParenChecker
{
   public static void main (String[] args)
   {
      Scanner in = new Scanner (System.in);
      System.out.print ("Enter an expression containing { [ ( ) ] }: ");
      String expression = in.nextLine();

      Stack parens = new Stack<>();

      for (int i = 0; i < expression.length(); i++)
      {
         char ch = expression.charAt(i);
         if (ch == '(' || ch == '[' || ch == '{')
         {
     

         	...
         	...


         }
         if ( ??? ) System.out.println ("YES, your expression was balanced!");
         else System.out.println ("No, it was NOT a balanced expression!");
   }
}
}

//

public class ParenChecker {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter an expression containing { [ ( ) ] }: ");
      String expression = in.nextLine();

      Stack<Character> parens = new Stack<>();

      for (int i = 0; i < expression.length(); i++) {
         char ch = expression.charAt(i);
         if (ch == '(' || ch == '[' || ch == '{') {
            parens.push(ch);
         } else if (ch == ')' || ch == ']' || ch == '}') {
            if (parens.isEmpty()) {
               System.out.println("No, it was NOT a balanced expression!");
               return; // Not balanced, exit early
            }

            char open = parens.pop();
            if ((ch == ')' && open != '(') ||
                (ch == ']' && open != '[') ||
                (ch == '}' && open != '{')) {
               System.out.println("No, it was NOT a balanced expression!");
               return; // Not balanced, exit early
            }
         }
      }

      if (parens.isEmpty()) {
         System.out.println("YES, your expression was balanced!");
      } else {
         System.out.println("No, it was NOT a balanced expression!");
      }
   }
}
