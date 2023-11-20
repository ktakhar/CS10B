import java.io.*;

class FileIO
{
    public static void main (String [] args)
    {
        // Create a File object named 'f' representing a file with the name "data.txt"
        File f = new File ("data.txt");

        // Check if the file exists and is readable
        if (f.exists() && f.canRead())
        {
            // If the file exists and is readable, print its length in bytes
            System.out.println ("Length = " + f.length());
        }
        else
        {
            // If the file does not exist or cannot be read, notify the user
            System.out.println ("Sorry, file does not exist or cannot be read!");
        }
    }
}
