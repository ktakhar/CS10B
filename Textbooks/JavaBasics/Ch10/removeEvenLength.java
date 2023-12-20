import java.util.*;

public class removeEvenLength {

    public static void main(String[] args) {
        ArrayList<String> newList = new ArrayList<>();
       
        newList.add("Hello");
        newList.add("World");
        newList.add("as");
        newList.add("if");
       
        removeEvenLength(newList);

        for (String word : newList) {
            System.out.println(word);
        }
    }

    public static void removeEvenLength(ArrayList<String> list) {
        for (int i = list.size() -1; i >= 0; i--) {
            String str = list.get(i);
            if (str.length() % 2 == 0) {
                list.remove(str);
            } 
        }
    }
}  