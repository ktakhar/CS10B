// DemoConstructorExecution program class demonstrates:
//     1. The order in which constructors are executed
//        (Constructors do an implied super() as their first action)
//     2. That you get a default 0-arg constructor automatically ...
//     3. ... Unless you provide your own constructor(s).

class DemoConstructorExecution {
    public static void main( String [] args ) {
        demoZeroArgConstructors();
        demoOneArgConstructors();
    }

    static void demoZeroArgConstructors() {
        new Sedan();
    }

    static void demoOneArgConstructors() {
        System.out.println();
        Sedan camry = new Sedan( "red" );
        System.out.printf( "camry.getPaintColor() is \"%s\"\n", camry.getPaintColor() );
    }
}

class Vehicle {
    String paintColor;

    public Vehicle() { System.out.println( "In Vehicle's 0-arg constructor" ); }
    public Vehicle( String paintColor ) {
        System.out.println( "In Vehicle's 1-arg constructor" );
        this.paintColor = paintColor;
    }
    String getPaintColor() { return this.paintColor; }
}

class Car extends Vehicle {
    public Car() { System.out.println( "In Car's 0-arg constructor" ); }
    public Car( String paintColor ) {
        super( paintColor );
        System.out.println( "In Car's 1-arg constructor" );
    }
}

class Sedan extends Car {
    public Sedan() { System.out.println( "In Sedan's 0-arg constructor" ); }
    public Sedan( String paintColor ) {
        super( paintColor );
        System.out.println( "In Sedan's 1-arg constructor" );
    }
}