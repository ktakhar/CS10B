/** Symtab.java - Symbol table for the interpreter/compiler
 *  unit 8 lecture notes
 *  Implemented as a simple linked list of SymEntry objects, 
 *  for clarity vs any pretense of speed.
 *  Table entries will be SymEntry objects, holding LexemeType.SYMBOL/value pairs.
 *  It is illegal to replace an existing Symbol, though it is ok to change 
 *      a Symbol's value.
 *
 *  METHOD INTERFACE:
 *  -  void init()               // initialize to empty symbol table
 *  -  AValue lookup (Lexeme)    // look up a symbol, return it's value
 *  -  void add (Lexeme, AValue) // add symbol/value pair to table
 *
 *  Symbol table entries are defined herein as an Inner Class (SymEntry)
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 3, 2008
 */
 
class Symtab
{                                   // disable default constructor to ensure 
    private Symtab() {}             // Symtab can't be instantiated

    private static SymEntry table = null;   // a singly linked list of SymEntrys.
    
    //=================================================================
    // first, define an "inner class" to represent symbol table entries.
    // See Savitch, "Java, An Intro..." 1999 ed., pp. 393-6
    
    static class SymEntry           // (no 'this' for enclosing class object)       
    {                               // .. can still be instantiated!
        private String name;        // symbol's print name
        private AValue sValue;      // its value
        private SymEntry next;      // link to next SymEntry in list
        
        SymEntry (String name, AValue sValue) 
        {   
            this.name = name;
            this.sValue = sValue;
            this.next = null;
        }
        
        public String toString()
        {   return "SymEntry: " + name + " " + sValue; }
    } //===============================================================
    
    
    /** initialize the Symbol Table
     */
    static void init()  { table = null; }
        
        
    /** add sym/value pair to the table.  Assumes sym is not already present.  
     *  Sym is inserted at the head of the linked list holding the table
     *  (AValue ref'd by 'value' becomes sValue field of new SymEntry)
     */
    static void add (Lexeme sym, AValue value)
    { 
      SymEntry currentSymbolTable = table;    // save old table ref
          table = new SymEntry (sym.name, value); // make new head node,
      table.next = currentSymbolTable;        // tack on old table!
    }
    
            
    /** look up 'sym' in table, return sym's 'sValue' field if found, else null
     *  I.e., can see:  1: whether a sym already exists, (rtns non-null)
     *                  2: sValue of existing symbol (the non-null rtn'd)
     */ 
    static AValue lookup (Lexeme sym)
    {  
        for (SymEntry p=table;  p!=null;  p = p.next)
            if (p.name.equals(sym.name))  return p.sValue;
        return null;
    }

    /** return String representation of a Symtab object.  Can't override
     *  Object.toString because this is static
     */
/**    static String symtab2String() 
    {   
        StringBuilder buf = new StringBuilder ("Symbol Table:\n");
        
        for (SymEntry p=table; p!=null; p = p.next)
            buf.append(p.toString()).append("\n");
        return buf.toString();
    }
**/
}
