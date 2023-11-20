class EmployeeTest {
    public static void main(String[] args) {
        
        Employee Lebron = new Employee();
        Manager Magic = new Manager();
        SalariedEmployee Steph = new SalariedEmployee("Steph Curry");
        
        System.out.println("Lebron Salary is: " + Lebron.getSalary());
        System.out.println("Magic Salary is: " + Magic.getSalary());
        System.out.println("Steph Salary is: " + Steph.getSalary());
    } 
}