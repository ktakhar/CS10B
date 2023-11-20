import java.io.*;
import java.util.*;

class FileInput {
    public static void main(String[] args) throws FileNotFoundException {
        // Create a new File object based on the file path provided in command-line arguments
        File f = new File(args[0]);

        // Create a Scanner to read the contents of the specified file
        Scanner s = new Scanner(f);

        // Initialize a counter to keep track of the number of blank lines
        int count = 0;

        // Iterate through each line in the file
        while (s.hasNextLine()) {
            // Read the next line from the file
            String str = s.nextLine();

            // Check if the current line, after removing leading and trailing whitespace, is empty
            if (str.trim().isEmpty()) {
                // If the line is empty (blank), increment the blank line count
                count++;
            }
        }

        // Print the total number of blank lines found in the file
        System.out.println("# of blank lines: " + count);
    }
}
