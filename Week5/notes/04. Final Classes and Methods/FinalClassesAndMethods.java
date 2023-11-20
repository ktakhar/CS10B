// FinalClassesAndMethods.java

/**
 * Show that final classes can't be extended
 * and that final methods can't be overridden
 *
 * 1. Show that final classes can't be extended:
 *        If we uncomment "final class Foo" instead of "class Foo",
 *        then Bar's attempt to extend Foo will cause a compiler error.
 *
 * 2. Show that final methods can't be overridden:
 *        If we allow Bar to extend Foo,
 *        then if we uncomment Foo's "final void helloWorld()" instead of "void helloWorld()",
 *        then Bar's helloWorld() method will cause a compiler error.
 *
 * 3. If we allow Bar to extend Foo and
 *    then if we allow Bar's helloWorld() to override Foo's helloWorld()
 *    then Bar's helloWorld() can be executed.
 *
 * 4. Show that a subclass inherits methods from the superclass:
 *        If we allow Bar to extend Foo,
 *        and if we remove Bar's helloWorld(),
 *        then Bar inherits Foo's helloWorld()
 *
 * 5. Show that even if a subclass overrides a method, the subclass
 *    can still execute the superclass's version of the method:
 *        If we allow Bar to extend Foo
 *        and if we allow Bar.helloWorld() to override Foo's helloWorld(),
 *        then super.helloWorld() from inside Bar.helloWorld() executes ok.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/

class FinalClassesAndMethods {
    public static void main( String [] args ) {
        System.out.println( "\nNow we'll execute Bar's helloWorld() from main()" );
        Bar bar = new Bar();
        bar.helloWorld();
    }
}

//final class Foo {
class Foo {

    //final void helloWorld() { System.out.println( "This is Foo's helloWorld()" ); }
    void helloWorld() { System.out.println( "This is Foo's helloWorld()" ); }
}

class Bar extends Foo {
//class Bar {
    void helloWorld() {
        System.out.println( "This is Bar's helloWorld()" );
        System.out.println( "\nNow we'll execute Foo's helloWorld() from within Bar's helloWorld() via super.helloWorld()." );
        super.helloWorld();
    }
}
