// BankAccountDemoV04.java

/**
 * Program class that demonstrates the BankAccount template class.
 *
 * V01: Instance variables are per-blob data
 *      Static variables are per-class and shared by all instances
 *      Instance methods executed on an instance via dot notation
 *
 * V02: Direct access to instance and static variables via dot notation
 *
 * V03: Instance variables should be private
 *      Instance variables should be accessed with "getter" and "setter" methods
 *
 * V04: Avoid "fake names" by qualifying instance variable references using "this"
 *      to distinguish them from instance method arguments with the same name.
 *
 * @author David Habermehl
 * @version Last modified 13_Nov_2017
 **/
public class BankAccountDemoV04 {
    public static void main( String [] args ) {

        BankAccount account1 = new BankAccount();

        account1.setAccountName( "David", "Habermehl" );
        account1.setAccountNumber();
        account1.setAccountBalance( 100.00 );

        // Retrieve David Habermehl's account details via getter methods.
        //System.out.printf( "Account #%06d is owned by %s and its balance is %,.2f\n", account1.getAccountNumber(), account1.getAccountName(), account1.getAccountBalance() );

        account1.printDetails();

        BankAccount account2 = new BankAccount();

        account2.setAccountName( "Janice", "Habermehl" );
        account2.setAccountNumber();
        account2.setAccountBalance( 1000000.00 );

        account2.printDetails();

    }
}




/**
 * Template class: A BankAccount object represents a single bank account.
 * The object's state is captured in instance variables.
 * The object "knows how to do" the capabilities in its instance methods.
 *
 * @author David Habermehl
 * @version Last modified 13_Nov_2017
 **/
class BankAccount {

    /** Next account number to be assigned to newly-created BankAccount object. */
    private static long nextAccountNumber = 0;

    /** Bank account holder's first name. */
    private String firstName;

    /** Bank account holder's last name. */
    private String lastName;

    /** Bank account's account number. */
    private long   accountNumber;

    /** Bank account's balance. */
    private double accountBalance;



    /**
     * Set the BankAccount object's firstName and lastName instance variables.
     *
     * @param  firstName    The account holder's first name
     * @param  lastName     The account holder's last name
     */
    public void setAccountName( String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    /**
     * Get the BankAccount object's firstName and lastName instance variables.
     *
     * @return  The account holder's name ("lastName, firstName").
     */
    public String getAccountName() {
        return this.lastName + ", " + this.firstName;
    }



    /**
     * Set the BankAccount object's accountBalance instance variable.
     *
     * @param  accountBalance     The new balance
     */
    public void setAccountBalance( double accountBalance ) {
        this.accountBalance = accountBalance;
    }



    /**
     * Get the BankAccount object's accountBalance instance variable.
     *
     * @return  The account's balance
     */
    public double getAccountBalance() {
        return this.accountBalance;
    }



    /**
     * Set the BankAccount object's accountNumber instance variable.
     */
    public void setAccountNumber() {
        this.accountNumber = BankAccount.nextAccountNumber++;
    }



    /**
     * Get the BankAccount object's accountNumber instance variable.
     */
    public long getAccountNumber() {
        return this.accountNumber;
    }



    /**
     * Print a representation of this BankAccount object.
     */
    public void printDetails() {
        System.out.printf( "Name: %-25s   Account #: %06d   Balance: %,12.2f\n", this.lastName+", "+this.firstName, this.accountNumber, this.accountBalance );
    }
}
