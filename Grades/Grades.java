import java.util.ArrayList;

public class Grades {

    // Calculate the average of problem set grades, including total extra credit
    public static double weightedPsetGrades(double totalEc) {
        ArrayList<Double> list = new ArrayList<>();
        list.add(91.0); // pset1 score out of 110
        list.add(84.0); // pset2 score out of 105
        list.add(70.0); // pset3 score out of 80
        list.add(124.0); // pset4 score out of 140
        list.add(39.0); // pset5 score out of 50
        list.add(72.0); // pset6 score out of 75

        double totalScored = 0;
        for (Double score : list) {
            totalScored += score; // Sum all pset scores
        }
        totalScored += totalEc; // Add total extra credit to the sum of pset scores

        // Total points possible for all psets
        double totalPointsPossible = 110 + 105 + 80 + 140 + 50 + 75;

        double percentageScore = (totalScored / totalPointsPossible) * 100; // Calculate percentage score
        double weightedScore = 0.50 * percentageScore; // 50% of the total grade

        return weightedScore; // Returns weighted Pset grade out of 50
    }

    // Method to calculate total extra credit
    public static double ec() {
        double totalEc = 7 + 2 + 23 + 23.5 + 9 + 3; // Sum of all extra credit points
        return totalEc;
    }

    // Calculate and return the weighted score for the midterm
    public static double weightedMidterm() {
        double grade = 36.0 / 50; // Midterm score
        double weightedGrade = 0.15 * (grade * 100); // 15% of the total grade
        return weightedGrade; // Returns weighted Midterm grade out of 15
    }

    // Calculate and return the weighted score for the term project
    public static double weightedTermProject() {
        double grade = 15.0 / 15; // Term project score
        double weightedGrade = 0.10 * (grade * 100); // 10% of the total grade
        return weightedGrade; // Returns weighted Term Project grade out of 10
    }

    // Calculate and return the weighted score for the final exam
    public static double weightedFinalExam() {
        double grade = 62.2 / 100; // Final exam score
        double weightedGrade = 0.25 * (grade * 100); // 25% of the total grade
        return weightedGrade; // Returns weighted Final Exam grade out of 25
    }

    public static void main(String[] args) {
        double ecValue = ec(); // Calculate total extra credit

        // Calculate and print the grade for each component without extra credit
        double psetGradeWithoutEc = weightedPsetGrades(0);
        System.out.println("PSet Grade without EC: " + psetGradeWithoutEc);

        double psetGradeWithEc = weightedPsetGrades(ecValue);
        System.out.println("PSet Grade with EC: " + psetGradeWithEc);

        double midtermGrade = weightedMidterm();
        System.out.println("Midterm Grade: " + midtermGrade);

        double termProjectGrade = weightedTermProject();
        System.out.println("Term Project Grade: " + termProjectGrade);

        double finalExamGrade = weightedFinalExam();
        System.out.println("Final Exam Grade: " + finalExamGrade);

        // Calculate and print the final grade including all components
        double finalGradeWithoutEc = psetGradeWithoutEc + midtermGrade + termProjectGrade + finalExamGrade;
        System.out.println("The final grade without EC is: " + finalGradeWithoutEc);

        double finalGradeWithEc = psetGradeWithEc + midtermGrade + termProjectGrade + finalExamGrade;
        System.out.println("The final grade with EC is: " + finalGradeWithEc);
    }
}
