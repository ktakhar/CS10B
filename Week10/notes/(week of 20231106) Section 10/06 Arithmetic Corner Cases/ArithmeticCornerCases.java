// ArithmeticCornerCases.java

/**
 * Demonstrates how Java handles sqrt( -1.0 ), 1.0/0.0 and -1.0/0.0
 *
 * @author  David Habermehl
 * @version Last modified 10_Apr_2019
 **/
class TestObject {
    protected String testDescription,
                     isFunctionName;
    protected double expectedResult;

    TestObject( String testDescription, double expectedResult, String isFunctionName ) {
        this.testDescription = testDescription;
        this.expectedResult = expectedResult;
        this.isFunctionName = isFunctionName;
    }
}

class ArithmeticCornerCases {

    public static void main( String[] args ) {
        demoStringValuesOfDoubleConstants();
        demoArithmeticCornerCases();
    }



    static void demoStringValuesOfDoubleConstants() {
        System.out.println( "Demonstrating String values of Double constants" );
        System.out.printf( "Double.NaN = %f\n", Double.NaN );
        System.out.printf( "Double.POSITIVE_INFINITY = %f\n", Double.POSITIVE_INFINITY );
        System.out.printf( "Double.NEGATIVE_INFINITY = %f\n\n", Double.NEGATIVE_INFINITY );
    }



    static void demoArithmeticCornerCases() {
        TestObject [] testObjects = {
            new TestObject( "Math.sqrt( -1.0 )", Double.NaN, "Double.isNaN" ),
            new TestObject( "1.0/0.0", Double.POSITIVE_INFINITY, "Double.isInfinite" ),
            new TestObject( "-1.0/0.0", Double.NEGATIVE_INFINITY, "Double.isInfinite" )
        };

        for ( TestObject testObject : testObjects ) {
            test( testObject );
        }
        System.out.println();
    }



    /**
     * test method executes operations and provides detailed reports on their handling
     *
     * @param   testObject    contains test-specific details
     */
    static void test( TestObject testObject ) {

        double result             = 0.0,
               expectedResult     = testObject.expectedResult;

        String testDescription    = testObject.testDescription,
               isFunctionName     = testObject.isFunctionName,
               wasExceptionThrown = "No exception was thrown";

        boolean isFunctionValue   = true;

        // Execute the test in a try/catch to demonstrate whether an exception is thrown
        try {
            if ( testDescription.equals( "Math.sqrt( -1.0 )" ) ) {
                result = Math.sqrt( -1.0 );
                isFunctionValue = Double.isNaN( result );
            }
            else if ( testDescription.equals( "1.0/0.0" ) ) {
                result = 1.0/0.0;
                isFunctionValue = Double.isInfinite( result );
            }
            else if ( testDescription.equals( "-1.0/0.0" ) ) {
                result = -1.0/0.0;
                isFunctionValue = Double.isInfinite( result );
            }
        }
        catch( Exception e ) {
            wasExceptionThrown = String.format( "Exception was thrown: %s\n", e.getMessage() );
        }

        // Print test description
        System.out.printf( "\nTesting %s\n", testDescription );

        // Print whether the test threw an exception
        System.out.println( wasExceptionThrown );

        // Print the result
        System.out.printf( "Result of operation is %f\n", result );

        // Demonstrate that == does not detect Double.NaN,
        //                     does detect Double.POSITIVE_INFINITY
        //                     does detect Double.NEGATIVE_INFINITY
        System.out.printf( "%f == %f is %b\n", result, expectedResult, result==expectedResult );

        // Demonstrate that Double.isNaN()      does detect Double.NaN,
        //                  Double.isInfinite() does detect Double.POSITIVE_INFINITY
        //                  Double.isInfinite() does detect Double.NEGATIVE_INFINITY
        System.out.printf( "%s is %b\n", String.format( "%s( %f )", isFunctionName, result ), isFunctionValue );
    }

}
