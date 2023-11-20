// MultipleClassesInOneFile.java

/**
 * Show that multiple classes can live in one file.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/

class MultipleClassesInOneFile {
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
