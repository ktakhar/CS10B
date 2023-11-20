// Scope2.java

/**
 * Class-scope variables are visible to all methods.
 * DON'T USE CLASS-LEVEL VARIABLES IN PROGRAM CLASSES (UNLESS THEY ARE FINAL
 * CONSTANTS). WE'LL TAKE POINTS IF YOU DON'T FOLLOW THAT GUIDELINE!!!
 *
 * @author David Habermehl
 * @version Last modified 26_Sep_2017
 **/
class Scope2 {
    static final double SALESTAXRATE = .075;

    public static void main( String [] args ) {
        double dollarAmount = 100.0;
        System.out.println( "Sales tax on $" + dollarAmount + " is $" + computeSalesTax( dollarAmount ) );
        System.out.printf( "Sales tax on $%f.2 is $%f.2\n", dollarAmount, computeSalesTax( dollarAmount ) );
    }



    /**
     * Method to compute sales tax
     *
     * @param  dollarAmount     Amount for which sales tax is computed
     * @return Sales tax on dollarAmount
     */
    static double computeSalesTax( double dollarAmount ) {
        return dollarAmount * SALESTAXRATE;
    }
}