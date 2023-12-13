 /** IAValue.java - Interpreter semantics for the interpreter/compiler. 
 *  Perform arithmetic operations, numerical results are returned
 *  as IAValues.
 *  unit 8 lecture notes
 *
 *  INTERFACE:  see abstract superclass AValue.  IAValue is part of
 *  a variant of the STRATEGY pattern, to provide various interp. and 
 *  compiler semantics to the Parser. 
 *  (see Gamma et al, "Design Patterns", Addison-Wesley, 1995, p. 315ff)
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 6, 2008
 */
 
class IAValue extends AValue implements Cloneable
{   
    int value;                          		// value of this IAValue object
    
    IAValue()                 { value = 0; }    // make a "zero" IAValue
    private IAValue (int val) { value = val;}   // make IAValue w/ value 'val'
            
    // getter necessary for STRATEGY pattern; 
    // version depends on receiver object type, not arg or field type
    
    AValue getZeroAValue() { return new IAValue(); }
    
    private void setValue (IAValue val) { value = val.value;  }
    
    /*  ARITHMETIC OPERATIONS do the math, return result as IAValue.
     */
    AValue plus (AValue addend)
    {  
       return new IAValue (this.value + ((IAValue)addend).value);
    }
    
    AValue minus (AValue subtrahend)
    {  
        return new IAValue (this.value - ((IAValue)subtrahend).value);
    }
    
    AValue times (AValue multiplier)
    { 
       return new IAValue (this.value * ((IAValue)multiplier).value); 
    }
    
    AValue divideBy (AValue divisor)
    {   
        if (((IAValue)divisor).value != 0)
            return new IAValue (this.value / ((IAValue)divisor).value);
        System.err.println ("fatal error: division by zero");
        System.exit (0);
        return null;        // keep compiler happy
    }
    
    AValue negate ()
    {   return new IAValue (-this.value); }
    
    
    /** apply interpreter semantics to the IAValue of a LexemeType.SYMBOL lexeme.
     *  (as retrieved from the Symbol Table)
     *  ...amounts to returning a copy of the IAValue receiver.
     */
    AValue evaluate ()
    {   
        try 
        {
            return (IAValue)this.clone();   // rtn copy of value
                
        } 
        catch (CloneNotSupportedException e) 
        {
            e.printStackTrace();
        }
        return new IAValue();                 // keep compiler happy
    }

    
    /** apply interpreter semantics to a NUMBER lexeme.
     *  ...amounts to returning its numerical value as an IAValue
     */
    AValue evaluate (Lexeme token)
    {  
       return new IAValue (token.nval); 
    }
    
                                        
    /** assign to lSide, a LexemeType.SYMBOL lexeme; return the IAVALUE assigned. 
     *  Adds lSide to symbol table if not already there.  Note 
     *  that the actual IAValue object whose reference is in 'this' ends up 
     *  as the IAValue 'value' object of the symbol's symbol table entry, 
     *  when added.  If lSide already there, the existing IAValue is updated.
     */
    AValue assignTo (Lexeme lSide)
    {  
        IAValue symVal;

        if ( (symVal = (IAValue)Symtab.lookup(lSide)) != null) // there?
            symVal.setValue (this);     // UPDATE IAVal. 'value' field
        else Symtab.add (lSide, this);   // add lSide to sym table
                                        // w/ rSide as value
        System.out.println (this);      // display value assigned
        return this;                    // return value assigned
    }
            
                                            
    /** return String representation of IAValue
     */                                     
    public String toString () 
    { 
      return String.valueOf (value);
    }
}
