// FileClassDemo.java

import java.io.File;
import java.io.IOException;

/**
 *  Program class shows how to use the File class to get information about files and directories.
 *
 *  @author     David Habermehl via Jan Jackson
 */
class FileClassDemo {
    public static void main( String[] args ) {
        fileClassDemo( args );
    }



    /**
     *  Run the demo that displays a nicely formatted directory listing of folder
     *  pointed to by command line arguments.
     *
     *  @param    args    Array of command line arguments
     */
    static void fileClassDemo( String[] args ) {
        // Create array of File objects representing args that name readable directories.
        File[] readablePaths = getReadablePaths( args );

        // Display each readable directory.
        for ( File readablePath : readablePaths ) {
            if ( readablePath != null ) { displayDirectory( readablePath ); }
        }

        System.out.println( "\nBye\n" );
    }



    /**
     *  Return array of File objects representing args that name readable directories.
     *
     *  @param    args    Array of command line arguments
     *  @return   array of arguments representing readable folders
     */
    static File[] getReadablePaths( String[] args ) {

        File[] readablePaths = new File[ args.length ];
        int index = 0;
        File f;

        // For each arg, add its File object to readablePaths if it is a readable directory.
        for ( String arg : args ) {
            f = new File( arg );
            if      ( !f.exists() )      { System.out.printf( "ERROR: %s does not exist\n", arg ); }
            else if ( !f.canRead() )     { System.out.printf( "ERROR: %s cannot be read\n", arg ); }
            else if ( !f.isDirectory() ) { System.out.printf( "ERROR: %s is not a directory\n", arg ); }
            else { readablePaths[ index++ ] = f; }
        }

        return readablePaths;
    }



    /**
     *  Display a directory.
     *
     *  @param    readablePath    File object representing the directory to be displayed
     */
    static void displayDirectory( File readablePath ) {
        displayDirectory( readablePath, 0 ); // Initial indent level is zero
    }



    /**
     *  Display a directory recursively.
     *
     *  @param    readablePath    File object representing the directory to be displayed
     *  @param    indentLevel     How far to indent the output for this directory
     */
    static void displayDirectory( File readablePath, int indentLevel ) {

        // Print readablePath's pathname
        System.out.printf( "\n%s%s\n", indent( indentLevel ), getPathname( readablePath, indentLevel ) );

        // Retrieve all of readablePath's File objects.
        File[] files = readablePath.listFiles();

        // First display all of readablePath's files.
        for ( File file : files ) {
            if ( file.isFile() ) {
                if ( file.canRead() ) {
                    displayFile( file, indentLevel+1 );
                }
                else { System.out.printf( "ERROR: %s cannot be read\n", file ); }
            }
        }

        // Then recursively display all of readablePath directories
        for ( File file : files ) {
            if ( file.isDirectory() ) {
                if ( file.canRead() ) {
                    displayDirectory( file, indentLevel+1 );
                }
                else { System.out.printf( "ERROR: %s cannot be read\n", file ); }
            }
        }
    }



    /**
     *  Returns path String for readablePath File object. Filters out extra parent folder content,
     *  depending on indentLevel.
     *
     *  @param    readablePath    File object representing the folder whose path we want
     *  @param    indentLevel     How far to indent the output for this directory
     *  @return   path String for readablePath File object.
     */
    static String getPathname( File readablePath, int indentLevel ) {
        File parentPath = readablePath;
        String fullPathString = "";

        try {
            fullPathString = readablePath.getCanonicalPath();
            for ( int i=0; i<indentLevel+1; i++ ) {
                parentPath = parentPath.getCanonicalFile().getParentFile();
            }
        }
        catch( IOException ioe ) { System.out.println( "Whoops! IOException in getPathname()!!!" ); System.exit( -1); }

        return fullPathString.replace( parentPath.toString(), "" );
    }



    /**
     *  Display information about an individual file.
     *
     *  @param    file            File object representing the individual file to be processed
     *  @param    indentLevel     How far to indent the output for this directory
     */
    static void displayFile( File file, int indentLevel ) {
        System.out.printf( "%s%s\n", indent( indentLevel ), file.getName() );
    }



    /**
     *  Return String of blanks
     *
     *  @param    indentLevel     Controls how long the String of blanks is.
     *  @return   A String of blanks whose length is a function of indentLevel.
     */
    static String indent( int indentLevel ) {
        String result = "";
        for ( int i=0; i<indentLevel; i++ ) {
            result += "    ";
        }
        return result;
    }
}