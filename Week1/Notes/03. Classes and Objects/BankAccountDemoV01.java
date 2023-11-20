// BankAccountDemoV01.java

/**
 * Program class that demonstrates the BankAccount template class.
 *
 * V01: Instance variables are per-blob data
 *      Static variables are per-class and shared by all instances
 *      Instance methods executed on an instance via dot notation
 *
 * @author David Habermehl
 * @version Last modified 13_Nov_2017
 **/
public class BankAccountDemoV01 {
    public static void main( String [] args ) {
        BankAccount account1 = new BankAccount();
        account1.printDetails();

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
    static long nextAccountNumber = 0;

    /** Bank account holder's first name. */
    String firstName;

    /** Bank account holder's last name. */
    String lastName;

    /** Bank account's account number. */
    long   accountNumber;

    /** Bank account's balance. */
    double accountBalance;



    /**
     * Print a representation of this BankAccount object.
     */
    public void printDetails() {
        System.out.printf( "Name: %-25s   Account #: %06d   Balance: %,12.2f\n",
                           lastName+", "+firstName, accountNumber,
                           accountBalance );
    }
}
