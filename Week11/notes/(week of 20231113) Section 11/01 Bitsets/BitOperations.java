import java.util.Scanner;

class BitOperations {
    final static int MAXNUM = 255;

    public static void main(String [] args) {
        bitOperationsDemo();
    }



    private static void bitOperationsDemo() {
        Scanner keyboard = new Scanner( System.in );

        System.out.print( "Enter an unsigned byte-sized base 10 number (0<=n<=255): ");
        while( keyboard.hasNextInt() ) {

            // Get base 10 number
            int number = keyboard.nextInt();
            if ( number > MAXNUM ) {
                System.out.printf( "Using %d\n", MAXNUM );
                number = MAXNUM;
            }

            // Get bitmask
            System.out.print( "Enter a byte-sized (<=8-digit) base 2 number to use as a bitmask: ");
            String bitmaskString = keyboard.next();
            int bitmask = Integer.parseUnsignedInt( bitmaskString, 2 );
            if ( bitmask > MAXNUM ) {
                System.out.printf( "Using %s\n", padLeft( Integer.toBinaryString( MAXNUM ), '0', 8 ) );
                bitmask = MAXNUM;
            }

            // Display base 10 number in binary, octal, and hex
            System.out.printf( "\nnumber:\n" +
                               "%s",
                               binaryOctalHexToString( number ) );

            // Display complement of base 10 number in binary, octal, and hex
            System.out.printf( "\nComplement of number (~%d):\n" +
                               "%s",
                               number, complementToBinaryOctalHexToString( number ) );

            // Display bitmask in binary, octal, and hex
            System.out.printf( "\nBitmask:\n" +
                               "%s",
                               binaryOctalHexToString( bitmask ) );


            // Display base 10 number & bitmask,
            //         base 10 number | bitmask,
            //         base 10 number ^ bitmask,
            System.out.printf( "\nnumber <op> bitmask\n" +
                               "\t%8s\n" +
                               "\t%8s\n" +
                               "\t--------\n" +
                               "%s\n",
                               padLeft( Integer.toBinaryString( number ), '0', 8 ),
                               padLeft( Integer.toBinaryString( bitmask ), '0', 8 ),
                               bitmaskOperationsToString( number, bitmask ) );

            System.out.print( "Enter an unsigned byte-sized base 10 number (0<=n<=255): ");
        }
    }



    // Return String showing argument in binary, octal, decimal, and hex
    private static String binaryOctalDecimalHexToString( int number ) {
        String binaryString   = padLeft( Integer.toBinaryString( number ), '0', 8 ),
               octalString    = padLeft( Integer.toOctalString( number ), '0', 2 ),
               decimalString  = padLeft( Integer.toString( number, 10 ), '0', 2 ),
               hexString      = padLeft( Integer.toHexString( number ).toUpperCase(), '0', 2 );

        return String.format( "\t%8s (base  2)\n" +
                              "\t%8s (base  8)\n" +
                              "\t%8s (base 10)\n" +
                              "\t%8s (base 16)\n",
                              binaryString, octalString, decimalString, hexString );
    }



    // Return String showing argument in binary, octal, and hex
    private static String binaryOctalHexToString( int number ) {
        String binaryString   = padLeft( Integer.toBinaryString( number ), '0', 8 ),
               octalString    = padLeft( Integer.toOctalString( number ), '0', 2 ),
               hexString      = padLeft( Integer.toHexString( number ).toUpperCase(), '0', 2 );

        return String.format( "\t%8s (base  2)\n" +
                              "\t%8s (base  8)\n" +
                              "\t%8s (base 16)\n",
                              binaryString, octalString, hexString );
    }



    // Return String showing argument's complement in binary, octal, and hex
    private static String complementToBinaryOctalHexToString( int number ) {
        number = ~number;
        String signExtendedBinaryString  = padLeft( Integer.toBinaryString( number ), '0', 32 ),
               hexString     = padLeft( Integer.toHexString( number ).toUpperCase(), '0', 2 );
        String binaryString = signExtendedBinaryString.substring( signExtendedBinaryString.length()-8 );
        String octalString   = padLeft( Integer.toOctalString( Integer.parseInt(binaryString, 2) ), '0', 3 );
        octalString = octalString.substring( octalString.length()-3 );
        String decimalString = padLeft( Integer.toString( Integer.parseUnsignedInt(signExtendedBinaryString, 2) ), '0', 3 );
        //decimalString = decimalString.substring( decimalString.length()-3 );
        hexString    = hexString.substring( hexString.length()-2 );

        return String.format( "\t%8s (base  2)\n" +
                           // "\t%32s (base  2, sign extended)\n" +
                              "\t%8s (base  8)\n" +
                           // "\t%32s (base 10)\n" +
                              "\t%8s (base 16)\n",
                           // binaryString, signExtendedBinaryString, octalString, decimalString, hexString );
                              binaryString,                           octalString,                hexString );
    }



    // Return String base 10 number <op> bitmask in binary. <op> is bitwise and, or, xor
    private static String bitmaskOperationsToString( int number, int bitmask ) {
        String andString = padLeft( Integer.toBinaryString( number & bitmask ), '0', 8 );
        String orString  = padLeft( Integer.toBinaryString( number | bitmask ), '0', 8 );
        String xorString = padLeft( Integer.toBinaryString( number ^ bitmask ), '0', 8 );

        String s = String.format( "\t%8s (and)\n" +
                                  "\t%8s (or)\n" +
                                  "\t%8s (xor)\n",
                                  andString, orString, xorString );
        return s;
    }



    private static String padLeft( String s, char padChar, int length ) {
        while ( s.length() < length ) {
            s = padChar + s;
        }
        return s;
    }
}
