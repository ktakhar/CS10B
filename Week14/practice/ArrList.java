import java.util.*;

class ArrList {
    public static void main(String[] args) {
        String [] words = {"Hello", "World"};
        ArrayList<String> printList = new ArrayList<>();

        for (String word : words) {
            printList.add(word);
        }

        reverse(printList);

    }
    public static void reverse(ArrayList<String> list) {
       String str = " ";
       for (String listItem : list) {
        System.out.println(listItem);
       }
    }
}

class reverseList {
    public static void main(String[] args) {

    }
    public static void reverse(ArrayList<String> list) {
        String str = "";
        
    }
}