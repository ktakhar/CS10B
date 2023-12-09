class PrintElements {
    public static void main(String[] args) {
        int[] data = {14, 5, 27, -3, 2595};
        printElements(data);
    }
    public static void printElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("element [" + i + "] is " + arr[i]);
        } 
    }
}