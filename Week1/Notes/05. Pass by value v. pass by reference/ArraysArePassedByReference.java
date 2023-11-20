import java.util.Arrays;


/**
 * Template class: Models a person.
 *
 * @author David Habermehl
 * @version Last modified 05_Dec_2017
 **/
class Person {
    private String name;

    public Person()              { this( "" ); }
    public Person( String name ) { this.name = name; }

    public void setName( String name ) { this.name = name; }
    public String toString() { return this.name; }
}



/**
 * Program class: Demonstration program illustrates that arrays are passed by reference.
 *
 * @author David Habermehl
 * @version Last modified 05_Dec_2017
 **/
class ArraysArePassedByReference {
    public static void main(String [] args) {

        System.out.println( "\nArrays are passed by reference.\nThat means that called " +
                            "methods see the address of the array.\nThat  means that " +
                            "modifications made by the called  method are made to the " +
                            "original array, and show up in the calling method.");

        Person[] somePeople = { new Person( "David Habermehl" ), new Person( "Henry Leitner" ) };
        System.out.printf( "\nIn main(). somePeople = %s\n", Arrays.toString( somePeople ) );

        // Objects are passed by reference.
        foo( somePeople );

        System.out.printf( "\nIn main(). somePeople = %s.\n\n", Arrays.toString( somePeople ) );
    }



    /**
     * foo
     *     1. Display argument
     *     2. Modify argument
     *     3. Display modified argument.
     *
     * @param  somePeople   Array of Person objects.
     */
    static void foo( Person[] somePeople ) {

        System.out.printf( "\n\tIn foo() before modifying " +
                           "somePeople. somePeople = %s.\n", Arrays.toString( somePeople ) );

        somePeople[0] = new Person( "Janice Habermehl" );

        System.out.printf( "\tIn foo()  after modifying " +
                           "somePeople. somePeople = %s.\n", Arrays.toString( somePeople ) );
    }
}
