class Ex2 {
    public static void main(String[] args) {
        int count = 0;
         int number = 10;

        for (int i = 1; i <= number; i++) {
            count+= i;
            System.out.println(count);
        }

        System.out.println("Total: " + count);
    }
}