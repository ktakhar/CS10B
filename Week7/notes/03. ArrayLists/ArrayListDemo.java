// ArrayListDemo.java

import java.util.Scanner;
import java.util.ArrayList;



/**
 * The ArrayListDemo program class demonstrates several ArrayList methods
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
public class ArrayListDemo {

    final static String SPACER = "\n\n\n"; // Controls blank spacing before command output and error messages.

    public static void main( String[] args ) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        Scanner keyboard = new Scanner( System.in ), cs;
        boolean notDone;
        String  commandLine, command;
        String  prompt =
            String.format(
                "%-14s  %-18s  %-18s\n%-14s  %-18s  %-18s\n%-14s  %-18s  %-18s\n%-14s  %-18s  %-18s\n%-14s  %-18s  %-15s: ",
                "add     (a)",                  "indexOf     (i)",                     "remove      (r)",
                "addAt   (aa)",                 "lastIndexOf (li)",                    "removeAt    (ra)",
                "clear   (clr)",                "contains    (c)",                     "removeRange (rr)",
                "isEmpty (ie)",                 "get         (g)",                     "",
                "size    (si)",                 "set         (s)",                     "quit        (q)"
            );

        do {
            commandLine = promptForStringValue( keyboard, prompt, a ).trim().toLowerCase();
            cs = new Scanner( commandLine );
            command = cs.next();
            notDone = !command.equals( "q" );

            try {
                switch( command ) {
                    case "a":   add(a,cs);     break;    case "i":   indexOf(a,cs);     break;    case "r":    remove(a,cs);        break;
                    case "aa":  addAt(a,cs);   break;    case "li":  lastIndexOf(a,cs); break;    case "ra":   removeAt(a,cs);      break;
                    case "clr": clear(a,cs);   break;    case "c":   contains(a,cs);    break;    case "rr":   removeRange(a,cs);   break;
                    case "ie":  isEmpty(a,cs); break;    case "g":   get(a,cs);         break;    case "q":                         break;
                    case "si":  size(a,cs);    break;    case "s":   set(a,cs);         break;    default: invalidCommand( command );
                }
            }
            catch( ArrayListDemoSyntaxErrorException aldsee ) {
                System.out.printf( "%s%s\n\n", SPACER, aldsee.getMessage() );
            }
            catch( Exception e ) {
                System.out.printf( "%s\n\n", e );
            }
        } while( notDone );
    }



    /**
     * invalidCommand() processes invalid commands.
     *
     * @param  command   The command.
     */
    private static void invalidCommand( String command ) throws ArrayListDemoSyntaxErrorException {
        syntaxError( "Invalid command: " + command );
    }



    /**
     * add() processes the "a intValue" command. It adds an element to the end of the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void add( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "a intValue" );
        printMethodCall( "add", a, ""+args[0] );
        a.add( args[0] );
        printArraylist( a );
    }



    /**
     * addAt() processes the "aa index intValue" command. It adds an element at the specified index in the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void addAt( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 2, "aa index intValue" );
        printMethodCall( "add", a, ""+args[0], ""+args[1] );
        a.add( args[0], args[1] );
        printArraylist( a );
    }



    /**
     * clear() processes the "clr" command. It clears the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void clear( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 0, "clr" );
        printMethodCall( "clear", a );
        a.clear();
        printArraylist( a );
    }



    /**
     * isEmpty() processes the "ie" command. It displays whether the ArrayList is empty.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void isEmpty( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 0, "ie" );
        printMethodCall( "isEmpty", a );
        System.out.printf( "%b\n\n", a.isEmpty() );
    }



    /**
     * size() processes the "si" command. It displays the size of the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void size( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 0, "si" );
        printMethodCall( "size", a );
        System.out.printf( "%d\n\n", a.size() );
    }



    /**
     * indexOf() processes the "i intValue" command. It displays the index in the ArrayList of the first occurrence of intValue.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void indexOf( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "i intValue" );
        printMethodCall( "indexOf", a, ""+args[0] );
        System.out.printf( "%d\n\n", a.indexOf( args[0] ) );
    }



    /**
     * lastIndexOf() processes the "li intValue" command. It displays the index in the ArrayList of the last occurrence of intValue.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void lastIndexOf( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "li intValue" );
        printMethodCall( "lastIndexOf", a, ""+args[0] );
        System.out.printf( "%d\n\n", a.lastIndexOf( args[0] ) );
   }



    /**
     * contains() processes the "c intValue" command. It displays whether the ArrayList contains intValue.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void contains( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "c intValue" );
        printMethodCall( "contains", a, ""+args[0] );
        System.out.printf( "%b\n\n", a.contains( args[0] ) );
    }



    /**
     * get() processes the "g index" command. It gets the value in the ArrayList at the specified index.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void get( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "g index" );
        printMethodCall( "get", a, ""+args[0] );
        System.out.printf( "%d\n\n", a.get( args[0] ) );
    }



    /**
     * set() processes the "s index intValue" command. It sets the element in the ArrayList at the specified index to the specified value.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void set( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 2, "s index intValue" );
        printMethodCall( "set", a, ""+args[0], ""+args[1] );
        a.set( args[0], args[1] );
        printArraylist( a );
    }



    /**
     * remove() processes the "r intValue" command. It removes the first occurrence of intValue from the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void remove( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "r intValue" );
        printMethodCall( "remove", a, String.format( "new Integer( %d )", args[0] ) );
        boolean b = a.remove( new Integer( args[0] ) );
        printArraylistPlusBoolean( a, b );
    }



    /**
     * removeAt() processes the "ra index" command. It removes the value at the specified index in the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void removeAt( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 1, "ra index" );
        printMethodCall( "remove", a, ""+args[0] );
        a.remove( args[0] );
        printArraylist( a );
    }



    /**
     * removeRange() processes the "rr fromIndex toIndex" command. It removes the values in the ArrayList from fromIndex (inclusive)
     * to toIndex (exclusive). Note that the removeRange() method in the ArrayList class is protected. That means it can only be
     * accessed from subclasses of ArrayList. Some research on Google suggests that a more flexible scheme to achieve the same goal
     * is to instead execute a.subList(  fromIndex, toIndex ).clear();
     *
     * @param  a    The demo program's ArrayList object.
     * @param  cs   Scanner constructed from the command line .
     */
    private static void removeRange( ArrayList<Integer> a, Scanner cs ) throws ArrayListDemoSyntaxErrorException {
        int[] args = getArgs( cs, 2, "rr fromIndex toIndex" );
        printMethodCall( "subList", a, ""+args[0], ""+args[1], "clear" );
        a.subList(  args[0], args[1] ).clear();
        printArraylist( a );
    }



    /**
     * printArraylist() prints the ArrayList.
     *
     * @param  a    The demo program's ArrayList object.
     */
    private static void printArraylist( ArrayList<Integer> a ) {
        System.out.printf( "%s\n\n", a.toString() );
    }



    /**
     * printArraylist() prints the ArrayList plus a boolean value that was returned by the demo'd ArrayList method.
     *
     * @param  a    The demo program's ArrayList object.
     * @param  b    boolean value that was returned by the demo'd ArrayList method.
     */
    private static void printArraylistPlusBoolean( ArrayList<Integer> a, boolean b ) {
        System.out.printf( "%s (method returned %b)\n\n", a.toString(), b );
    }



    /**
     * getArgs() retrieves and validates the arguments on the command line
     *
     * @param  cs   Scanner constructed from the command line .
     * @param  numberOfArgs    The expected number of arguments.
     * @param  syntaxErrorMessage The error message if the actual # of arguments is different from numberOfArgs
     */
    private static int[] getArgs( Scanner cs, int numberOfArgs, String syntaxErrorMessage ) throws ArrayListDemoSyntaxErrorException {
        int[] args = new int[numberOfArgs];
        for ( int i=0; i<numberOfArgs; i++ ) {
            if ( !cs.hasNextInt() ) { syntaxError( syntaxErrorMessage ); }
            args[i] = cs.nextInt();
        }
        if ( cs.hasNextInt() ) { syntaxError( syntaxErrorMessage ); }
        return args;
    }



    /**
     * syntaxError() processes a syntax error in the user's response to the command prompt.
     *
     * @param  s    The correct syntax.
     */
    private static void syntaxError( String s ) throws ArrayListDemoSyntaxErrorException {
        throw new ArrayListDemoSyntaxErrorException( s );
    }



    /**
     * printMethodCall() prints the demo'd method call.
     *
     * @param  methodName  The method's name.
     * @param  a           The ArrayList.
     * @param  args        The arguments passed to the method.
     */
    private static void printMethodCall( String methodName, ArrayList a, String...args ) {
        System.out.printf( "%s%s", SPACER, getMethodCallString( methodName, a, args ) );
    }



    /**
     * getMethodCallString() returns the String describing the demo'd method call.
     *
     * @param  methodName  The method's name.
     * @param  a           The ArrayList.
     * @param  args        The arguments passed to the method.
     * @return             the String describing the demo'd method call.
     */
    private static String getMethodCallString( String methodName, ArrayList a, String...args ) {
        switch( args.length ) {
            case 0: return String.format( "%s.%s() produced ", a.toString(), methodName );
            case 1: return String.format( "%s.%s( %s ) produced ", a.toString(), methodName, args[0] );
            case 2: return String.format( "%s.%s( %s, %s ) produced ", a.toString(), methodName, args[0], args[1] );
            case 3: return String.format( "%s.%s( %s, %s ).%s() produced ", a.toString(), methodName, args[0], args[1], args[2] );
            default: return "";
        }
    }



    /**
     * promptForStringValue prompts the user and returns the String response.
     *
     * @param  keyboard   Scanner used to read response.
     * @param  prompt     String used to prompt the user.
     * @return String containing user's response.
     */
    private static String promptForStringValue( Scanner keyboard, String prompt, ArrayList<Integer> a ) {
        printArraylist( a );
        System.out.print( prompt );
        return keyboard.nextLine();
    }
}



/**
 * The ArrayListDemoSyntaxErrorException template class is a custom exception thrown
 * when syntax errors are detected, and caught in the command processing loop.
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
class ArrayListDemoSyntaxErrorException extends Exception {
    public ArrayListDemoSyntaxErrorException()           { super();  }
    public ArrayListDemoSyntaxErrorException( String s ) { super( s ); }
}