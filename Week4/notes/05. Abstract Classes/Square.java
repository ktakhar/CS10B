//  Square.java

/**
 *  The Square template class extends the Shape abstract template class, and describes a square.
 *
 *  @author     David Habermehl via Jan Jackson
 */
public class Square extends Shape {    // Every Square "is a" Shape

    private double sideLength;             // Every Square "has a" side length.

    /**
     *  0-parameter constructor, it creates a basic square at location 0,0 with
     *  sideLength of 1.
     */
    public Square() {
        this( 0, 0, 1 );
    }

    /**
     *  The 3-argument constructor allows the user to specify the square's
     *  location on the x/y axis, as well as the square's size.
     *
     *  @param     x     The x-coordinate for the circle's location
     *  @param     y     The y-coordinate for the circle's location
     *  @param     s     The length of the square's sides
     */
    public Square( double x, double y, double sideLength ) {
        super( x, y );
        this.sideLength = sideLength > 0 ? sideLength : 1;
        System.out.printf( "\tConstructing Square at ( %4.1f, %4.1f ), sideLength = %4.1f\n", super.x, super.y, sideLength );
    }

    /**
     *  This version overrides the toString() in the Shape class(and also
     *  uses it) to describe the square's information
     *
     *  @return     The String representation of information about the swuare
     */
    public String toString() {
        return String.format( "%s. My side length is %4.1f",
                              super.toString(), this.sideLength );
    }

    /**
     *  This method computes the area of the square
     *
     *  @return     The area of the square.
     */
    public double getArea() {
        return this.sideLength * this.sideLength;
    }
}
