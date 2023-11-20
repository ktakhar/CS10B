class HourglassParam {
    public static void main(String[] args) {
        int SUB_HEIGHT = 5;
        drawTop(SUB_HEIGHT);
        writeSpaces(10);
    }
    public static void writeSpaces(int number) {
        for (int i = 1; i <= number; i++) {
            System.out.print(" ");
        }
    }
    public static void drawTop(int SUB_HEIGHT) {
        for (int line = 1; line <= SUB_HEIGHT; line++) {
            System.out.print("|");
            writeSpaces(line - 1);
            System.out.print("\\");
            int dots = 2 * SUB_HEIGHT - 2 * line;
            for (int i = 1; i <= dots; i++) {
                System.out.print(".");
            }
            System.out.print("/");
            writeSpaces(line - 1);
                System.out.println("|");
        }
    }
}