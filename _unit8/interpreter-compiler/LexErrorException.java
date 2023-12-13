/** LexErrorException.java 
 *  - Lexical exception subclass for error handling 
 *      in the interpreter/compiler
 *  unit 8 lecture notes
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 5, 2008
 */


/** Class LexErrorException 
 *  - Lexical analyzer error handling for the interpreter/compiler
 */
 
class LexErrorException extends Exception
{  
   String description;

    LexErrorException (String description)
    {   this.description = "Lexical error: " + description; }
    
    void showLexError()
    {
        System.err.println (description);
    }
}
