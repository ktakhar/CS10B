import java.util.ArrayList;
import java.util.Scanner;

public class GradeApp {

    public static double weightedPsetGrades(ArrayList<Double> psetScores, double totalEc) {
        double totalScored = 0;
        for (Double score : psetScores) {
            totalScored += score;
        }
        totalScored += totalEc; // Add total extra credit to the sum of pset scores

        double totalPointsPossible = 110 + 105 + 80 + 140 + 50 + 75;
        double percentageScore = (totalScored / totalPointsPossible) * 100;
        double weightedScore = 0.50 * percentageScore;

        return weightedScore;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> psetScores = new ArrayList<>();
        double totalEc = 0;

        // Input problem sets and extra credit
        for (int i = 1; i <= 6; i++) {
            System.out.println("Enter pset " + i + " grade:");
            psetScores.add(scanner.nextDouble());
            System.out.println("Enter pset " + i + " extra credit:");
            totalEc += scanner.nextDouble();
        }

        System.out.println("Enter Midterm grade:");
        double midtermGrade = scanner.nextDouble() / 50 * 15; // 15% of total

        System.out.println("Enter Final grade:");
        double finalGrade = scanner.nextDouble() / 100 * 25; // 25% of total

        System.out.println("Enter Term Project grade:");
        double termProjectGrade = scanner.nextDouble() / 15 * 10; // 10% of total

        // Calculating weighted grades with and without extra credit
        double weightedPsetGradeWithEc = weightedPsetGrades(psetScores, totalEc);
        double weightedPsetGradeWithoutEc = weightedPsetGrades(psetScores, 0);
        double finalTotalGradeWithEc = weightedPsetGradeWithEc + midtermGrade + finalGrade + termProjectGrade;
        double finalTotalGradeWithoutEc = weightedPsetGradeWithoutEc + midtermGrade + finalGrade + termProjectGrade;

        // Printing the final grades
        System.out.println("The final grade without extra credit is: " + finalTotalGradeWithoutEc);
        System.out.println("The final grade with extra credit is: " + finalTotalGradeWithEc);

        scanner.close(); // Close the scanner
    }
}
