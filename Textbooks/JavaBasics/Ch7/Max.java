class Max {
    public static void main(String[] args) {
        int[] numbers = {12, 7, -1, 25, 3, 9};
        int maxValue = max(numbers);
        System.out.println("The maximum value is: " + maxValue);
    }
    public static int max (int[] arr) {
        int max = arr[0];
    
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
