// ExamAnalysis.java

/**
 * PSET3 - Problem 6
 * 
 * @author Kuljit Takhar
 * @version October 25 2023
 * 
 * Java program for analyzing and processing exam results including: 
 * Reading correct answers and student responses from the user
 * Performing analysis on the responses
 * Displaying statistics about student performance and question analysis
 * 
 * TEST FILE
 * Exams.dat 
 * 
 * ABDEBBAC D
   ABCE CACED
   DCE AEDC
   ABCEB ACED
   BBCEDBACED
   DBCE CACED
   ABCE CA E 
   BBE  CACED
   CBCEDBACED
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExamAnalysis {
    public static void main(String[] args) {
        System.out.println("I hope you are ready to begin ...");

        // Step 1: Get Correct Answers
        String correctAnswers = getCorrectAnswersFromUser();

        // Step 2: Read Student Responses
        String filePath = getFilePathFromUser();
        ArrayList<String> studentResponses = readStudentResponsesFromFile(filePath); // Exams.dat

        // Step 3: Analyze Student Responses
        ArrayList<AnalysisResult> studentAnalysis = analyzeStudentResponses(correctAnswers, studentResponses);

        // Step 4: Print Student Analysis
        System.out.println("Thank you for the data on " + studentResponses.size() + " students. Here's the analysis:");
        printStudentAnalysis(studentAnalysis);

        // Step 5: Analyze and Print Question Analysis
        analyzeAndPrintQuestionAnalysis(correctAnswers, studentResponses);
    }

    // Step 1: Get correct answers from the user
    private static String getCorrectAnswersFromUser() {
        try {
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            System.out.print("Please type the correct answers to the exam questions: ");
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Error reading correct answers from the user: " + e.getMessage());
            System.exit(1);
        }
        return "";
    }

    // Step 2: Get the file path containing student responses from the user
    private static String getFilePathFromUser() {
        try {
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
            System.out.print("What is the name of the file containing each student's responses to the questions? ");
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Error reading file path from the user: " + e.getMessage());
            System.exit(1);
        }
        return "";
    }

    // Step 2: Read student responses from a file and store them in an ArrayList
    private static ArrayList<String> readStudentResponsesFromFile(String filePath) {
        ArrayList<String> studentResponses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespaces
                if (!line.isEmpty()) { // Check if the line is not empty
                    studentResponses.add(line);
                    System.out.println("Student #" + studentResponses.size() + "'s responses: " + line);
                }
            }
            System.out.println("We have reached 'end of file!'");
        } catch (IOException e) {
            System.err.println("Error reading student responses from the file: " + e.getMessage());
            System.exit(1);
        }

        return studentResponses;
    }

    // Step 3: Analyze student responses and return a list of AnalysisResult objects
    private static ArrayList<AnalysisResult> analyzeStudentResponses(String correctAnswers, ArrayList<String> studentResponses) {
        ArrayList<AnalysisResult> studentAnalysis = new ArrayList<>();

        for (int i = 0; i < studentResponses.size(); i++) {
            String response = padResponseToMatchLength(studentResponses.get(i), correctAnswers);
            AnalysisResult result = new AnalysisResult(i + 1, correctAnswers, response);
            studentAnalysis.add(result);
        }

        return studentAnalysis;
    }

    // Helper method to pad the response to match the length of correct answers
    private static String padResponseToMatchLength(String response, String correctAnswers) {
        int responseLength = response.length();
        int correctAnswersLength = correctAnswers.length();

        if (responseLength < correctAnswersLength) {
            // Pad with spaces for missing characters
            int padding = correctAnswersLength - responseLength;
            response = response + " ".repeat(padding);
        }

        return response;
    }

    // Step 4: Print student analysis results
    private static void printStudentAnalysis(ArrayList<AnalysisResult> studentAnalysis) {
        System.out.println("Student # Correct Incorrect Blank");
        System.out.println("~~~~~~~~~ ~~~~~~~ ~~~~~~~~~ ~~~~~");
        for (AnalysisResult result : studentAnalysis) {
            System.out.println(result);
        }
    }

    // Step 5: Analyze and print question analysis
    private static void analyzeAndPrintQuestionAnalysis(String correctAnswers, ArrayList<String> studentResponses) {
        System.out.println("QUESTION ANALYSIS (* marks the correct response)");
        System.out.println("~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < correctAnswers.length(); i++) {
            char correctAnswer = correctAnswers.charAt(i);
            displayQuestionAnalysis(correctAnswer, studentResponses, i);
        }
    }

 // Step 6: Analyze and display analysis for a single question
    private static void displayQuestionAnalysis(char correctAnswer, ArrayList<String> studentResponses, int questionIndex) {
    String[] options = {"A", "B", "C", "D", "E", "Blank"};
    int[] counts = new int[options.length];

    for (String response : studentResponses) {
        if (questionIndex < response.length()) {
            char studentAnswer = response.charAt(questionIndex);
            if (studentAnswer == ' ') {
                counts[5]++; // Blank
            } else if (studentAnswer == correctAnswer) {
                counts[0]++; // Correct
            } else {
                for (int i = 1; i <= 4; i++) {
                    if (studentAnswer == options[i].charAt(0)) {
                        counts[i]++; // A, B, C, D
                        break;
                    }
                }
            }
        }
    }

    System.out.println("Question #" + (questionIndex + 1) + ":");
    System.out.printf("%-6s %-6s %-6s %-6s %-6s %-6s%n", "A*", "B", "C", "D", "E", "Blank");
    for (int count : counts) {
        System.out.printf("%-6d ", count);
    }
    System.out.println();
    
    for (int count : counts) {
        double percentage = (double) count / studentResponses.size() * 100;
        System.out.printf("%-6.1f%% ", percentage);
    }
    System.out.println();
}

}

// Class to store and analyze student exam results
class AnalysisResult {
    private int studentNumber;
    private int correctCount;
    private int incorrectCount;
    private int blankCount;

    // Constructor to analyze and store student responses
    public AnalysisResult(int studentNumber, String correctAnswers, String studentResponse) {
        this.studentNumber = studentNumber;
        analyze(correctAnswers, studentResponse);
    }

    // Analyze student responses and count correct, incorrect, and blank answers
    private void analyze(String correctAnswers, String studentResponse) {
        correctCount = 0;
        incorrectCount = 0;
        blankCount = 0;

        int responseLength = studentResponse.length();

        for (int i = 0; i < correctAnswers.length() && i < responseLength; i++) {
            char correctAnswer = correctAnswers.charAt(i);
            char studentAnswer = studentResponse.charAt(i);

            if (studentAnswer == ' ') {
                blankCount++;
            } else if (studentAnswer == correctAnswer) {
                correctCount++;
            } else {
                incorrectCount++;
            }
        }
    }

    // Override toString to format the analysis result
    @Override
    public String toString() {
        return String.format("%6d %6d %8d %8d", studentNumber, correctCount, incorrectCount, blankCount);
    }
}
