public class AccountHolder {
    private BankAccount account;

    public AccountHolder(String name, double balance) {
        account = new BankAccount(name, balance);
    }

    public void printAccountInfo() {
        System.out.println("Account Holder: " + account.getName());
        System.out.println("Account Balance: $" + account.getBalance());
    }

    public static void main(String[] args) {
        // Create an AccountHolder with hard-coded data
        AccountHolder accountHolder = new AccountHolder("Mooshy", 1000000.0);

        // Print the account holder's information
        accountHolder.printAccountInfo();
    }
}
