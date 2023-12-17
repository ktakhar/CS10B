class FindSum {
    public static void main(String[] args) {
        int [] sumList = {1, 3, 5, 6};

        int result = findSum(sumList);
        System.out.println(result);
    }
    // public static int findSum(int[] numList) {
    //     int sum = 0;
    //     for (int i = 0; i < numList.length; i++) {
    //         sum += numList[i];
    //     }
    //     return sum;
    // }

    public static int findSum(int[] numList) {
        int sum = 0;
        for (int n : numList) {
            sum += n;
        }
        return sum;
    }
}