// Template superclass and its two subclasses.
class Car                   { String foo() { return "This is Car's foo()" ;       } }
class Sedan   extends Car   { String foo() { return "This is Sedan's foo()" ;     } }
class Hatchback extends Car { String foo() { return "This is Hatchback's foo()" ; } }

// Program class demonstrate how superclass/subclass containers/values work
class SedanIsACar {

    public static void main( String [] args ) {

        Car car;
        Sedan sedan;
        Hatchback hatchback;



        // It's Ok to store a subclass value in superclass container because
        // a subclass instance always has an "is a" relationship with the superclass.
        car = new Sedan();

        // It's not Ok to store superclassclass value in subclass container because
        // a superclass instance won't necessarily have an "is a" relationship with the subclass.
        // sedan = car;

        // If you KNOW that the superclass variable contains the "correct" subclass, you can coerce
        // the assignment via casting
        sedan = (Sedan) car;
        // hatchback = (Hatchback) car; // WON'T WORK!



        // Following code emonstrates overloading.
        car       = new Car();
        sedan     = new Sedan();
        hatchback = new Hatchback();
        System.out.printf( "car.foo() returned %s\n",       car.foo() );
        System.out.printf( "sedan.foo() returned %s\n",     sedan.foo() );
        System.out.printf( "hatchback.foo() returned %s\n", hatchback.foo() );



        // Following code shows that Sedan inherited the Object class's toString()
        System.out.printf( "sedan.toString produced %s\n", sedan );
    }
}
