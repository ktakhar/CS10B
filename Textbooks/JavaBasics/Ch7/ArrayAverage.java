public class ArrayAverage {
    public static double average(int[] arr) {
        int sum = 0;
        
        for (int num : arr) {
            sum += num;
        }
        
        // Cast the sum to double and divide by the number of elements in the array
        double average = (double) sum / arr.length;
        
        return average;
    }

    public static void main(String[] args) {
        int[] values = {1, -2, 4, -4, 9, -6, 16, -8, 25, -10};
        double result = average(values);
        System.out.println("The average is: " + result);
    }
}
