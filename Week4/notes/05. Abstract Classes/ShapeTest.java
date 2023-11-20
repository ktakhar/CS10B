//  ShapeTest.java

/**
 *  This program class uses the Shape, Circle, and Square classes to demonstrate
 *  inheritance.  It makes clear the need for abstract methods in the superclass.
 *  It also shows shat happens when a subclass object is stored in a superclass container.
 *
 *  @author     David Habermehl via Jan Jackson
 */

public class ShapeTest {
    public static void main( String [] args ) {

        // This will cause an error, because you can't create an instance of an abstract class.
        //Shape sh = new Shape();

        //System.out.printf( "Constructing Circle c and Square sq\n" );
        //Circle c = new Circle( 1, 1, 3 );
        //Square sq = new Square( 2, -1, 2 );

        //demoToString( c, sq );
        //demoPolymorphism( c, sq );
        //demoInheritance( c, sq );
        //demoAbstractMethod( c, sq ); // NEED TO UNCOMMENT BODY OF METHOD'S FOR LOOP
        //demoSuperclassContainerBehavior( c, sq );
        //demoObjectClassContainerBehavior( c, sq );
        //demoGetNameAndGetClass( c, sq );
    }

    static void demoToString( Circle c, Square sq ) {
        System.out.println( "\nDemo Shape, Circle and Square classes\' toString() methods" );
        System.out.printf( "\tPrinting  c : %s\n",  c );
        System.out.printf( "\tPrinting sq : %s\n", sq );
    }

    static void demoPolymorphism( Circle c, Square sq ) {
        System.out.println( "\nDemo polymorphism by executing class-specific getArea() method:" );
        System.out.printf( "\tArea of  c : %4.1f\n",  c.getArea() );
        System.out.printf( "\tArea of sq : %4.1f\n", sq.getArea() );
    }

    static void demoInheritance( Circle c, Square sq ) {
        System.out.println( "\nDemo inheritance by executing Shape class's getLocation() method:" );
        System.out.printf( "\tLocation of  c : %s\n",  c.getLocation() );
        System.out.printf( "\tLocation of sq : %s\n", sq.getLocation() );
    }

    static void demoAbstractMethod( Circle c, Square sq ) {
        System.out.println( "\nDemo the usefulness of abstract methods:" );
        Shape [] shapes = { c, sq };  // shapes[0] = c; shapes[1] = sq;
        int index = 0;
        for( Shape shape : shapes ) {
            System.out.printf( "\tThe area of shape[%d] is %4.1f\n", index++, shape.getArea() );
        }
    }

    static void demoSuperclassContainerBehavior( Circle c, Square sq ) {
        System.out.println( "\nDemo that Circles and Squares stored in Shape containers know if they're a Circle or a Square:" );
        Shape [] shapes = { c, sq };  // shapes[0] = c; shapes[1] = sq;
        for( Shape shape : shapes ) {
            System.out.printf( "\t%s\n", shape );
        }
    }

    static void demoObjectClassContainerBehavior( Circle c, Square sq ) {
        System.out.println( "\nDemo that Shapes stored in Object containers know if they're a Circle or a Square:" );
        Shape [] shapes = { c, sq };  // shapes[0] = c; shapes[1] = sq;
        Object [] objects = { shapes[0], shapes[1] };
        for( Object object : objects ) {
            System.out.printf( "\t%s\n", object );
        }
    }

    static void demoGetNameAndGetClass( Circle c, Square sq ) {
        System.out.println( "\nDemo getName() and getClass():" );
        Shape [] shapes = { c, sq };  // shapes[0] = c; shapes[1] = sq;
        int i = 0;
        for( Shape shape : shapes ) {
            System.out.printf( "\tshapes[%d] is an instance of the %s class\n", i++, shape.getClass().getName() );
        }
    }
}
