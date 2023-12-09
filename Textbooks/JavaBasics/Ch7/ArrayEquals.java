import java.util.Arrays;

class ArrayEquals {
    public static void main(String[] args) {
        int [] first = new int[2];
        first[0] = 3;
        first[1] = 7;

        int [] second = new int[2];
        second[0] = 3;
        second[1] = 7;

        arrayEquals(first, second);
    }
    public static void arrayEquals(int [] first, int [] second) {
        System.out.println("first = " + Arrays.toString(first));
        System.out.println("second = " + Arrays.toString(second));

        if (Arrays.equals(first, second)) {
            System.out.println("They contain the same elements");
        } else {
            System.out.println("The elements are different");
        }
    }
}