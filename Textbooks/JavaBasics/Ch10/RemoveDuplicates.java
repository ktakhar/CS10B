import java.util.ArrayList;

public class RemoveDuplicates {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("be");
        list.add("be");
        list.add("is");
        list.add("not");
        list.add("or");
        list.add("question");
        list.add("that");
        list.add("the");
        list.add("to");
        list.add("to");

        removeDuplicates(list);

        // Print the updated list
        System.out.println(list);
    }

    public static void removeDuplicates(ArrayList<String> list) {
        if (list.isEmpty()) {
            return; // No duplicates to remove from an empty list
        }

        for (int i = 1; i < list.size(); i++) {
            String current = list.get(i);
            String previous = list.get(i - 1);

            if (current.equals(previous)) {
                list.remove(i);
                i--; // Adjust the index after removing an element
            }
        }
    }
}
