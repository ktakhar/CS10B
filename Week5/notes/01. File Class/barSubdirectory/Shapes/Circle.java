//  Circle.java

/**
 *  The Circle class extends the Shape class, and describes a circle.
 *
 *  @author     David Habermehl via Jan Jackson
 */
public class Circle extends Shape {      // Every Circle "is a" Shape

    private double radius;             // Every Circle "has a" radius

    /**
     *  0-parameter constructor, it creates a basic unit circle with a
     *  radius of size 1.
     */
    public Circle() {
        this( 0, 0, 1 );
    }

    /**
     *  The 3-argument constructor allows the user to specify the circle's
     *  location on the x/y axis, as well as the circle's size(radius).
     *
     *  @param     x     The x-coordinate for the circle's location
     *  @param     y     The y-coordinate for the circle's location
     *  @param     r     The size of the circle's radius
     */
    public Circle( double x, double y, double radius ) {
        super( x, y );
        this.radius = radius > 0 ? radius : 1;   // ternary operator
        System.out.printf( "\tConstructing Circle at ( %4.1f, %4.1f ), radius = %4.1f\n", x, y, radius );
    }

    /**
     *  This version overrides the toString() in the Shape class(and also
     *  uses it) to describe the circle's information
     *
     *  @return     The String representation of information about the circle
     */
    public String toString() {
        return String.format( "%s. My radius is %4.1f",
                              super.toString(), this.radius );
    }

    /**
     *  This method computes the area of the circle
     *
     *  @return     The area of the circle.
     */
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}
