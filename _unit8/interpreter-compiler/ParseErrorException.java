/** ParseErrorException.java 
 *  - Parser exception subclasses for error handling 
 *      in the interpreter/compiler
 *  unit 8 lecture notes
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified March 3, 2008
 */

/** Class ParseErrorException
 *  - Parser error handling for the interpreter/compiler 
 */
class ParseErrorException extends Exception
{   String description;

    ParseErrorException (String description)
    {   this.description = "Parse error: " + description; }
    
    void showParseError()
    {
        System.err.println (description);
    }
}
