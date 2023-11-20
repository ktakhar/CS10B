// BankAccountDemoV03.java

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
 * @author David Habermehl
 * @version Last modified 13_Nov_2017
 **/
public class BankAccountDemoV03 {
    public static void main( String [] args ) {

        BankAccount account1 = new BankAccount();

        account1.setAccountName( "David", "Habermehl" );
        account1.setAccountNumber();
        account1.setAccountBalance( 100.00 );
        // Retrieve account details via getter methods.
        System.out.printf( "\nAccount #%06d is owned by %s and its balance is %,.2f\n",
                                    account1.getAccountNumber(), account1.getAccountName(),
                                    account1.getAccountBalance() );
        account1.printDetails();

        BankAccount account2 = new BankAccount();

        account2.setAccountName( "Janice", "Habermehl" );
        account2.setAccountNumber();
        account2.setAccountBalance( 1000000.00 );
        // Retrieve account details via getter methods.
        System.out.printf( "\nAccount #%06d is owned by %s and its balance is %,.2f\n",
                                    account2.getAccountNumber(), account2.getAccountName(),
                                    account2.getAccountBalance() );
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
     * @param  theFirstName    The account holder's first name
     * @param  theLastName     The account holder's last name
     */
    public void setAccountName( String theFirstName, String theLastName ) {
        firstName = theFirstName;
        lastName = theLastName;
    }



    /**
     * Get the BankAccount object's firstName and lastName instance variables.
     *
     * @return  The account holder's name ("lastName, firstName").
     */
    public String getAccountName() {
        return lastName + ", " + firstName;
    }



    /**
     * Set the BankAccount object's accountBalance instance variable.
     *
     * @param  theAccountBalance     The new balance
     */
    public void setAccountBalance( double theAccountBalance ) {
        accountBalance = theAccountBalance;
    }



    /**
     * Get the BankAccount object's accountBalance instance variable.
     *
     * @return  The account's balance
     */
    public double getAccountBalance() {
        return accountBalance;
    }



    /**
     * Set the BankAccount object's accountNumber instance variable.
     */
    public void setAccountNumber() {
        accountNumber = nextAccountNumber++;
    }



    /**
     * Get the BankAccount object's accountNumber instance variable.
     */
    public long getAccountNumber() {
        return accountNumber;
    }



    /**
     * Print a representation of this BankAccount object.
     */
    public void printDetails() {
        System.out.printf( "Name: %-25s   Account #: %06d   Balance: %,12.2f\n",
                           lastName+", "+firstName, accountNumber,
                           accountBalance );
    }
}
