// Find.java

/**
 * PSET3 - Problem 9
 * 
 * Command-line program called "Find" that searches for a specified "special word" 
 * within one or more text files provided as command line arguments. It reads each specified file, 
 * line by line, and if it finds the special word within a line, it prints the filename, 
 * line number, and the line itself to the standard output. If any errors occur during file reading, 
 * such as a file or word not being found, it reports the error to the standard error output.
 * 
 * // TEST: $ java Find hello hello.txt // 
 *    OUTPUT: hello.txt: (Line 1) hello
 * 
 * @author Kuljit Takhar
 * @version October 28 2023
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Find {
    public static void main(String[] args) {
        // Check if the special word is provided as the first command line argument
        if (args.length < 1) {
            System.out.println("Usage: java Find <special_word> <file1> <file2> ...");
            return;
        }

        // The special word to search for
        String specialWord = args[0];

        // Loop through the files specified in the command line arguments
        for (int i = 1; i < args.length; i++) {
            String filename = args[i];
            File file = new File(filename);

            if (!file.exists()) {
                System.err.println("File not found: " + filename);
                continue;
            }

            boolean wordFound = false;

            try {
                // Open and read the current file
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int lineNumber = 1;

                // Loop through each line of the file
                while ((line = reader.readLine()) != null) {
                    // Check if the line contains the special word
                    if (line.contains(specialWord)) {
                        // Print the filename, line number, and the line itself
                        System.out.println(filename + ": (Line " + lineNumber + ") " + line);
                        wordFound = true;
                    }
                    lineNumber++;
                }

                // Close the current file
                reader.close();
            } catch (IOException e) {
                // Handle any other potential exceptions
                System.err.println("Error reading " + filename + ": " + e.getMessage());
            }

            if (!wordFound) {
                System.out.println("Special word not found in " + filename);
            }
        }
    }
}

