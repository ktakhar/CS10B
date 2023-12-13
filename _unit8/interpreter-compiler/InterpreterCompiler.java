/** InterpreterCompiler.java - Interpreter/compiler main class
 *  CSCI E-50b
 *  unit 8 lecture notes
 *
 *  Uses a variant of the STRATEGY pattern, to provide
 *  various compiler semantics to the Parser. 
 *  (see Gamma et al, "Design Patterns", Addison-Wesley, 1995, p. 315ff)
 *
 *  METHOD INTERFACE:  main()
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified April 28, 2008
 */
 
import java.util.Scanner;
import java.io.*;

public class InterpreterCompiler 
{   
    private static int NUM_CHOICES = 7;

    public static void main (String[] args) 
    {   
        AValue semantics;
        Scanner keyboard = new Scanner(System.in); 
        System.out.println ("INTERPRETER/COMPILER for Assignment Statements");
        showSemanticsList();

        while ((semantics = getUserSemanticChoice (keyboard)) != null)
        {   
           try 
	   {
	      Parser.setSemantics (semantics);   // INITIALIZE:
	      semantics.init();  		 // clear any static state: reg use array 
	      MyScanner.init();       		 // flush input, clear buffers
	      Symtab.init();    		 // empty symbol table
	      System.out.println ("Enter program:  ';' ends lines, '.' ends pgm");
	      System.out.print("-> ");
             
	      Parser.program();                  // do all the work!
	   } 
	   catch (ParseErrorException e) 
	   {
	       e.showParseError();
           } 
	   catch (LexErrorException e) 
	   {
               e.showLexError();
	   }
        }
        System.out.println ("...y'all come back now, hear?");
    }
    
    
    /** return AValue subclass corresponding to user's choice of 
     *  semantics, or null if user chooses 'quit'.  
     *  Here is where the STRATEGY-variant design pattern lets 
     *  user configure the pgm for each run, on the fly.
     */
    private static AValue getUserSemanticChoice (Scanner keyboard)
    {   
        int choice;
        do
        {   
            flushInput();       
            System.out.print  ("select semantics (enter 1.." + (NUM_CHOICES-2)
                               + ", " + (NUM_CHOICES-1) + " to quit, or "
                               + NUM_CHOICES + " for list): ");
            choice = keyboard.nextInt();
            if (choice == NUM_CHOICES)             // final entry? 
                showSemanticsList();               // show list, re-prompt
                
        } while (choice < 1 || choice >= NUM_CHOICES);
        
        switch (choice) 
        {
            case 1: System.out.println ("\t\t   -- INTERPRETER --");
                      return new IAValue();
            case 2: System.out.println ("\t\t  -- MIPS COMPILER --");
                      return new CAValue();
            case 3: System.out.println ("\t\t-- POST-FIX COMPILER --");
                      return new RPNAValue();
            case 4: System.out.println ("\t\t   --LISP COMPILER --");
                      return new LispAValue();
            case 5: System.out.println ("\t\t -- ENGLISH COMPILER --");
                    return new EngAValue();
            default: return null;               // case '6': quit
        }
    }
    
    private static void showSemanticsList()
    {               
        System.out.println 
            ("Available semantics:\n"
              + "\t1 - interpreter\n"
              + "\t2 - MIPS compiler\n"
              + "\t3 - post-fix compiler\n"
              + "\t4 - LISP compiler\n"
              + "\t5 - English compiler\n"
              + "\t6 - quit\n"
              + "\t7 - ...show this list again\n"); // always final entry,
    }                                               // number is NUM_CHOICES


    private static void flushInput()
    {   
       try 
       {
          System.in.skip (System.in.available());   // empty input stream
       }
       catch (IOException e) { }
    }
}
