// MultipleClassesInSeparateFiles.java

/**
 * Show that multiple classes can live in separate files.
 *
 * Show automatic compilation.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/

class MultipleClassesInSeparateFiles {
    public static void main( String [] args ) {
        Foo foo = new Foo();
        foo.helloWorld();

        Bar bar = new Bar();
        bar.helloWorld();
    }
}
