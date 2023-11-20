class Ex3 {
    public static void main(String[] args) {
        int count = 0;
        int number = 10;

        for (int i = 1; i <= number; i++ ) {
            count += i;
        }
        System.out.println("sum of the numbers from 1 to " + number + " is " + count);
    }
}