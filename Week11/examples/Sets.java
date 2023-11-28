import java.util.*;
import java.io.*;

class Sets {
    public static void main(String[] args) throws FileNotFoundException {
        
        Set <String> words = new HashSet<String> (); // TreeSet also an option
        
        Scanner in = new Scanner (new File (args[0])); // command line argument "RomeAndJuliet.txt"

        in.useDelimiter("[^a-zA-Z']");

        while (in.hasNext()) {
            String word = in.next();
            word.toLowerCase();
            words.add(word);
        }
        System.out.println("# of unique words = " + words.size());
        System.out.println(words);
    }
}