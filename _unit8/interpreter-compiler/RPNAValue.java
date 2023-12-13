/** RPNAValue.java - postfix compiler for the interpreter/compiler. 
 *  unit 8 lecture notes
 *
 *  INTERFACE:  see abstract superclass AValue.  RPNAValue is part of
 *  a variant of the STRATEGY pattern, to provide various interp. and 
 *  compiler semantics to the Parser. 
 *  (see Gamma et al, "Design Patterns", Addison-Wesley, 1995, p. 315ff)
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 6, 2008
 */
 
class RPNAValue extends AValue
{   
    String pName;       // pName of this RPNAValue obj.
    
    RPNAValue()                  { pName = " "; }   // make a "zero" EngAVal.
    private RPNAValue (String s) { pName = s;}  // make EngAV. w/ pName 'val'
            
    // getter necessary for STRATEGY pattern; 
    // version depends on receiver object type, not arg or field type
    
    AValue getZeroAValue() { return new RPNAValue(); }
    
    private void setValue (RPNAValue val) { pName = val.pName;  }
    
    /*  ARITHMETIC OPERATIONS do the math, return result as RPNAValue.
     */
    AValue plus (AValue addend)
    {   return new RPNAValue (this + " " + addend + " +"); } 
    
    AValue minus (AValue subtrahend)
    {   return new RPNAValue
            (this + " " + subtrahend + " -"); } 
    
    AValue times (AValue multiplier)
    {   return new RPNAValue
            (this + " " + multiplier + " *"); } 
    
    AValue divideBy (AValue divisor)
    {   return new RPNAValue 
            (this + " " + divisor + " /"); 
    } 
    
    AValue negate ()
    {   return new RPNAValue ("-" + this); } 
    
    
    /** apply postfix semantics to the RPNAValue of a LexemeType.SYMBOL lexeme.
     *  (as retrieved from the Symbol Table)
     */
    AValue evaluate ()
    {   return this; }

    
    /** apply postfix semantics to a NUMBER lexeme.
     *  ...amounts to returning its numerical pName as an RPNAValue
     */
    AValue evaluate (Lexeme token)
    {   return new RPNAValue (String.valueOf(token.nval)); 
    }


    /** assign to lSide, a LexemeType.SYMBOL lexeme; return the RPNAValue assigned.
     *  Adds unknown lSide symbols with their NAME as value.
     */
    AValue assignTo (Lexeme lSide)
    {  
        RPNAValue symVal;

        if ( (symVal = (RPNAValue)Symtab.lookup(lSide)) != null) // there?
          symVal.setValue(new RPNAValue(lSide.toString()));// UPDATE to NAME
        else 
          Symtab.add (lSide, new RPNAValue(lSide.toString())); // ADD NAME SO
                                    // symbols print as NAME in future phrases
        System.out.println 
                (lSide.toString() + " " + this + " = "); // display assgt
        return new RPNAValue 
                (lSide.toString() + " " + this + " = "); // rtn assgt
    }
    
                                            
    /** return String representation of RPNAValue
     */                                     
    public String toString () { return pName; }
}
