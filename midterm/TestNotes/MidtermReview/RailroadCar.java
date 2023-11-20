// P3TrainDemo.java

/**
 * P3
 * Inheritance Problem
 * 
 * Freight trains, like the one shown above, consist of 3 different types of railroad
    cars: rectangular box cars (which have a height, length and width), cylindrical tank
    cars (which have a diameter and a length), and engines. All railroad cars, regardless
    of their type, have two properties: a serial number and a year in which it was built.
    Shown below is a Java program that constructs a freight train by constructing
    individual BoxCars, TankCars and Engines and storing them in an array. The
    program then prints some basic information about these railroad cars.   
 */

/** 
class TrainDemo {
    public static void main (String [] args) {
    RailroadCar [] myFreightTrain =
        {
            new Engine ("OhBama", 1961),
            new BoxCar ("bar34", 1953, 10, 10.0, 8.9),
            new TankCar("TBrady", 1977, 10, 5.0),
            new BoxCar ("blah17", 1966, 10, 11, 5)
        };

        for (RailroadCar r : myFreightTrain){
            System.out.println( r + " and has a volume of " +
                r.volume() );
        }
    }
}

*/

/**

The output from this program appears below:
Engine #OhBama was built in 1961 and has a volume of 0.0
Box Car #bar34 was built in 1953 and has a volume of 890.0
Tank Car #TBrady was built in 1977 and has a volume of 785.3981
Box Car #blah17 was built in 1966 and has a volume of 550.0
The Engine class is pretty trivial to define:

class Engine extends RailroadCar {
    
    public Engine (String serialNumber, int yearBuilt) {
        super( serialNumber, yearBuilt );
    }
    
    public double volume () {
        return 0.0; 
    }

    public String toString() {
        return "Engine " + super.toString();
    }
}

public abstract class RailroadCar {
    
    protected String serialNumber;
    protected int yearBuilt;
    
    public RailroadCar (String s, int y) { 
        // complete this
    }
    
    public abstract double volume();
    
    public String toString () { 
        // complete this
    }
}

*/

/**
 * Part A: 
 * Fill in the two missing code fragments (one in the RailroadCar constructor, the
    other in the toString method) to complete the definition of the following
    RailroadCar class.
 */

/**
 * Part B:
 * What sort of error message would the javac compiler produce when trying to
    compile the main program if the statement
    public abstract double volume();
    were removed from the definition of RailroadCar?
 */

/**
 *
 * Part C:
 *  Now define the complete BoxCar class. Note that the volume of a BoxCar is
    easily computed as the product of its length, width and height.
 */

/**
 * Part D:
 * Suppose we want an easy way to keep track of the total number of RailroadCars
    that get constructed (whether they get stored in arrays or elsewhere). In one or two
    simple, unambiguous English sentences, how can this be accomplished by making
    changes to the RailroadCar class only?
 */


class TrainDemo {
    
    public static void main(String[] args) {
    
        RailroadCar[] myFreightTrain = {
            new Engine("OhBama", 1961),
            new BoxCar("bar34", 1953, 10, 10.0, 8.9),
            new TankCar("TBrady", 1977, 10, 5.0),
            new BoxCar("blah17", 1966, 10, 11, 5)
        };

        for (RailroadCar r : myFreightTrain) {
            System.out.println(r + " and has a volume of " + r.volume());
        }
        System.out.println();
    }
}

public abstract class RailroadCar {
    
    protected String serialNumber;
    protected int yearBuilt;
    protected static int count = 0;
    protected double length;
    protected double width; 
    protected double height;
    
    public RailroadCar(String s, int y) { 
        this.serialNumber = s;
        this.yearBuilt = y;
        count++;
    }
    
    public abstract double volume();
    
    public String toString() { 
        return "# " + this.serialNumber + " was built in " + this.yearBuilt;
    }
}

class Engine extends RailroadCar {
    
    public Engine(String serialNumber, int yearBuilt) {
        super(serialNumber, yearBuilt);
    }
    
    public double volume() {
        return 0.0; 
    }

    public String toString() {
        return "Engine " + super.toString();
    }
}

// Part B
// error: RailroadCar is not abstract and does not override abstract method volume() in RailroadCar


// Part C
class BoxCar extends RailroadCar {
    
    public BoxCar(String serialNumber, int yearBuilt, double height, double length, double width) {
        super(serialNumber, yearBuilt);
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public double volume() {
        return this.length * this.width * this.height;
    }

    public String toString() {
        return "Box Car " + super.toString();
    }
}

// Part D
// Add a static variable within the class and increment it to count the instances of RailroadCar objects