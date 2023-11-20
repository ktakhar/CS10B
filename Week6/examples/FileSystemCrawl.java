import java.io.File;
import java.util.Scanner;

class FileSystemCrawl
{
   public static void main (String [] args)
   {
       // Create a Scanner object to read input from the user
       Scanner keyboard = new Scanner (System.in);
       System.out.print ("Type the name of a file or directory where traversal should start: ");
       // Read the name of the file or directory to start traversal from
       String name = keyboard.nextLine();

       // Create a File object based on the input name
       File f = new File (name);
       
       // Check if the file or directory exists
       if (! f.exists() )
       {
           System.out.println ("Sorry that file or directory does not exist!");
           // Exit the program if the file or directory does not exist
           System.exit (0);
       }
       else
       {
           // If the file or directory exists, initiate the recursive crawling process
           recursivelyCrawl(f, 0);
       }
   }

   // Recursive method to crawl through the file system
   static void recursivelyCrawl (File f, int level)
   {
       // Print an appropriate number of spaces to create a visual representation of the directory structure
       for (int i = 0; i < level; i++)
           System.out.print ("   ");
       
       // Print the name of the current file or directory
       System.out.println (f.getName());

       // Check if the current item is a directory and can be read
       if (f.isDirectory() && f.canRead())
       {
             // Iterate through the files and subdirectories within the current directory, and recursively crawl them
             for (File subF : f.listFiles())
             {
                 recursivelyCrawl(subF, level + 1);
             }
       }
       // Return to the previous level of the directory structure
       return;
   }
}
