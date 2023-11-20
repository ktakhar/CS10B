import java.io.*;

class FileIO2
{
    public static void main (String [] foobar)
    {
        // Check if the number of command-line arguments is not exactly one
        if (foobar.length != 1)
        {
            // Notify the user that they need to supply precisely one file name and exit the program
            System.out.println ("Sorry, you need to supply precisely one file name ...");
            System.exit(0);
        }
        
        // Create a File object based on the provided file name from the command-line arguments
        File f = new File (foobar[0]);

        // Check if the file exists and is readable
        if (f.exists() && f.canRead())
        {
            // If the file exists and is readable, print its length in bytes
            System.out.println ("Length = " + f.length());
            
            // Print the absolute path of the file
            System.out.println (f.getAbsolutePath());
        }
        else
        {
            // If the file does not exist or cannot be read, notify the user
            System.out.println ("Sorry, file does not exist or cannot be read!");
        }
    }
}
