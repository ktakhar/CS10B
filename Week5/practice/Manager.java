class Manager extends Employee {
    int bonus = 10000;

    public double getSalary() {
        double base = super.getSalary();
        return base + bonus;
    }
}