/** CAValue.java - MIPS Compiler semantics
 *  for the compiler/compiler.
 *  unit 8 lecture notes
 *
 *  A CAValue is an object holding type & number of of some register.
 *  MIPS code is generated as a side effect of the various methods,
 *  as coordinated by the Parser
 *
 *  INTERFACE:  see abstract superclass AValue.
 *
 *  Chris Morrison, Dr. Henry H. Leitner
 *  Last Modified May 3, 2008
 */

enum RegisterType {ZERO, TEMPORARY, PERMANENT};

class CAValue extends AValue implements Cloneable
{
    // MIPS registers are temporaries $t0, ... $t<MAX...>,
    // permanent registers $s0, ..., $s<MAX...>,
    // and zero $0,
    private static final int MAX_TEMPORARY_REGS = 8;
    private static final int MAX_PERMANENT_REGS = 8;


    // Track temporary and permanent register usage:
    // (array [n] == true) => $<t,s>n is in use
    //
    private static boolean temporaryRegUsage []
        = new boolean [MAX_TEMPORARY_REGS];
    private static boolean permanentRegUsage []
        = new boolean [MAX_PERMANENT_REGS];

    RegisterType regType;        // ZERO, TEMP., PERM.
    int regNumber;      // 0 .. <MAX...>

    /** clear away remains of previous compile; make all regs available.
     */
    void init()
    {
       temporaryRegUsage  = new boolean [MAX_TEMPORARY_REGS];
       permanentRegUsage  = new boolean [MAX_PERMANENT_REGS];
    }

    /** return a "zero register"
     */
    CAValue() { regType = RegisterType.ZERO; regNumber = 0; }

    /** return a CAValue representing
     *  register 'regNum' of type 'regType'
     */
    private CAValue (RegisterType regType, int regNum)
    {
       this.regType = regType;
       this.regNumber  = regNum;
    }

    // getter/setters necessary for STRATEGY pattern; client must use
    // method access on AValue to correctly obtain CAValue...

    AValue getZeroAValue()      { return new CAValue(); }

    //-------------------------------------------------------------
    /*  ARITHMETIC OPERATIONS do the math, return result as CAValue.
     *  GetValue() used to take care of figuring which AValue subclass
     *  was passed as AValue.  Simple field access requires a cast.
     */
    AValue plus (AValue addend)
    {   return genArithOpCode ("add ", this, (CAValue)addend); }

    AValue minus (AValue subtrahend)
    {   return genArithOpCode ("sub ", this, (CAValue)subtrahend); }

    AValue times (AValue multiplier)
    {   return genArithOpCode ("mul ", this, (CAValue)multiplier); }

    AValue divideBy (AValue divisor)
    {   return genArithOpCode ("div ", this, (CAValue)divisor); }

    AValue negate ()
    {   return genArithOpCode ("sub ", (CAValue)getZeroAValue(), this); }

    private CAValue genArithOpCode (String opcode, CAValue src1, CAValue src2)
    {
        CAValue dest = getTempReg();
        System.out.println ('\t' + opcode + " " + dest
                            + ", " + src1 + ", " + src2);
        src1.freeRegIfTempReg();
        src2.freeRegIfTempReg();
        return dest;
    }


    /** allocate temporary register, return as CAValue
     */
    private CAValue getTempReg()
    {
        for (int i=0; i < MAX_TEMPORARY_REGS; i++)
        {
            if (! temporaryRegUsage[i])
            {
                temporaryRegUsage[i] = true;
                return new CAValue (RegisterType.TEMPORARY, i);
            }
        }
        System.err.println ("fatal error: ran out of temporary registers");
        System.exit (0);
        return null;        // keep compiler happy
    }

    /** free register held by receiver IFF it's a TEMPORARY register
     */
    private void freeRegIfTempReg()
    {
        if (regType == RegisterType.TEMPORARY)
            temporaryRegUsage [regNumber] = false;  // mark it reusable
    }


    /** allocate permanent register, return as CAValue
     */
    private CAValue getPermReg()
    {
        for (int i=0; i < MAX_PERMANENT_REGS; i++)
        {
            if (! permanentRegUsage[i])
            {
                permanentRegUsage[i] = true;
                return new CAValue (RegisterType.PERMANENT, i);
            }
        }
        System.err.println ("fatal error: ran out of permanent registers");
        System.exit (0);
        return null;        // keep compiler happy
    }


    /** apply compiler semantics to the CAValue of a LexemeType.SYMBOL lexeme.
     *  (as retrieved from the Symbol Table)
     *  ...amounts to returning a copy of the CAValue receiver.
     */
    AValue evaluate ()
    {
       try
       {
          return (CAValue) this.clone();    // rtn copy of value

       } catch (CloneNotSupportedException e)
       {
            e.printStackTrace();
       }
       return new CAValue();                // keep compiler happy
    }


    /** apply compiler semantics to a NUMBER lexeme.
     *  Generate code to load number into a temp register,
     *  return register as CAValue.
     */
    AValue evaluate (Lexeme token)
    {
        CAValue reg = getTempReg();
        System.out.println  ("\tli   " + reg + ",  " + token.nval);
        return reg;
    }


    /** assign to lSide, a LexemeType.SYMBOL lexeme; return the CAValue assigned.
     *  Note: rtn value is ref to CAValue obj. actually in sym table.
     */
    AValue assignTo (Lexeme lSide)
    {
        CAValue destReg;

        if ( (destReg = (CAValue) Symtab.lookup(lSide)) == null)
        {
            destReg = getPermReg();         // alloc a register
            Symtab.add (lSide, destReg);    // add lSide to sym table
        }                                   // w/ perm reg as value
        // if lSide _was_ in table, it's register won't change

        System.out.println ("\tmove " + destReg + ", " + this);
        freeRegIfTempReg();                 // perm. regs keep; in symtab!
        return destReg;                     // ...gotta return it!!
    }

    /** return String representation of CAValue
     */
    public String toString ()
    {
        switch (regType)
        {
           case TEMPORARY: return "$t" + regNumber;
           case ZERO:      return "$0";
           case PERMANENT: return "$s" + regNumber;
           default:        return "unknown CAValue register";
        }
    }
}
