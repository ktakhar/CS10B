/** MyScanner.java - Lexical analyzer for the interpreter/compiler
 *  unit 8 lecture notes
 *  Runs in both unix and Codewarrior pro 5 (see below)
 *
 *  Scans lexemes from keyboard, classifies their type, returns them as 
 *  Lexeme objects;  provides one-Lexeme "pushback":
 *
 *  METHOD INTERFACE:
 *  - init()         // initialize the MyScanner for fresh input 
 *  - get()          // gets next lexeme as a Lexeme
 *  - unGet (Lexeme) // pushes Lexeme "back onto the input stream"
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 4, 2008
 */

import java.io.*;       // for IOException

class MyScanner
{  
    private MyScanner() {}                  // MyScanner can't be instantiated
    
    // character constants
    public static final char PLUS_SIGN   = '+';
    public static final char MINUS_SIGN  = '-';
    public static final char TIMES_SIGN  = '*';
    public static final char DIVIDE_SIGN = '/';
    public static final char EQUAL_SIGN  = '=';
    public static final char LEFT_PAREN  = '(';
    public static final char RIGHT_PAREN = ')';
    public static final char SEMICOLON   = ';';
    public static final char EOP_MARKER  = '.'; // End-Of-Program

    // Two 1-item pushback buffers: each serves as its own flag; if not set
    //  the next item to read is in the buffer, else waiting in input stream:
    
    private static int charPushback;        // 1-char buffer
    private static Lexeme lexPushback;      // 1-Lexeme buffer
    static PushbackReader in                   // FINE IN UNIX.
     = new PushbackReader (new InputStreamReader(System.in));

    
    /** initialize MyScanner for fresh input:  flush input stream, clear buffers.
     */
    static void init()  
    {  
        charPushback = Integer.MAX_VALUE;   // "huge" => empty
        lexPushback  = null;                //  null  => empty

        try  
        {    // empty out the input stream (may be empty)
            System.in.skip (System.in.available());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


    /** return next token from input; either from keyboard or previously
     *  read and "pushed back" onto the input stream in buffer 'lexPushback'
     *  return EOF Lexeme if End-Of-File (end-of-stream)
     */
    static Lexeme get() throws LexErrorException
    {
        int c = 0;                          // for reading chars
         
        if (lexPushback != null)            // lexeme pushed back?
        {  
            Lexeme temp = lexPushback;      // ..save the Lexeme,
            lexPushback = null;             // ..empty the buffer,
            return temp;                    // ..return the Lexeme
        }

        try 
        {   // first, strip leading white space, get first non-white char
                // w/ a java idiom, cleaner than the more normal:
                // while (Character.isWhitespace((char)(c=in.read()))) continue;  

            for (c=in.read(); Character.isWhitespace((char)c); c=in.read() )
                continue;
                
            if (c == -1)
                return new Lexeme (LexemeType.EOF); // ..but watch for eof
                
            if (Character.isLetter((char)c) )   // Letter => Symbol
                return  getSymbol ((char)c);
                
            if (Character.isDigit((char)c) )    // Digit => number
                return getNumber ((char)c);
                
            return getOpOrPunct ((char)c);      // Other => op, (, )...
                                                // or illegal char
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;                            // keep the compiler happy
    }


    /** return a LexemeType.SYMBOL Lexeme by converting ch and subsequent chars
     *  into the corresponding Symbol.  Ch must be a alphabetic char
     */ 
    private static Lexeme getSymbol (char ch) 
            throws IOException, LexErrorException
    {   
        StringBuilder sb = new StringBuilder();

        while (Character.isLetterOrDigit(ch)) // gobble digit chars...
        {   
            sb.append (ch);                   // append to a StringBuilder
            ch = (char)in.read();
        }
        in.unread (ch);                       // put back non-alfanum "marker"
        return new Lexeme (LexemeType.SYMBOL, sb.toString());
    }


    /** return a NUMBER Lexeme by converting ch and subsequent chars
     *  into the corresponding number.  Ch must be a digit char
     */ 
    private static Lexeme getNumber (char ch) throws 
                                   IOException, LexErrorException
    {   
        StringBuilder sb = new StringBuilder();
        
        while (Character.isDigit(ch))      // gobble digit chars...
        { 
            sb.append (ch);                // append to a StringBuilder
            ch = (char) in.read();
        }
        in.unread (ch);                    // put back non-digit endOf# mark
        return new Lexeme (LexemeType.NUMBER, sb.toString());
    }

 
    /** convert ch into the corresponding Lexeme.  Ch holds a char
     *  that is a legal 1-char lexeme
     */
    private static Lexeme getOpOrPunct (char ch)
            throws LexErrorException
    {  
       switch (ch)
       {
          case PLUS_SIGN:   return new Lexeme (LexemeType.OP, Lexeme.OP_PLUS);
          case MINUS_SIGN:  return new Lexeme (LexemeType.OP, Lexeme.OP_MINUS);
          case TIMES_SIGN:  return new Lexeme (LexemeType.OP, Lexeme.OP_TIMES);
          case DIVIDE_SIGN: return new Lexeme (LexemeType.OP, Lexeme.OP_DIVIDE);
          case EOP_MARKER:  return new Lexeme (LexemeType.EOP);
          case LEFT_PAREN:  return new Lexeme (LexemeType.LEFT_PAREN);
          case RIGHT_PAREN: return new Lexeme (LexemeType.RIGHT_PAREN);
          case SEMICOLON:   return new Lexeme (LexemeType.SEMICOLON);
          case EQUAL_SIGN:  return new Lexeme (LexemeType.ASSIGN);
          default:          throw new LexErrorException("Illegal char: "+ch);
       } 
    }
    
    
    /** put lex "back" in the input stream, so next call to get()
    *  will return lex.  Multiple unGet()'s without intervening get()'s
    *  will fail.
    */
    static void unGet (Lexeme lex) throws LexErrorException
    {   if (lexPushback != null)
            throw new LexErrorException ("Lexeme buffer overflow!");
        lexPushback = lex;    
    }
    
}
