public class Grades {
    public static void main(String[] args) {
        double pset1grade = 98;
        double pset1total = 110;
        double pset2grade = 86;
        double pset2total = 105;
        double pset3grade = 91;
        double pset3total = 80;
        double midterm = 36;
        double midtermtotal = 50;

        // Calculate total points and total points possible
        double totalPoints = pset1grade + pset2grade + pset3grade + midterm;
        double totalPointsPossible = pset1total + pset2total + pset3total + midtermtotal;

        // Calculate the grade as a percentage
        double grade = (totalPoints / totalPointsPossible) * 100;

        // Print the results
        System.out.println("Total points: " + totalPoints);
        System.out.println("Points Possible: " + totalPointsPossible);
        System.out.println("Grade: " + grade);
    }
}