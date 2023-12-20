import java.util.ArrayList;

public class MinToFront {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(8);
        list.add(92);
        list.add(4);
        list.add(2);
        list.add(17);
        list.add(9);

        minToFront(list);

        // Print the updated list
        System.out.println(list);
    }

    public static void minToFront(ArrayList<Integer> list) {
        int minValue = list.get(0);
        int minIndex = 0;

        // Find the minimum value and its index
        for (int i = 1; i < list.size(); i++) {
            int current = list.get(i);
            if (current < minValue) {
                minValue = current;
                minIndex = i;
            }
        }

        // Remove the minimum value from its current position
        list.remove(minIndex);

        // Insert the minimum value at the front
        list.add(0, minValue);
    }
}
