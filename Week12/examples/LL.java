import java.util.*;
import java.io.*;

class LL {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> words = new LinkedList<String>();
        Scanner in = new Scanner(new File(args[0]));

        // Set the delimiter for the Scanner to split words based on non-alphabetical characters
        in.useDelimiter("[^a-zA-Z']");

        // Loop through the file's content, tokenizing it into words and adding them to the LinkedList
        while (in.hasNext()) {
            String word = in.next(); // Read the next token (word)
            word = word.toLowerCase(); // Convert the word to lowercase and assign it back to 'word'
            words.add(word); // Add the word to the LinkedList
        }

        System.out.println("# of words = " + words.size());

        System.out.println(words);
    }
}
