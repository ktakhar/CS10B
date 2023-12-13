/** AValue.java - Abstract superclass for semantics
 *  of the interpreter/compiler.  
 *  Concrete subclasses will implement arithmetic operations:
 *  -  compilers:     manage registers, generate code
 *  -  interpreter:  do the math to produce partial results
 *
 *  unit 8 lecture notes
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 3. 2008
 */
 
abstract class AValue
{   
    void init() { }     						// allow subclasses to init if they need to
     
    // SEMANTIC METHODS
    abstract AValue plus (AValue addend);
    abstract AValue minus (AValue subtrahend);
    abstract AValue times (AValue multiplier);
    abstract AValue divideBy (AValue divisor);
    abstract AValue negate();
    abstract AValue evaluate();                	// for symbol Lexemes
    abstract AValue evaluate (Lexeme token);    // for number Lexemes
    abstract AValue assignTo (Lexeme token);
    
    // UTILITIES
    abstract AValue getZeroAValue();            // provide "zero" object
    
    abstract public String toString ();
}
