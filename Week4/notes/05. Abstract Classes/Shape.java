//  Shape.java

/**
 *  The Shape class is a an abstract template class that models geometric shapes
 *
 *  @author     David Habermehl via Jan Jackson
 */
public abstract class Shape {
    protected double x, y;               // Every Shape "has a" location

    /**
     *   0-parameter constructor for the class, which sets the default
     *  location at(0, 0).
     */
    public Shape()     {
        this( 0, 0 );
    }

    /**
     *   2-parameter constructor for the class, which sets the location
     *  at the user's specified choice.
     *
     *  @param    x    The x-coordinate for location
     *  @param    y    The y-coordinate for location
     */
    public Shape( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    /**
     *  This method will return a String describing basic information about the object
     *
     *  @return     Basic information about the object
     */
    public String toString() {
        return String.format( "I am a Shape of type %s located at ( %4.1f, %4.1f )", this.getClass().getName(), this.x, this.y );
    }

    /**
     *  Fake getArea method to satisfy compiler
     */
    //public double getArea() {
    //    return 99.0;
    //}


    /**
     *  The getArea method will return the area of the shape.
     *  Since it's abstract, it must be defined in all sub-classes.
     */
    public abstract double getArea();


    /**
     *  The getLocation method will return the location of the shape
     *  as a String. It will be available in all sub-classes.
     */
    public String getLocation() {
        return String.format( "x: %4.1f, y: %4.1f", this.x, this.y );
    }
}
