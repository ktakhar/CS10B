// PublicClasses.java

/**
 * Show rules regarding public classes:
 *
 * 1. If a file contains a public class Foo, the file's name must be Foo.java
 *
 * 2. A file cannot contain more than one public class
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/

public class PublicClasses {
    public static void main( String [] args ) {
        Foo foo = new Foo();
        foo.helloWorld();

        Bar bar = new Bar();
        bar.helloWorld();
    }
}

class Foo {
    void helloWorld() { System.out.println( "This is Foo's helloWorld()" ); }
}

class Bar {
    void helloWorld() { System.out.println( "This is Bar's helloWorld()" ); }
}
