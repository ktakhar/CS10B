class StudentIDTest {
    public static void main (String [] args) {
        StudentID student1 = new StudentID (123456, "John Doe");
        StudentID student2 = new StudentID (654321, "Jane Doe");

        System.out.println ("Student ID: " + student1.getID() + "\nStudent Name: " + student1.getName() );
        System.out.println ("Student ID: " + student2.getID() + "\nStudent Name: " + student2.getName() );

    }
}