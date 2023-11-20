// BankAccountDemoV08.java

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
 * V05: Include a constructor method that accepts arguments used to initialize
 *      the object's instance variables when the object is constructed.
 *
 * V06: Best practice: always include a zero-arg constructor that assigns default
 *      values to all instance variables.
 *
 * V07: Best practice: always include a toString() instance method that returns a
 *      String representation of the object.
 *
 * V08: When an instance of an object is referenced in a context that expects a
 *      String, its class's toString() method is automatically called.
 *
 * @author David Habermehl
 * @version Last modified 13_Nov_2017
 **/
public class BankAccountDemoV08 {
    public static void main( String [] args ) {

        BankAccount account1 = new BankAccount( "David", "Habermehl", 100.00 );
        System.out.printf( "%s\n", account1 );
        account1.printDetails();

        BankAccount account2 = new BankAccount( "Janice", "Habermehl", 1000000.00 );
        System.out.printf( "%s\n", account2 );
        account2.printDetails();

        BankAccount account3 = new BankAccount();
        System.out.printf( "%s\n", account3 );
        account3.printDetails();

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
     * Constructor creates a BankAccount object and initializes its instance variables.
     *
     * @param  firstName          The account holder's first name
     * @param  lastName           The account holder's last name
     * @param  accountBalance     The account's initial balance
     */
    public BankAccount( String firstName, String lastName, double accountBalance ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
        this.accountNumber = BankAccount.nextAccountNumber++;
    }



    /**
     * Zero-arg constructor creates a BankAccount object and assigns default values
     * to its instance variables.
     */
    public BankAccount() {
        this.firstName = "";
        this.lastName = "";
        this.accountBalance = 0.00;
        this.accountNumber = BankAccount.nextAccountNumber++;
    }



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
     * Get a String representation of this BankAccount object.
     *
     * @return  String representation of this BankAccount object.
     */
    public String toString() {
        return String.format( "Name: %-25s   Account #: %06d   Balance: %,12.2f", this.lastName+", "+this.firstName, this.accountNumber, this.accountBalance );
    }



    /**
     * Print a representation of this BankAccount object.
     */
    public void printDetails() {
        System.out.printf( "%s\n", this );
    }
}
