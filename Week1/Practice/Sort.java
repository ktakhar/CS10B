import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] numList = {1, 4, 5, 3};

        // Sort the array in ascending order
        Arrays.sort(numList);

        // Print the sorted array in ascending order
        System.out.println("Sorted in ascending order:");
        Arrays.stream(numList).forEach(System.out::println);

        // Sort the array in descending order
        int[] descendingOrder = Arrays.stream(numList)
                .boxed()
                .sorted((a, b) -> b - a) // Reverse order comparator
                .mapToInt(Integer::intValue)
                .toArray();

        // Print the sorted array in descending order
        System.out.println("\nSorted in descending order:");
        Arrays.stream(descendingOrder).forEach(System.out::println);
    }
}