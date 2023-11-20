class Ex4 {
    public static void main(String[] args) {
        int space1 = 3;
        int space2 = 5;

        System.out.print("*");
        writeSpaces(space1);
        System.out.print("*");

        System.out.println();

        System.out.print("!");
        writeSpaces(space2);
        System.out.println("!");

        System.out.print("<");
        writeSpaces(8);
        System.out.println(">");
    }
    public static void writeSpaces(int number) {
        for (int i = 1; i <= number; i++) {
            System.out.print(" ");
        }
    }
}