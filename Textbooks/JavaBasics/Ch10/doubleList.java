import java.util.*;

public class doubleList {
    public static void main(String[] args) {
        ArrayList<String> doubles = new ArrayList<>();
        
        doubles.add("Hello");
        doubles.add("World");

        doubleWord(doubles);

        for (String word : doubles) {
            System.out.print(word + " ");
        }
    }

    public static void doubleWord(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            list.add(i + 1, str);
            i++;
        }
    }
}

