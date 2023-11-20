import java.util.*;
import java.io.*;

class Sets {
    public static void main(String[] args) throws FileNotFoundException {
        
        Set <String> words = new HashSet<String> ();
        
        Scanner in = new Scanner (new File ("RomenAndJuliet.txt"));

        while (in.hasNext()) {
            String word = in.next();
            words.add(word);
        }
        System.out.println("# of unique words = " + words.size());
    }
}