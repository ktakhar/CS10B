// DataStreamDemo.java

import java.util.Scanner;
import java.io.FileInputStream;  // Reads 8-bit bytes from a file.
import java.io.FileOutputStream; // Writes 8-bit bytes to a file.
import java.io.DataInputStream;  // Reads Strings and primitive Java data types from a binary file.
import java.io.DataOutputStream; // Writes Strings and primitive Java data types to a binary file.
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;



/**
 * The MyCustomDataRecord template class models objects whose instance variables are
 * Strings and primitive Java data types. The MyCustomDataRecord class  knows how to
 *     1. write an instance of itself to a DataOutputStream
 *     2. read an instance of itself using a DataInputStream
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
class MyCustomDataRecord {



    // Instance variables
    private boolean booleanValue;
    private int     intValue;
    private double  doubleValue;
    private String  stringValue;



    // Constructors
    public MyCustomDataRecord() { this( true, 0, 0.0, ""); }
    public MyCustomDataRecord( boolean booleanValue, int intValue, double doubleValue, String stringValue ) {
        this.booleanValue = booleanValue;
        this.intValue     = intValue;
        this.doubleValue  = doubleValue;
        this.stringValue  = stringValue;
    }



    /**
     * writeRecord writes this object's instance variables to a DataOutputStream.
     *
     * @param  outputFileWriter   DataOutputStream to which instance variables are written.
     */
    public void writeRecord( DataOutputStream outputFileWriter ) throws IOException {
            outputFileWriter.writeBoolean( this.booleanValue );
            outputFileWriter.writeInt(     this.intValue );
            outputFileWriter.writeDouble(  this.doubleValue );
            outputFileWriter.writeUTF(     this.stringValue );
    }



    /**
     * readRecord reads a MyCustomDataRecord object's instance variables from a DataInputStream.
     *
     * @param  inputFileReader   DataInputStream from which instance variables are read.
     * @return MyCustomDataRecord object constructed from read instance variables, or null on EOF.
     */
    public static MyCustomDataRecord readRecord( DataInputStream inputFileReader ) throws IOException {
        MyCustomDataRecord newRecord = new MyCustomDataRecord();

        try {
            newRecord.booleanValue = inputFileReader.readBoolean(  );
            newRecord.intValue     = inputFileReader.readInt(    );
            newRecord.doubleValue  = inputFileReader.readDouble( );
            newRecord.stringValue  = inputFileReader.readUTF(  );
        }
        catch( EOFException eofe ) { newRecord = null; }

        return newRecord;
    }



    /**
     * toString returns a String representation of this MyCustomDataRecord object.
     *
     * @return String representation of this MyCustomDataRecord object.
     */
    public String toString() {
        return String.format( "%5b   %5d   %5.2f   %10s", this.booleanValue, this.intValue, this.doubleValue, this.stringValue );
    }
}



/**
 * The DataStreamDemo program class writes or reads a binary file.
 * The binary file is written with a DatStreamOutput object.
 * The binary file is read with a DataStreamInput object.
 *
 * @author David Habermehl
 * @version Last modified 13_Feb_2018
 */
public class DataStreamDemo {

    public static void main( String[] args ) {
        runDataStreamDemo();
    }



    /**
     * runDataStreamDemo runs the actual demo.
     */
    private static void runDataStreamDemo() {
        Scanner          keyboard = new Scanner( System.in );
        DataInputStream  inputFileReader  = null;
        DataOutputStream outputFileWriter = null;
        char             command;
        boolean          notDone;

        do {
            command = promptForStringValue( keyboard, "\nType r (read file), w (write file), or q (quit): " ).trim().toLowerCase().charAt(0);
            notDone = command != 'q';

            switch( command ) {
                case 'r':
                    inputFileReader  = getInputFileReader( keyboard );
                    dataStreamDemoRead( inputFileReader );
                    break;

                case 'w':
                    outputFileWriter = getOutputFileWriter( keyboard );
                    dataStreamDemoWrite( outputFileWriter );
                    break;

                 case 'q':
                    break;

                default:
                    System.out.println( "Invalid command." );
            }

        } while( notDone );

        dataStreamDemoCloseStreams( inputFileReader, outputFileWriter );
        System.out.println( "Bye" );
    }



    /**
     * dataStreamDemoCloseStreams closes DataInputStreams and DataOutputStreams.
     *
     * @param  streams   Object array containing DataInputStream and DataOutputStream objects.
     */
    private static void dataStreamDemoCloseStreams( Object ... streams ) {
        for ( Object obj : streams ) {
            if ( obj != null ) {
                try {
                    if ( obj instanceof DataInputStream ) {
                        System.out.println( "Closing input file" );
                        ((DataInputStream)obj).close();
                    }
                    if ( obj instanceof DataOutputStream ) {
                        System.out.println( "Closing output file" );
                        ((DataOutputStream)obj).close();
                    }
                }
                catch( IOException ioe ) {
                    System.err.println( "Caught IOException while closing file\n" );;
                }
            }
        }
    }



    /**
     * dataStreamDemoRead processes the 'r' command, reading MyCustomDataRecord objects via inputFileReader until EOF.
     *
     * @param  inputFileReader   DataInputStream object used to read the binary data.
     */
    private static void dataStreamDemoRead( DataInputStream inputFileReader ) {
        MyCustomDataRecord newRecord = null;
        int recordNumber = 1;
        boolean notDone;
        do {
            try {
                newRecord = MyCustomDataRecord.readRecord( inputFileReader );
            }
            catch( IOException ioe) {
                System.err.printf( "Caught IOException while reading record #%d\n", recordNumber );
            }
            notDone = newRecord != null;
            if ( notDone ) { System.out.printf( "%5d. %s\n", recordNumber++, newRecord ); }
        } while( notDone );
    }



    /**
     * dataStreamDemoWrite processes the 'w' command, writing MyCustomDataRecord objects via outputFileWriter.
     *
     * @param  outputFileWriter   DataOutputStream object used to write the binary data.
     */
    private static void dataStreamDemoWrite( DataOutputStream outputFileWriter ) {
        int recordNumber = 1;

        for ( MyCustomDataRecord record : new MyCustomDataRecord [] {
                                              new MyCustomDataRecord( true,  1, 99.1, "String1" ),
                                              new MyCustomDataRecord( false,  99, 99999.1, "FOOBAR" ),
                                              new MyCustomDataRecord( false, 2, 98.2, "String2" )
                                          }
            )
        {
            try {
                record.writeRecord( outputFileWriter );
                System.out.printf( "%5d. %s\n", recordNumber++, record );
            }
            catch( IOException ieo ) {
                System.err.printf( "Caught IOException while writing %s\n", record );
            }

        }
    }



    /**
     * getInputFileReader prompts user for input file and returns DataInputStream constructed from that file,
     * repeating until a DataInputStream is successfully constructed.
     *
     * @param  keyboard   Scanner used to read response.
     * @return DataInputStream object for input file.
     */
    private static DataInputStream getInputFileReader( Scanner keyboard ) {

        String inputFileName;
        FileInputStream fileInputStream = null;

        do {
            inputFileName  = promptForStringValue( keyboard, "\nInput file: " );

            // FileInputStream constructor throws a FileNotFoundException.
            // That's a "checked" exception, so compiler insists that we handle it.
            try {
                fileInputStream  = new FileInputStream( inputFileName );
            }
            catch( FileNotFoundException fnfe ) {
                System.err.printf( "%s not found\n", inputFileName );
            }
        } while( fileInputStream == null );

        return new DataInputStream( fileInputStream );
    }



    /**
     * getOutputFileWriter prompts user for output file and returns DataOutputStream constructed from that file,
     * repeating until a DataOutputStream is successfully constructed.
     *
     * @param  keyboard   Scanner used to read response.
     * @return DataOutputStream object for output file.
     */
    private static DataOutputStream getOutputFileWriter( Scanner keyboard ) {

        String outputFileName;
        FileOutputStream fileOutputStream = null;

        do {
            outputFileName = promptForStringValue( keyboard, "\nOutput file: " );

            // FileOutputStream constructor throws a FileNotFoundException.
            // That's a "checked" exception, so compiler insists that we handle it.
            try {
                fileOutputStream = new FileOutputStream( outputFileName);
            }
            catch( FileNotFoundException fnfe ) {
                System.err.printf( "%s cannot be opened for writing\n", outputFileName );
            }
        } while( fileOutputStream == null );

        return new DataOutputStream( fileOutputStream );
    }



    /**
     * promptForStringValue prompts the user and returns the String response.
     *
     * @param  keyboard   Scanner used to read response.
     * @param  prompt     String used to prompt the user.
     * @return String containing user's response.
     */
    private static String promptForStringValue( Scanner keyboard, String prompt ) {
        System.out.print( prompt );
        return keyboard.nextLine();
    }
}
