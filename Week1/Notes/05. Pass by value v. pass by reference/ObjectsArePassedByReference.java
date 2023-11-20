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
 * Program class: Demonstration program illustrates passing arguments by reference.
 *
 * @author David Habermehl
 * @version Last modified 05_Dec_2017
 **/
class ObjectsArePassedByReference {
    public static void main(String [] args) {

        System.out.println( "\nObjects are passed by reference.\nThat means that called " +
                            "methods see the address of the object.\nThat  means that " +
                            "modifications made by the called  method are made to the " +
                            "original object, and show up in the calling method.");

        Person somePerson = new Person( "David Habermehl" );
        System.out.printf( "\nIn main(). somePerson = %s\n", somePerson );

        // Objects are passed by reference.
        foo( somePerson );

        System.out.printf( "\nIn main(). somePerson = %s.\n\n", somePerson );
    }



    /**
     * foo
     *     1. Display argument
     *     2. Modify argument
     *     3. Display modified argument.
     *
     * @param  somePerson   A Person object.
     */
    static void foo( Person somePerson ) {

        System.out.printf( "\n\tIn foo() before modifying " +
                           "somePerson. somePerson = %s.\n", somePerson );

        somePerson.setName( "Henry Leitner" );

        System.out.printf( "\tIn foo()  after modifying " +
                           "somePerson. somePerson = %s.\n", somePerson );
    }
}
