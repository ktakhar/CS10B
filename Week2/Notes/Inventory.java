import java.util.Scanner;

enum Size { SMALL, MEDIUM, LARGE, EX_LARGE }
enum Color { BLACK, WHITE, BROWN }
enum Material { COTTON, WOOL, POLYESTER }

public class Inventory {
    public static void main(String[] args) {
        int[][][] slacks = new int[Material.values().length][Color.values().length][Size.values().length];

        // Part (a): Initialize the slacks array with random integers between 0 and 50.
        for (int i = 0; i < Material.values().length; i++) {
            for (int j = 0; j < Color.values().length; j++) {
                for (int k = 0; k < Size.values().length; k++) {
                    slacks[i][j][k] = (int) (Math.random() * 51); // Generates a random integer between 0 and 50.
                }
            }
        }

        // Part (b): Allow the user to inquire about the inventory.
        Scanner scanner = new Scanner(System.in);
        while (true) { // An indefinite loop.
            System.out.print("Enter size (SMALL, MEDIUM, LARGE, EX_LARGE): ");
            String sizeInput = scanner.nextLine().toUpperCase();
            
            System.out.print("Enter color (BLACK, WHITE, BROWN): ");
            String colorInput = scanner.nextLine().toUpperCase();
            
            System.out.print("Enter material (COTTON, WOOL, POLYESTER): ");
            String materialInput = scanner.nextLine().toUpperCase();

            try {
                Size size = Size.valueOf(sizeInput);
                Color color = Color.valueOf(colorInput);
                Material material = Material.valueOf(materialInput);

                int availableSlacks = slacks[material.ordinal()][color.ordinal()][size.ordinal()];
                System.out.println("Available slacks in " + size + "/" + color + "/" + material + ": " + availableSlacks);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter valid values.");
            }

            System.out.print("Do you want to inquire about another pair of pants? (Y/N): ");
            String anotherInquiry = scanner.nextLine();
            
            if (anotherInquiry.equalsIgnoreCase("N")) {
                break; // Exit the loop if the user doesn't want to inquire further.
            }
        }

        scanner.close();
    }
}
