import java.util.*;
import java.io.*;

class Sets {
    public static void main(String[] args) throws FileNotFoundException {
        
        // Create a HashSet to store unique words
        Set<String> words = new HashSet<String>(); // TreeSet is also an option
        
        // Create a Scanner to read from the input file specified in the command line arguments
        Scanner in = new Scanner(new File(args[0])); // command line argument "RomeAndJuliet.txt"

        // Set the delimiter for the Scanner to split words based on non-alphabetical characters
        in.useDelimiter("[^a-zA-Z']");

        // Loop through the file's content, tokenizing it into words and adding them to the HashSet
        while (in.hasNext()) {
            String word = in.next(); // Read the next token (word)
            word.toLowerCase(); // Convert the word to lowercase (but this doesn't modify the 'word' variable)
            words.add(word); // Add the word to the HashSet
        }

        // Print the number of unique words in the file
        System.out.println("# of unique words = " + words.size());

        // Print the unique words (Note: This will not necessarily be in any specific order)
        System.out.println(words);
    }
}