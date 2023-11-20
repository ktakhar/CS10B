class Ex1 {
    public static void main(String[] args) {
        int number = 10;
        int quant = 3;
        printSpace(number, quant);
    }
    public static void printSpace(int number, int quant) {
        for (int i = 1; i <= number; i++) {
            System.out.println(" / ");
            for (int j = 1; j <= quant; j++) {
                System.out.println(".");
            }
        }
    }
}