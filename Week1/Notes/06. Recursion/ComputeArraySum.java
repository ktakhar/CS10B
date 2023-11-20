import java.util.Arrays;

/**
 * Program class demonstrates recursive algorithm to add up elements of an array of int values.
 *
 * @author David Habermehl
 * @version Last modified 01_Feb_2018
 **/
class ComputeArraySum {

    public static void main(String [] args) {
        int [] arrayOfIntValues = { 3, 17, 10 }; // Array's ints add up to 30

        int indexOfFirstIntToLookAt = 0,
            total;

        total = addThemUp( arrayOfIntValues, indexOfFirstIntToLookAt );

        System.out.printf( "\n\naddThemUp( %s, %d ) = %d\n\n",
                                            Arrays.toString( arrayOfIntValues ),
                                               indexOfFirstIntToLookAt,
                                                      total );
    }



    /**
     * addThemUp recursively computes the sum of entries in an array of int values
     *
     * @param   arrayOfIntValues         Array whose entries will be summed.
     * @param   indexOfFirstIntToLookAt  Index of first int to be included in the sum.
     * @return  sum of entries in arrayOfIntValues.
     */
    private static int addThemUp( int [] arrayOfIntValues, int indexOfFirstIntToLookAt ) {

        // Base case: Only summing one int, the last value in the array,
        // so addThemUp returns answer without calling itself recursively.
        if ( indexOfFirstIntToLookAt == arrayOfIntValues.length-1 ) {
            return arrayOfIntValues[indexOfFirstIntToLookAt];
        }

        // Recursive case: Summing multiple ints, so addThemUp computes the answer by calling itself recursively.
        else {
            return arrayOfIntValues[indexOfFirstIntToLookAt]
                 + addThemUp( arrayOfIntValues, indexOfFirstIntToLookAt+1 );
            //                                  -------------------------
            //                                  Each recursive call to addThemUp gets us closer to the base case
        }
    }
}
