import java.util.Scanner;

public class BankAccount {
    public int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(int amount) {
        if (amount > 0) {
            balance -= amount;
            System.out.println("Your new balance is: " + balance);
        }
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Your new balance is: " + balance);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome! (W)ithdraw / (D)eposit / (Q)uit");
        String response = in.nextLine().toUpperCase();

        BankAccount account = new BankAccount(350000);

        while (!response.equals("Q")) {
            if (response.equals("W")) {
                System.out.println("Enter withdrawl amount: ");
                int withdrawAmount = in.nextInt();
                account.withdraw(withdrawAmount);
            } else if (response.equals("D")) {
                System.out.println("Enter deposit amount: ");
                int depositAmount = in.nextInt();
                account.deposit(depositAmount);
            } else {
                System.out.println("Uh oh.");
            }
            
            System.out.println("Welcome! (W)ithdraw / (D)eposit / (Q)uit");
            in.nextLine();
            response = in.nextLine().toUpperCase();
        }
       
        System.out.println("Thank you for using the bank.");
        
        in.close();
    }
}