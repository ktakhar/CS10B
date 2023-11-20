class Student {
    private String name;
    private int studentID;
    private List<Double> grades;

    public Student(String name, int studentID, List<Double> grades) {
        this.name = name;
        this.studentID = studentID;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public List<Double> getGrades() {
        return grades;
    }
}

class StudentRecordProcessor {
    public static double calculateAverageGrade(Student student) {
        // Implement this method to find the average grade of a student;
        List<Double> grades = student.getGrades();
        if (grades.isEmpty()) {
            return -1;
        } 
        double sum = 0.0;
        for (Double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public static double calculateHighestGrade(Student student) {
        // Implement this method to find the highest grade of a student.
        List<Double> grades = student.getGrades();
        return grades.stream();
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0); 
    }

    public static double calculateLowestGrade(Student student) {
        // Implement this method to find the lowest grade of a student.
    }

    public static void printStudentInfo(Student student) {
        // Implement this method to print the student's information, including their name, student ID, and grades.
    }
}
