import java.util.Scanner;

public class ProcessDataTest {
    public static void main(String[] args) {
        // Create a sample input using the provided data
        String testData = "8 4 2 9 7\n13 5 9 -2\n2 3";
        Scanner scanner = new Scanner(testData);

        // Call the processData method
        processData(scanner);

        // Close the scanner
        scanner.close();
    }

    public static void processData(Scanner input) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScan = new Scanner(line);

            int sum = 0;
            int count = 0;

            while (lineScan.hasNextInt()) {
                int value = lineScan.nextInt();
                sum += value;
                count++;
                System.out.println("Sum of " + count + " = " + sum);
            }

            double average = (double) sum / count;
            System.out.println("Average = " + average);
            System.out.println();

            lineScan.close();
        }
    }
}
