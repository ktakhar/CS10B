/** Parser.java - Recursive-descent parser for the interpreter/compiler
 *  unit 8 lecture notes
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified April 20, 2008
 *
 * METHOD INTERFACE:  program()
 *                    setSemantics(AValue)
 *
 * The grammar for the arithmetic language is as
 * follows:
 *
 *      <expr>     => <term> {[+,-]<term>}*
 *      <term>     => <factor> {[*,/]<factor>}*
 *      <factor>   => <symbol> | <number> | (<expr>)
 *                  | -<factor>
 *      <asst>     => <symbol> = <expr>;
 *      <program>  => {<asst>}*.
 *
 *  The methods for each rule call ask the MyScanner for lexemes;  if they
 *  match the rule, interp. or compiler semantics are applied,
 *  using the AValue methods:
 *  - interpreter:  calculate an arithmetic result or do action (assignTo())
 *  - compilers:     generate code
 *
 *  In either case, each method will return a "value":
 *  - interpreter:     the numerical value of the operation
 *  - MIPS compiler:   the register holding the value calculated by gen'd code
 *  - other compilers: a String of code for rule parsed, or name of symbols
 *
 *  Usually, semantics are accessable via the AValue-subclass objects
 *  returned by the various Parser methods, but occasionally 
 *  the parser must refer to its own copy, so as to apply the correct actions.  
 *  See the field 'semantics', and method setSemantics().

 *
 *  Semantic methods:
 *
 *  - plus()
 *  - minus()
 *  - times()
 *  - divideBy()
 *  - negate()
 *  - evaluate() ...two versions
 *  - assignTo()
 */
 
 class Parser
 {  
    private Parser() {}             // Parser can't be instantiated.
 
    // set 'semantics' to either an AValue subclass instance, making
    // correct semantics directly available to the parser:
    
    private static AValue semantics = null; 
    static void setSemantics (AValue rules)  { semantics = rules; }
    
    
    /** parse the rule:  <expr> => <term> {[+,-]<term>}*
     */
    private static AValue expr() 
            throws ParseErrorException, LexErrorException
    {  
        Lexeme  token;
        AValue  leftSide, rightSide, value = null;
        
        leftSide = term();          // get a valid "left-hand side"

        while ((token = MyScanner.get()).type == LexemeType.OP
                && (token.name.equals (Lexeme.OP_PLUS)
                    || token.name.equals (Lexeme.OP_MINUS)))
        {   
            rightSide = term();     // now, try for 1 or more {[+,-]<term>}'s:
            if (token.name.equals (Lexeme.OP_PLUS))
                 leftSide = leftSide.plus (rightSide);
            else leftSide = leftSide.minus (rightSide);
        }    
        MyScanner.unGet (token);      // "un-read" token that wasn't + or -
        return leftSide;
    }


    /** parse the rule: <term> => <factor> {[*,/]<factor>}*
     */
    private static AValue term() throws ParseErrorException, LexErrorException
    {  
        Lexeme  token;
        AValue  leftSide, rightSide, value = null;
        
        leftSide = factor();        // get a valid "left-hand side"
        while ((token = MyScanner.get()).type == LexemeType.OP
                && (token.name.equals (Lexeme.OP_TIMES)
                    || token.name.equals (Lexeme.OP_DIVIDE)))
        {   
            rightSide = factor();   // now try for 1 or more {[*,/]<factor>}'s:
            if (token.name.equals (Lexeme.OP_TIMES))
                leftSide = leftSide.times (rightSide);
            else
                leftSide = leftSide.divideBy (rightSide);
        }    
        MyScanner.unGet (token);      // "un-read" token that wasn't * or /
        return leftSide;
    }
     
    /** parse the rule:  <factor> => <symbol> | <number> | (<expr>) | -<factor>
     */
    private static AValue factor() 
            throws ParseErrorException, LexErrorException
    {   Lexeme  token;
        AValue  value;

        token = MyScanner.get();
        switch (token.type) 
        {
          case SYMBOL:             // <symbol>
            if ((value = Symtab.lookup(token)) == null)
                throw new ParseErrorException 
                        ("undefined symbol: " + token.name);
            return value.evaluate ();  // apply interp or cmp semantics
            
          case NUMBER:             // <number>
            return semantics.evaluate (token);  // apply semantics
            
          case LEFT_PAREN:         // (<expr>)
            value = expr();
            if (MyScanner.get().type != LexemeType.RIGHT_PAREN)
                throw new ParseErrorException ("Unmatched parentheses");           
            return value;
            
          case OP:                 // [+,-] <factor>
            if (token.name.equals (Lexeme.OP_MINUS))
                return factor().negate();
            if (token.name.equals (Lexeme.OP_PLUS))
                return factor();
            throw new ParseErrorException ("Illegal unary op: " + token.name);
        }
        throw new ParseErrorException ("Illegal factor");
    }


    /** parse the rule:  <asst> => <symbol> = <expr>;
     */
    private static AValue asst() throws ParseErrorException, LexErrorException
    {   
        Lexeme  leftSide;
        AValue  value;
        
        if ((leftSide = MyScanner.get()).type != LexemeType.SYMBOL)
            throw new ParseErrorException 
                ("Illegal left-hand-side for assgt stmt: " + leftSide.name);
        
        if (MyScanner.get().type != LexemeType.ASSIGN) // '=' present??
            throw new ParseErrorException ("Assignment operator expected");

        value = expr().assignTo (leftSide);      // get rhs, do assignment
        if ( MyScanner.get().type != LexemeType.SEMICOLON )
            throw new ParseErrorException 
                ("Semicolon stmt delimiter expected");
        return value;
    }


    /** parse the rule:  <program> => {<asst>}*.
     */
    static AValue program() throws ParseErrorException, LexErrorException
    { 
        Lexeme token;
        AValue value = semantics.getZeroAValue(); // Zero_avalue becomes value
                                                  // of empty program.
        while ((token = MyScanner.get()).type != LexemeType.EOP) 
        {   
            MyScanner.unGet (token);
            value = asst();
        }
        return value;   // Return value of last assignment statement.
    }
}
