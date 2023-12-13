/** EngAValue.java - English compiler for the interpreter/compiler. 
 *  unit 8 lecture notes
 *
 *  INTERFACE:  see abstract superclass AValue.  EngAValue is part of
 *  a variant of the STRATEGY pattern, to provide various interp. and 
 *  compiler semantics to the Parser. 
 *  (see Gamma et al, "Design Patterns", Addison-Wesley, 1995, p. 315ff)
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 6, 2008
 */
 
class EngAValue extends AValue
{   
    String pName;          // pName of this EngAValue obj.
    
    EngAValue()                  { pName = " "; }  // make a "zero" EngAVal.
    private EngAValue (String s) { pName = s;}  // make EngAV. w/ pName 'val'
            
    // getter necessary for STRATEGY pattern; 
    // version depends on receiver object type, not arg or field type
    
    AValue getZeroAValue() { return new EngAValue(); }
    
    private void setValue (EngAValue val) { pName = val.pName;  }
    
    /*  ARITHMETIC OPERATIONS do the math, return result as EngAValue.
     */
    AValue plus (AValue addend)
    {  
       return new EngAValue ("the sum of " + this + " and " + addend); 
    } 
    
    AValue minus (AValue subtrahend)
    {   
       return new EngAValue ("the difference of " + this + " minus " + subtrahend);
    } 
    
    AValue times (AValue multiplier)
    {  
       return new EngAValue ("the product of " + this + " and " + multiplier);
    } 
    
    AValue divideBy (AValue divisor)
    {   return new EngAValue 
            ("the quotient of " + this + " divided by " + divisor); 
    } 
    
    AValue negate ()
    {   return new EngAValue ("negative " + this); } 
    
    
    /** apply English semantics to the EngAValue of a LexemeType.SYMBOL lexeme.
     *  (as retrieved from the Symbol Table)
     */
    AValue evaluate ()
    {  
       return this; 
    }

    
    /** apply English semantics to a NUMBER lexeme.
     *  ...amounts to returning its numerical pName as an EngAValue
     */
    AValue evaluate (Lexeme token)
    {   
       return new EngAValue (String.valueOf(token.nval)); 
    }


    /** assign to lSide, a LexemeType.SYMBOL lexeme; return the EngAValue assigned.
     *  Adds unknown lSide symbols with their NAME as value.
     */
    AValue assignTo (Lexeme lSide)
    { 
        EngAValue symVal;

        if ( (symVal = (EngAValue)Symtab.lookup(lSide)) != null) // there?
          symVal.setValue(new EngAValue(lSide.toString())); // UPDATE to NAME
        else 
          Symtab.add (lSide, new EngAValue(lSide.toString())); // ADD NAME SO
                                    // symbols print as NAME in future phrases
        System.out.println 
                (lSide + " gets " + this); // display assgt phrase
        return new EngAValue 
                (lSide + " gets " + this); // rtn assgt phrase
    }
    
                                            
    /** return String representation of EngAValue
     */                                     
    public String toString () 
    {
       return pName;
    }
}
