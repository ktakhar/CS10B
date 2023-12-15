class MinMax {
    public static void main(String [] args) {
        int [] numbers = {1, 4, 6, 3, 3, 2};
        int maxResult = max(numbers);
        int minResult = min(numbers);

        printArray(numbers);

        System.out.println("Max number is: " + maxResult);
        System.out.println("Min number is: " + minResult);
    }

    public static int max(int[] values) {
        int tempMax = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > tempMax) {
                tempMax = values[i];
            }
        }
        return tempMax;
    }

    public static int min(int[] values) {
        int tempMin = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < tempMin) {
                tempMin = values[i];
            }
        }
        return tempMin;
    }

    public static void printArray(int [] numbers) {
        for (int i = 0; i < numbers.length; i++ ) {
            System.out.print(numbers[i] + " ");
       
        }
        System.out.println();
    }
}
