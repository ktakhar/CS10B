/** LispAValue.java - LISP compiler for the LISP/compiler. 
 *  unit 8 lecture notes
 *
 *  INTERFACE:  see abstract superclass AValue.  LispAValue is part of
 *  a variant of the STRATEGY pattern, to provide various interp. and 
 *  compiler semantics to the Parser. 
 *  (see Gamma et al, "Design Patterns", Addison-Wesley, 1995, p. 315ff)
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 5, 2008
 */
 
class LispAValue extends AValue
{   
    String pName;       // pName of this LispAValue obj.
    
    LispAValue()                  { pName = " "; }  // make a "zero" LispAVal.
    private LispAValue (String s) { pName = s;} // make LispAV. w/ pName 'val'
            
    // getter necessary for STRATEGY pattern; 
    // version depends on receiver object type, not arg or field type
    
    AValue getZeroAValue() { return new LispAValue(); }
    
    private void setValue (LispAValue val) { pName = val.pName;  }
    
    /*  ARITHMETIC OPERATIONS do the math, return result as LispAValue.
     */
    AValue plus (AValue addend)
    {   return new LispAValue ("(+ " + this + " " + addend + ')'); } 
    
    AValue minus (AValue subtrahend)
    {   return new LispAValue ("(- " + this + " " + subtrahend +')'); } 
    
    AValue times (AValue multiplier)
    {   return new LispAValue ("(* " + this + " " + multiplier + ')'); } 
    
    AValue divideBy (AValue divisor)
    {   return new LispAValue ("(/ " + this + " " + divisor + ')'); } 
    
    AValue negate ()
    {   return new LispAValue ("(- " + this + ')'); } 
    
    
    /** apply LISP semantics to the LispAValue of a LexemeType.SYMBOL lexeme.
     *  (as retrieved from the Symbol Table)
     */
    AValue evaluate ()
    {  
       return this;
    }

    
    /** apply LISP semantics to a NUMBER lexeme.
     *  ...amounts to returning its numerical pName as an LispAValue
     */
    AValue evaluate (Lexeme token)
    {   return new LispAValue (String.valueOf(token.nval)); 
    }


    /** assign to lSide, a LexemeType.SYMBOL lexeme; return the LispAValue assigned.
     *  Adds unknown lSide symbols with their NAME as value.
     */
    AValue assignTo (Lexeme lSide)
    {   
        LispAValue symVal;

        if ( (symVal = (LispAValue)Symtab.lookup(lSide)) != null) // there?
          symVal.setValue(new LispAValue(lSide.toString()));// UPDATE to NAME
        else 
          Symtab.add (lSide, new LispAValue(lSide.toString())); // ADD NAME SO
                                    // symbols print as NAME in future s-exprs
        System.out.println 
                ("(setq " + lSide + " " + this + ')'); // display assgt s-expr
        return new LispAValue 
                ("(setq " + lSide + " " + this + ')'); // rtn assgt s-expr
    }
    
// THIS VERSION GIVES MORE INTERESTING RESULTS, THOUGH NOT WHAT LISP _DOES_
// THIS IS MORE LIKE A QUASI-INTERPRETER...                                     
/***    /** assign to lSide, a LexemeType.SYMBOL lexeme; return the LispAValue assigned. Note that
     *  actual LispAValue object whose reference is in 'this' ends up as the
     *  LispAValue 'value' object of the symbol's symbol table entry, when 
     *  added.  If lSide already there, the existing LispAValue is updated.
     */
/***    AValue assignTo (Lexeme lSide)
    {   LispAValue symVal;

        if ( (symVal = (LispAValue)Symtab.lookup(lSide)) != null) // there?
            symVal.setValue (this);     // UPDATE IAVal. 'pName' field
        else 
            Symtab.add (lSide, this);   // add lSide to sym table
                                        // w/ rSide as val; rtn copy
        System.out.println 
                ("(setq " + lSide + " " + this + ')'); // display assgt s-expr
        return new LispAValue 
                ("(setq " + lSide + " " + this + ')'); // rtn assgt s-expr
    }
***/            
                                            
    /** return String representation of LispAValue
     */                                     
    public String toString () { return pName; }
}
