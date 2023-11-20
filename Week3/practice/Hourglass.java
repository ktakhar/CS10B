class Hourglass {
    public static void main(String[] args) {
        int SUB_HEIGHT = 5;
        drawHourglass(SUB_HEIGHT);
    }

    public static void drawHourglass(int SUB_HEIGHT) {
        for (int line = 1; line <= SUB_HEIGHT; line++) {
            // Print leading spaces
            for (int i = 1; i <= line - 1; i++) {
                System.out.print(" ");
            }

            System.out.print("\\"); // Left side of hourglass

            // Print dots in the middle
            int dots = 2 * SUB_HEIGHT - 2 * line;
            for (int i = 1; i <= dots; i++) {
                System.out.print(".");
            }

            System.out.print("/"); // Right side of hourglass

            // Print trailing spaces
            for (int i = 1; i <= line - 1; i++) {
                System.out.print(" ");
            }

            System.out.println(); // Move to the next line
        }

        // Draw the middle line of the hourglass
        for (int i = 1; i <= 2 * SUB_HEIGHT; i++) {
            System.out.print("|");
        }

        System.out.println(); // Move to the next line

        // Draw the bottom half of the hourglass (symmetric to the top half)
        for (int line = SUB_HEIGHT; line >= 1; line--) {
            // Print leading spaces
            for (int i = 1; i <= line - 1; i++) {
                System.out.print(" ");
            }

            System.out.print("/"); // Left side of hourglass

            // Print dots in the middle
            int dots = 2 * SUB_HEIGHT - 2 * line;
            for (int i = 1; i <= dots; i++) {
                System.out.print(".");
            }

            System.out.print("\\"); // Right side of hourglass

            // Print trailing spaces
            for (int i = 1; i <= line - 1; i++) {
                System.out.print(" ");
            }

            System.out.println(); // Move to the next line
        }
    }
}
