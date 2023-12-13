/** Lexeme.java - Lexeme object for the interpreter/compiler
 *  unit 8 lecture notes
 *  The Scanner will break the input into lexemes and package 
 *  their NAME and TYPE as instances of Lexeme.  NUMBER lexemes will 
 *  also hold their numerical value.
 *
 *  METHOD INTERFACE:
 *  - two constructors
 *  - toString()
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 4, 2008
 */


enum LexemeType {SYMBOL, NUMBER, OP, ASSIGN, EOP, LEFT_PAREN, 
                  RIGHT_PAREN, SEMICOLON, EOF};
 
class Lexeme
{   

    // Operator Lexeme "names":
    public static final String OP_PLUS   = "+";
    public static final String OP_MINUS  = "-";
    public static final String OP_TIMES  = "*";
    public static final String OP_DIVIDE = "/";
    
    // for Lexemes w/o names
    public static final String NO_NAME   = " "; 
        
    LexemeType type;  // kind of lexeme:  LexemeType.SYMBOL, NUMBER, OP...
    String name;      // the chars read that formed the lexeme
    int nval;         // the numeric value of NUMBER lexemes
    
    Lexeme (LexemeType type, String name)
    {   
	this.type = type;
        this.name = name;
        if (type == LexemeType.NUMBER) 
            nval = Integer.parseInt (name); // convert String of digits...
    }
    
    Lexeme (LexemeType type)
    {   
       this.type = type;
       name = NO_NAME;
       nval = 0;
    }
    
    // return String representation of Lexeme
    public String toString() { return name; }
}
