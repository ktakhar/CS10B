import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the cost of the house
        System.out.print("Enter the cost of the house: $");
        double houseCost = scanner.nextDouble();

        // Prompt the user for the annual interest rate
        System.out.print("Enter the annual interest rate (as a decimal): ");
        double annualInterestRate = scanner.nextDouble();

        // Prompt the user for the down payment percentage
        System.out.print("Enter the down payment percentage (as a decimal): ");
        double downPaymentPercentage = scanner.nextDouble();

        // Calculate the down payment amount
        double downPaymentAmount = houseCost * downPaymentPercentage;

        // Calculate the loan amount
        double loanAmount = houseCost - downPaymentAmount;

        // Calculate the monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12;

        // Calculate the number of monthly payments
        int numberOfPayments = 30 * 12;

        // Calculate the monthly mortgage payment
        double monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        // Display the monthly mortgage payment
        System.out.printf("Your monthly mortgage payment is: $%.2f%n", monthlyPayment);

        // Close the scanner
        scanner.close();
    }
}
