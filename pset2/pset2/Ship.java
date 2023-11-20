// Ship.java

/**
 * PSET2 #5
 * Abstract class representing generic Ship with properties like name, yearBuilt, and engineType.
 * It also defines an enum EngineType to categorize ship engine types.
 * Subclasses CruiseShip and CargoShip inherit from this class.
 * 
 * @author Kuljit Takhar
 * @version October 7, 2023
 * 
 */

/**
 * Public abstract class representing a Ship
 */

public abstract class Ship {
    private String name;
    private int yearBuilt;
    private EngineType engineType;

    // Enumeration to represent types of ship engines
    public enum EngineType {
        STEAM_ENGINE, STEAM_TURBINE, GAS_TURBINE, DIESEL, ELECTRIC, WIND, HUMAN_FORCE
    }

    // Constructor to initialize ship properties
    public Ship(String name, int yearBuilt, EngineType engineType) {
        this.name = name;
        this.yearBuilt = yearBuilt;
        this.engineType = engineType;
    }

    // Getter and setter methods for name, yearBuilt, and engineType
    public String getName() {
        return name;
    }

    // Rest of the class remains the same
    @Override
    public String toString() {
        return "Ship Name: " + name + ", Engine Type: " + engineType + ", Year Built: " + yearBuilt;
    }
}

/**
 * Subclass representing a CruiseShip
 */ 

class CruiseShip extends Ship {
    private int maxPassengers;
    private boolean hasNorovirus;

    // Constructor to initialize CruiseShip properties
    public CruiseShip(String name, int yearBuilt, EngineType engineType, int maxPassengers, boolean hasNorovirus) {
        super(name, yearBuilt, engineType);
        this.maxPassengers = maxPassengers;
        this.hasNorovirus = hasNorovirus;
    }

    // Getter and setter methods for maxPassengers and hasNorovirus
    @Override
    public String toString() {
        return "Cruise Ship Name: " + getName() + ", Max Passengers: " + maxPassengers;
    }
}

/**
 * Subclass representing a CargoShip
 */

class CargoShip extends Ship {
    private double cargoCapacity;

    // Constructor to initialize CargoShip properties
    public CargoShip(String name, int yearBuilt, EngineType engineType, double cargoCapacity) {
        super(name, yearBuilt, engineType);
        this.cargoCapacity = cargoCapacity;
    }

    // Getter and setter methods for cargoCapacity
    @Override
    public String toString() {
        return "Cargo Ship Name: " + getName() + ", Cargo Capacity: " + cargoCapacity + " tons";
    }
}
