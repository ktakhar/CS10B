class Basics {

    public Basics() {

    }

    public static boolean checkEven(int n) {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean isEven = checkEven(25);
        System.out.println(isEven);
    }
}