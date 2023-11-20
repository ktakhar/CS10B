// ShipTest.java

/**
 * PSET2 #5
 * Test class for Ship hierarchy by creating instances of CruiseShip and CargoShip.
 * Displays ship information for each ship in the array.
 * 
 * @author Kuljit Takhar
 * @version October 8, 2023
 * 
 */

public class ShipTest {
    public static void main(String[] args) {
        Ship[] ships = new Ship[4];

        // Create instances of CruiseShip and CargoShip with specific properties
        ships[0] = new CruiseShip("Cruise1", 2000, Ship.EngineType.DIESEL, 2000, false);
        ships[1] = new CruiseShip("Cruise2", 2010, Ship.EngineType.ELECTRIC, 1500, true);
        ships[2] = new CargoShip("Cargo1", 1995, Ship.EngineType.GAS_TURBINE, 5000.0);
        ships[3] = new CargoShip("Cargo2", 2005, Ship.EngineType.STEAM_ENGINE, 8000.0);

        // Initialize the remaining elements with null
        for (int i = 4; i < ships.length; i++) {
            ships[i] = null;
        }

        // Display information for each ship in the array
        for (Ship ship : ships) {
            if (ship != null) {
                System.out.println(ship.toString());
            }
        }
    }
}
