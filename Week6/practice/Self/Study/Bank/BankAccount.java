import java.util.Scanner;

public class BankAccount {
    private String name;
    private double balance;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + " has $" + this.balance;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = input.nextLine();
        BankAccount account = new BankAccount(name, 0);
        System.out.println(account);

        System.out.println("Would you like to deposit or withdraw? ");
        String action = input.nextLine();

        if (action.equals("deposit")) {
            System.out.println("How much would you like to deposit? ");
            double amount = input.nextDouble();
            account.deposit(amount);
        } else if (action.equals("withdraw")) {
            System.out.println("How much would you like to withdraw? ");
            double amount = input.nextDouble();
            account.withdraw(amount);
        }

        System.out.println(account);
    }
}
